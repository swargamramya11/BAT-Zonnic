package com.salmon.test.runners.RunRequiredTestCases;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
        monochrome = true, plugin = {
        "pretty",
        "html:target/cucumber-report/requiredTestCaseszonnic",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionab/cucumber.json",
        "rerun:target/regressionzonnicca-rerun.txt"},
        glue = "com.salmon.test",
        tags = "@failure"
)

public class RunRequiredTestCasesZonnicCASuite extends AbstractTestNGCucumberTests {

}