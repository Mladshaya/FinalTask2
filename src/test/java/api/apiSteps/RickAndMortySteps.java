package api.apiSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import utils.Configuration;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RickAndMortySteps {


    private static final RequestSpecBuilder builder = new RequestSpecBuilder();
    private static Response response;
    private static String speciesMorty;
    private static String locationMorty;
    private static String lastEpisode;
    private static String speciesLastCharacter;
    private static String locationLastCharacter;


    @Given("^Установить базовый url и header$")
    public static void setBaseUrl() {
        builder.setBaseUri(Configuration.getConfigurationValue("rickMortyUrl"));
        builder.setContentType("Content-type");
        builder.setAccept("application/json");
    }

    @Given("^Передать параметры запроса для персонажа ([^\"]*)$")
    public static void getInfoCharacterByName(String name) {
        builder.addQueryParam("name", name);
    }

    @Given("^Установить эндпоинт ([^\"]*)$")
    public static void setEndpoint(String endpoint) {
        builder.setBasePath(endpoint);
    }


    @And("^Отправить ([^\"]*) запрос$")
    public static void sendRequestByMethod(String requestMethod) {

        requestSpecification = builder.log(LogDetail.METHOD).log(LogDetail.PARAMS).build();

        String method = requestMethod.toLowerCase();
        if (method.equals("get")) {
            response = given()
                    .when()
                    .get()
                    .then()
                    .log().all()
                    .extract().response();
        }
        builder.removeQueryParam("name");
    }

    @Then("^Проверить статус код (\\d+)$")
    public static void checkByStatus(int expectedStatus) {
        int actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);
    }

    @And("^Получить информацию о персонаже$")
    public static void getInfo() {
        speciesMorty = response.body().jsonPath().get("results[0].species").toString();
        locationMorty = response.body().jsonPath().get("results[0].location.name").toString();
    }

    @Then("^Получить информацию о последнем эпизоде$")
    public void getEpisode() {
        List<Object> episodes = response.body().jsonPath().getList("results[0].episode");
        lastEpisode = episodes.get(episodes.size() - 1).toString();
    }

    @And("^Получить информацию о последнем персонаже эпизода$")
    public void lastCharacterInfo() {

        List<String> characters = given()
                .expect().log().body()
                .when()
                .get(lastEpisode)
                .then().statusCode(200)
                .extract()
                .jsonPath().getList("characters");

        String lastCharacter = characters.get(characters.size() - 1);

        response = given()
                .when()
                .get(lastCharacter)
                .then()
                .log().all()
                .extract().response();

        speciesLastCharacter = response.body().jsonPath().getJsonObject("species").toString();
        locationLastCharacter = response.body().jsonPath().getJsonObject("location.name").toString();
    }

    @Then("^Сравнить расу и местонахождение персонажей$")
    public void compareCharacters() {
        assertEquals(speciesMorty, speciesLastCharacter);
        assertNotEquals(locationMorty, locationLastCharacter);
    }
}