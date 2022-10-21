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

    private static Response response;
    private static String speciesMorty;
    private static String locationMorty;
    private static String idLastEpisode;
    private static String idLastCharacter;
    private static String speciesLastCharacter;
    private static String locationLastCharacter;

    @Given("^Отправить ([^\"]*) запрос на ([^\"]*) о персонаже ([^\"]*) и получить статус (\\d+)$")
    public static void sendRequestAboutCharacter(String requestMethod, String endpoint, String name, int expectedStatus) {

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(Configuration.getConfigurationValue("rickMortyUrl"));
        builder.setContentType("Content-type");
        builder.setAccept("application/json");
        builder.addQueryParam("name", name);
        builder.setBasePath(endpoint);

        requestSpecification = builder.log(LogDetail.METHOD).log(LogDetail.PARAMS).build();
        String method = requestMethod.toLowerCase();
        if (method.equals("get")) {
            response = given()
                    .when()
                    .get()
                    .then()
                    .log().all()
                    .extract().response();

            speciesMorty = response.body().jsonPath().get("results[0].species").toString();
            locationMorty = response.body().jsonPath().get("results[0].location.name").toString();
            int actualStatus = response.getStatusCode();
            assertEquals(expectedStatus, actualStatus);
        }
    }

    @Then("Получить информацию о последнем эпизоде")
    public static void getLastEpisode() {
        List<String> episodes = response.body().jsonPath().getList("results[0].episode");
        String urlLastEpisode = episodes.get(episodes.size() - 1);
        String[] str = urlLastEpisode.split("/");
        idLastEpisode = str[str.length - 1];
        System.out.println(idLastEpisode);
    }

    @Then("Получить последнего персонажа последнего эпизода")
    public static void getLastCharacterByEpisode() {
        //List<String> characters = given()
        Response response1 = given()
                .baseUri(Configuration.getConfigurationValue("rickMortyUrl"))
                .contentType("Content-type").accept("application/json")
                .when()
                .get("episode/" + idLastEpisode)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        List<String> allLastEpisodeCharacters = response1.body().jsonPath().getList("characters");
        String lastCharacter = allLastEpisodeCharacters.get(allLastEpisodeCharacters.size() - 1);
        String[] str = lastCharacter.split("/");
        idLastCharacter = str[str.length - 1];
        System.out.println(idLastCharacter);
    }

    @And("Получить информацию о последнем персонаже последнего эпизода")
    public static void getInfoLastCharacterById() {

        Response response2 = given()
                .baseUri(Configuration.getConfigurationValue("rickMortyUrl"))
                .contentType("Content-type").accept("application/json")
                .when()
                .get("character/" + idLastCharacter)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        speciesLastCharacter = response2.body().jsonPath().getJsonObject("species").toString();
        locationLastCharacter = response2.body().jsonPath().getJsonObject("location.name").toString();
    }

    @Then("Сравнить расу и местонахождение персонажей")
    public static void compareCharacters() {
        assertEquals(speciesMorty, speciesLastCharacter);
        assertNotEquals(locationMorty, locationLastCharacter);
    }
}
