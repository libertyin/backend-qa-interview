package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/weather.feature",
        glue = {"StepDefinitions"}
)

public class Run extends AbstractTestNGCucumberTests {
}
