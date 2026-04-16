package com.swaglab.jandcode.runners.custom;

import com.swaglab.jandcode.utils.report.ReportGenerator;
import com.swaglab.jandcode.utils.report.support.WordReport;

public class GenerateReportWord {

    public static void main(String[] args) {

        ReportGenerator report = new WordReport();

        String scenario = "Purchase Product in SwagLabs";
        String tester = "Jandtocode";
        String role = "Senior QA Automation Engineer";

        System.out.println("--- Starting Word Evidence Generation ---");

        try {

            report.createDocument(scenario, tester, role);
            System.out.println("--- Report Process Finished ---");
        } catch (Exception e) {
            System.err.println("Fatal error generating the document: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
