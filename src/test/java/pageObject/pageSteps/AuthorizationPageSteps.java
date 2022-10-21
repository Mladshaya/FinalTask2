package pageObject.pageSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static pageObject.pageElements.AuthorizationPageElements.*;
import static utils.Configuration.getConfigurationValue;

public class AuthorizationPageSteps {

    @Given("Открываем главную страницу jira")
    public static void openUrl() {
        open(getConfigurationValue("jiraUrl"));
    }

    @And("Вводим логин и пароль")
    public static void authorization() {
        loginLane.shouldBe(visible).sendKeys(getConfigurationValue("login"));
        passwordLane.sendKeys(getConfigurationValue("password"));
        buttonLogin.shouldBe(enabled).click();
    }
}
