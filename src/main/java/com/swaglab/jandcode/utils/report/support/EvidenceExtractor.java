package com.swaglab.jandcode.utils.report.support;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public abstract class EvidenceExtractor {

    protected List<byte[]> screenshotBytes = new ArrayList<>();
    protected List<String> stepTitles = new ArrayList<>();

    private final String SERENITY_REPORT_PATH = System.getProperty("user.dir") + "/target/site/serenity";

    protected void extractScreenshots() {
        screenshotBytes.clear();
        stepTitles.clear();

        File htmlFile = findScreenshotsHtml();
        if (htmlFile == null || !htmlFile.exists()) {
            System.out.println("No Serenity screenshot report found at: " + SERENITY_REPORT_PATH);
            return;
        }

        try {
            Document doc = Jsoup.parse(htmlFile, "UTF-8");
            // Select images - note: selector might change based on Serenity version
            Elements images = doc.select("img");

            for (Element img : images) {
                String src = img.attr("src");
                String title = img.attr("title");

                if (src.endsWith(".png") || src.endsWith(".jpg")) {
                    screenshotBytes.add(loadImageAsBytes(src));
                    stepTitles.add(title != null ? title : "Step Detail");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File findScreenshotsHtml() {
        File dir = new File(SERENITY_REPORT_PATH);
        File[] files = dir.listFiles((d, name) -> name.endsWith("_screenshots.html"));
        return (files != null && files.length > 0) ? files[0] : null;
    }

    private byte[] loadImageAsBytes(String fileName) {
        File imageFile = new File(SERENITY_REPORT_PATH + "/" + fileName);
        try (InputStream is = new FileInputStream(imageFile);
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            int nRead;
            byte[] data = new byte[16384];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            return buffer.toByteArray();
        } catch (IOException e) {
            return new byte[0];
        }
    }

    protected void testJsonReading(String targetScenario) {
        File folder = new File(System.getProperty("user.dir") + "/target/site/serenity");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files == null) return;

        ObjectMapper mapper = new ObjectMapper();

        for (File file : files) {
            try {
                JsonNode root = mapper.readTree(file);

                String scenarioName = root.path("name").asText();

                if (!scenarioName.equalsIgnoreCase(targetScenario)) {
                    continue;
                }

                System.out.println("✅ PROCESSING SCENARIO: " + scenarioName);

                JsonNode testSteps = root.path("testSteps");

                // 🔥 recorrer recursivamente
                for (JsonNode step : testSteps) {
                    processStep(step);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processStep(JsonNode step) {

        JsonNode children = step.path("children");

        // 🔥 Si tiene hijos → NO procesar este nivel
        if (children.isArray() && children.size() > 0) {
            for (JsonNode child : children) {
                processStep(child);
            }
            return;
        }

        // 🔥 SOLO nodos hoja
        String description = step.path("description").asText();
        JsonNode screenshots = step.path("screenshots");

        if (screenshots.isArray()) {
            for (JsonNode shot : screenshots) {
                String image = shot.path("screenshot").asText();

                File imgFile = new File(System.getProperty("user.dir") + "/target/site/serenity/" + image);

                if (imgFile.exists()) {
                    try {
                        byte[] bytes = Files.readAllBytes(imgFile.toPath());

                        screenshotBytes.add(bytes);
                        stepTitles.add(description);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}