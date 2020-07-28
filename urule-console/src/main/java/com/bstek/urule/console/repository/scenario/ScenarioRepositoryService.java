package com.bstek.urule.console.repository.scenario;

import java.io.InputStream;
import java.util.List;

public interface ScenarioRepositoryService {
    void saveTestScenarios(String var1, String var2, String var3) throws Exception;

    List<TestScenario> loadTestScenarios(String var1, String var2) throws Exception;

    void deleteTestScenarios(String var1, String var2) throws Exception;

    void saveTestScenarioExcel(String var1, String var2, InputStream var3) throws Exception;

    InputStream loadTestScenarioExcel(String var1, String var2) throws Exception;

    boolean deleteTestScenarioExcel(String var1, String var2) throws Exception;
}