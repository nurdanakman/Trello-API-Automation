package com.example.trello;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class TrelloApiClient {
    private static final String BASE_URL = "https://api.trello.com/1";
    private final String apiKey;
    private final String token;
    private RequestSpecification requestSpecification;

    public TrelloApiClient() {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("TRELLO_API_KEY");
        this.token = dotenv.get("TRELLO_TOKEN");
    }

    public Response createBoard(String boardName) {
        return given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"" + boardName + "\"}")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .post(BASE_URL + "/boards/");
    }

    public Response createCard(String cardName, String boardId) {
        String listId = getListIdFromBoard(boardId);
        return given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"" + cardName + "\", \"idList\":\"" + listId + "\"}")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .post(BASE_URL + "/cards/");

    }

    public Response updateCard(String cardId, String newName) {
        return given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"" + newName + "\"}")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .put(BASE_URL + "/cards/" + cardId);
    }

    public Response deleteCard(String cardId) {
            return given()
                    .header("Content-Type", "application/json")
                    .queryParam("key", apiKey)
                    .queryParam("token", token)
                    .when()
                    .delete(BASE_URL + "/cards/" + cardId);

    }

    public Response deleteBoard(String boardId) {
        return given()
                .header("Content-Type", "application/json")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .delete(BASE_URL + "/boards/" + boardId);
    }

    public String getListIdFromBoard(String boardId) {
            Response response = given()
                    .queryParam("key", apiKey)
                    .queryParam("token", token)
                    .when()
                    .get(BASE_URL + "/boards/" + boardId + "/lists/")
                    .then()
                    .statusCode(200)
                    .extract().response();

            return response.jsonPath().getString("find { it.name == '" + "Yapılacaklar" + "' }.id");
    }

}
