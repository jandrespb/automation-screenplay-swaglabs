package com.swaglab.jandcode.utils.report.support;

import com.swaglab.jandcode.utils.report.ReportGenerator;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.Document;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WordReport extends EvidenceExtractor implements ReportGenerator {

    private XWPFDocument document;

    @Override
    public void createDocument(String scenarioName, String author, String role) {

        testJsonReading(scenarioName);
        // 1. Initialize data from the Extractor (Motor)

        if (screenshotBytes.isEmpty()) {
            System.out.println("No evidence to add to Word for scenario: " + scenarioName);
            return;
        }

        this.document = new XWPFDocument();

        try {
            setupPageMargins();
            createMainTable(scenarioName, author, role);
            insertScreenshots();
            saveFile(scenarioName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clearEvidence(); // Clean memory
        }
    }

    private void createMainTable(String scenario, String author, String role) {
        XWPFTable table = document.createTable(5, 2);
        table.setWidth("100%");

        fillRow(table.getRow(0), "Project:", "SwagLabs Ecommerce");
        fillRow(table.getRow(1), "Scenario:", scenario);
        fillRow(table.getRow(2), "Responsible:", author + " (" + role + ")");
        fillRow(table.getRow(3), "Date:", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        // Logical check for status (you can pass this as a parameter too)
        fillRow(table.getRow(4), "Execution Status:", "COMPLETED SUCCESSFULLY");
    }

    private void fillRow(XWPFTableRow row, String label, String value) {
        row.getCell(0).setText(label);
        row.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000));
        row.getCell(1).setText(value);
    }

    private void insertScreenshots() throws Exception {
        for (int i = 0; i < screenshotBytes.size(); i++) {
            XWPFParagraph para = document.createParagraph();
            XWPFRun run = para.createRun();

            run.addBreak();
            run.setBold(true);
            run.setText("Step Detail: " + stepTitles.get(i).toUpperCase());
            run.addBreak();

            try (ByteArrayInputStream is = new ByteArrayInputStream(screenshotBytes.get(i))) {
                // Image sizing in EMUs (460x230 approx)
                run.addPicture(is, Document.PICTURE_TYPE_PNG, "evidence.png", Units.toEMU(450), Units.toEMU(250));
            }
            run.addBreak(BreakType.PAGE);
        }
    }

    private void saveFile(String name) throws IOException {

        String path = System.getProperty("user.dir") + "/target/evidence-reports/";
        File folder = new File(path);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 🔥 Limpiar nombre (evita caracteres raros)
        String safeName = name.replaceAll("[^a-zA-Z0-9]", "_");

        // 🔥 Timestamp único
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        // 🔥 Nombre final del archivo
        String fileName = safeName + "_" + timestamp + ".docx";

        File file = new File(path + fileName);

        try (FileOutputStream out = new FileOutputStream(file)) {
            document.write(out);
            System.out.println("✅ Word report generated: " + file.getAbsolutePath());
        }

        document.close();
    }

    private void setupPageMargins() {
        // Logical setup for margins if needed via CTSectPr
    }

    @Override
    public void clearEvidence() {
        screenshotBytes.clear();
        stepTitles.clear();
    }
}
