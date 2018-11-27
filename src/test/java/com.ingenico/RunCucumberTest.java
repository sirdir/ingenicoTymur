package com.ingenico;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@CucumberOptions(plugin ={"pretty", "html:target/surefire-reports"})
@CucumberOptions(plugin ={"pretty"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}