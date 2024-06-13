package com.salmon.test.runners.regression;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
        monochrome = true, plugin = {
        "pretty",
        "html:target/cucumber-report/regressionzonnic",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionbc/cucumber.json",
        "rerun:target/regressionzonnicca-rerun.txt"},
        glue = "com.salmon.test",
        tags = "@zonnicCAReg or @bcReg"
)

public class RunBCRegressionZonnicCASuite extends AbstractTestNGCucumberTests {

}