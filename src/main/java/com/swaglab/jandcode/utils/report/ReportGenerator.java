package com.swaglab.jandcode.utils.report;

public interface ReportGenerator {

    void createDocument(String scenarioName, String author, String role);
    void clearEvidence();

}
