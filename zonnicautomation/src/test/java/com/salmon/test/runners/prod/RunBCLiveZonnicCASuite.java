package com.salmon.test.runners.prod;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
        tags = {"@bcLive"}, monochrome = true, plugin = {
        "pretty",
        "html:target/cucumber-report/smokevypesmokeuser",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokevypesmokeuser/cucumber.json",
        "rerun:target/smokevypesmokeuser-rerun.txt"},
        glue = "com.salmon.test")

public class RunBCLiveZonnicCASuite extends AbstractTestNGCucumberTests {

}