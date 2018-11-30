package com.ingenico;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(plugin = {"pretty" , "html:target/cucumber"}, glue = "com.ingenico.step_defs")
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}