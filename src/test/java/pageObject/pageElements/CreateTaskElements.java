package pageObject.pageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CreateTaskElements {

    public static SelenideElement buttonCreate = $x("//a[@id='create_link']");

    public static SelenideElement issueTypeField = $x("//div[@id='issuetype-single-select']//span");

    public static SelenideElement summaryField = $x("//input[@id='summary']");

    public static SelenideElement buttonText = $x("//li[@class='aui-nav-selected']//button[text()='Текст']");

    public static SelenideElement descriptionField = $x("//div[@id='description-wiki-edit']/child::textarea");

    public static SelenideElement priorityField = $x("//div[@id='priority-single-select']//span");

    public static SelenideElement assigneeField = $x("//input[@id='assignee-field']");

    public static SelenideElement buttonCreateTask = $x("//input[@id='create-issue-submit']");

    public static SelenideElement linkTask = $x("//a[@class='issue-created-key issue-link']");

    public static SelenideElement buttonInwork = $x("//span[text()='В работе']");

    public static SelenideElement statusFieldInwork = $x("//span[@id='status-val']//span[text()='В работе']");

    public static SelenideElement dropdownStatusTask = $x("//a[@id='opsbar-transitions_more']//span[@class='dropdown-text']");

    public static SelenideElement doneStatusTask = $x("//div[@class='aui-dropdown2-item-group']//span[text()='Выполнено']");

    public static SelenideElement statusFieldDone = $x("//span[@id='status-val']//span[text()='Готово']");
}
