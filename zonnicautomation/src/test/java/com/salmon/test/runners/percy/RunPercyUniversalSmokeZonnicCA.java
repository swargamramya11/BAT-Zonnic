package com.salmon.test.runners.percy;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
tags = {"@zonnicCAPercy"}, monochrome = true, plugin = {
        "pretty", 
        "html:target/cucumber-report/smokeZonnicCA",
        "junit:target/cucumber-report/cucumber.xml",
        "json:target/cucumber-report/smokeZonnicCA/cucumber.json",
        "rerun:target/smokeZonnicCA-rerun.txt"},
        glue = "com.salmon.test")
public class RunPercyUniversalSmokeZonnicCA extends AbstractTestNGCucumberTests {
	
}
