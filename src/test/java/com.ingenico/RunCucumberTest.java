package com.ingenico;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(plugin ={"pretty" , "html:target/surefire-reports"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}