package pageObject.pageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPageElements {

    public static SelenideElement buttonProject = $x("//a[@id='browse_link']");

    public static SelenideElement buttonAllProject = $x("//a[@id='project_view_all_link_lnk']");

    public static SelenideElement searchProject = $x("//input[@id='project-filter-text']");

    public static SelenideElement linkProject = $x("//td[@class='cell-type-name']//a[@href='/browse/TEST']");

    public static SelenideElement tasksList = $x("//a[@href='/projects/TEST/issues']");

    public static SelenideElement tasksAmount = $x("//div[@class='showing']//child::span");

    public static SelenideElement taskFilter = $x("//button[@aria-label='Переключить фильтр']");

    public static SelenideElement buttonAllTasks = $x("//a[@class='aui-dropdown2-radio'] [text()='Все задачи']");

    public static SelenideElement searchField = $x("//input[@id='quickSearchInput']");

    public static SelenideElement taskVersion = $x("//span[text()='Version 2.0']");

    public static SelenideElement taskStatus = $x("//span[@class='value']//span");

}
