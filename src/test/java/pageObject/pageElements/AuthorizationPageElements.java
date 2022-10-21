package pageObject.pageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationPageElements {

    public static SelenideElement loginLane = $x("//input[@id='login-form-username']");

    public static SelenideElement passwordLane = $x("//input[@id='login-form-password']");

    public static SelenideElement buttonLogin = $x("//input[@id='login']");
}
