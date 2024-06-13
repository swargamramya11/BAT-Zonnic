package com.salmon.test.runners.prod;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
        monochrome = true, plugin = {
        "pretty",
        "html:target/cucumber-report/regressionzonnicLive",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/regressionsk/cucumber.json",
        "rerun:target/regressionzonnicca-rerun.txt"},
        glue = "com.salmon.test",
        tags = "@zonnicCALive or @skLive"
)

public class RunSKLiveZonnicCASuite extends AbstractTestNGCucumberTests {

}