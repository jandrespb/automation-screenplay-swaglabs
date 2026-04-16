package com.swaglab.jandcode.utils.report.support;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
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
}