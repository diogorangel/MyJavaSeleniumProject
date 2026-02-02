package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/Login.feature",
    glue = {"steps", "hooks"},
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    plugin = {
        "pretty", 
        "html:evidence/cucumber-reports.html", 
        "json:evidence/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" 
    },
    tags = "@001_Login_StandardUser",
    monochrome = true
)
public class Login {
}