package pageObject.pageSteps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.Then;

import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;
import static pageObject.pageElements.ProjectPageElements.*;

public class ProjectPageSteps {
    @Then("^Открыть проект ([^\"]*)$")
    public static void openProject(String projectName) {
        buttonProject.shouldBe(Condition.enabled).click();
        buttonAllProject.shouldBe(Condition.visible).click();
        searchProject.setValue(projectName);
        searchProject.pressEnter();
        linkProject.shouldHave(Condition.exactText(projectName)).click();
    }

    @Then("^Проверить количество задач в проекте$")
    public static void findTasksAmount() {
        tasksList.shouldBe(Condition.enabled).click();
        String text = tasksAmount.getText();
        String amount = text.substring(text.lastIndexOf("з") + 1);
        assertNotNull(amount);
    }

    @Then("^Найти и открыть задачу ([^\"]*)$")
    public static void searchTask(String task) {
        taskFilter.shouldBe(Condition.enabled).click();
        buttonAllTasks.click();
        searchField.shouldBe(Condition.empty).setValue(task);
        searchField.pressEnter();
    }

    @Then("^Проверить затронутую версию$")
    public static void checkVersion() {
        String version = taskVersion.getText();
        assertEquals("Version 2.0", version);
    }

    @Then("^Проверить статус задачи$")
    public static void checkStatus() {
        taskStatus.shouldBe(visible);
        String status = taskStatus.getText();
        assertEquals("СДЕЛАТЬ", status);
    }
}
