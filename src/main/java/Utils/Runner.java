package Utils;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:reports/cucumber-html-report",
                "json:reports/cucumber.json",
                "pretty"},
        tags = {"@runit,@Run","~@ignore"},
        features = {"src/test/java/FeatureFiles"},
        glue = {"StepDefinitions", "Utils"}
)

public class Runner {
    }

