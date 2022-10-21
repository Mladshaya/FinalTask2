package api.apiSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import utils.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqResSteps {
    private JSONObject body;

    @When("^Считать данные из json-файла$")
    public void readFile() throws IOException {
        body = new JSONObject(new String(Files.readAllBytes(Paths.get(Configuration.getConfigurationValue("pathToJsonDirectory")))));
    }

    @Then("^Изменить данные в json-объекте$")
    public void changeObject() {
        body.put("name", "Tomato");
        body.put("job", "Eat maket");
    }

    @And("^Отправить запрос и проверяем корректность данных$")
    public void sendRequest() {
        RequestSpecification request = given();
        request

                .baseUri(Configuration.getConfigurationValue("reqResUrl"))
                .header("Content-type", "application/json");

        Response response = request
                .body(body.toString())
                .post("api/users")
                .then()
                .statusCode(201)
                .log().all()
                .extract().response();

        String expectedName = response.body().jsonPath().get("name").toString();
        String expectedJob = response.body().jsonPath().get("job").toString();
        assertEquals("Tomato", expectedName);
        assertEquals("Eat maket", expectedJob);
    }
}
