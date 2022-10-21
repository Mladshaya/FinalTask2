package api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "api.apiSteps",
        tags = "@rickAndMorty or @reqRes",
        plugin = {"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm", "json:target/cucumber.json", "html:test-output"}
)
public class RunnerApiTest {
    @BeforeClass
    public static void before() {
        RestAssured.filters(new AllureRestAssured());
    }
}
