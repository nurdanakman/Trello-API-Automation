package com.example.trello;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrelloApiTests {
    private static TrelloApiClient trelloApiClient;
    private static String boardId;
    private static String firstCardId;
    private static String secondCardId;
    private static final Logger logger = LogManager.getLogger(TrelloApiTests.class);

    @BeforeAll
    static void setup() {
        trelloApiClient = new TrelloApiClient();
    }

    @Test
    @Order(1)
    @DisplayName("Create a new Trello Board")
    public void createBoard() {
        String boardName = "Testinium";
        Response response = trelloApiClient.createBoard(boardName);
        assertEquals(200, response.statusCode());
        boardId = response.jsonPath().getString("id");
        logger.info("A board is created. Board ID: " + boardId + " and " + boardName);
    }

    @Test
    @Order(2)
    @DisplayName("Create two cards on the board")
    public void createCards() {
        String firstCardName = "First Card";
        String secondCardName = "Second Card";
        firstCardId = trelloApiClient.createCard(firstCardName, boardId).jsonPath().getString("id");
        logger.info("A card is created. Card ID: " + firstCardId + " and " + firstCardName);
        secondCardId = trelloApiClient.createCard(secondCardName, boardId).jsonPath().getString("id");
        logger.info("A card is created. Card ID: " + secondCardId + " and " + secondCardName);
    }

    @Test
    @Order(3)
    @DisplayName("Update a random card")
    void updateRandomCard() {
        String newName = "Updated Random Card";
        String cardToUpdate = Math.random() < 0.5 ? firstCardId : secondCardId;
        Response response = trelloApiClient.updateCard(cardToUpdate, newName);
        assertEquals(200, response.statusCode());
        logger.info(cardToUpdate + "is updated and its new name is " + response.jsonPath().getString("name"));
    }

    @Test
    @Order(4)
    @DisplayName("Delete both cards")
    void deleteCards() {
        Response firstCardResponse = trelloApiClient.deleteCard(firstCardId);
        assertEquals(200, firstCardResponse.statusCode());
        logger.info("The card id: " + firstCardId + " is deleted");
        Response secondCardResponse = trelloApiClient.deleteCard(secondCardId);
        assertEquals(200, secondCardResponse.statusCode());
        logger.info("The card id: " + secondCardId + " is deleted");
    }

    @Test
    @Order(5)
    @DisplayName("Delete the board")
    void deleteBoard() {
        Response response = trelloApiClient.deleteBoard(boardId);
        assertEquals(200, response.statusCode());
        logger.info("Board is deleted");
    }


}
