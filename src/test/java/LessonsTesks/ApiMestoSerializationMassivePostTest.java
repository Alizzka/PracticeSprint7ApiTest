package LessonsTesks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiMestoSerializationMassivePostTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    //POST-запрос на ссоздание новых карточек мест массивом, ручка /api/cards (нужна актуальная ручка!)
    //Выводим тело и код ответа на экран
    /*
    @Test
    public void createMassiveofNewPlacesAndCheckResponse() {
        // создали пустой список
        List<Card> cards = new ArrayList<Card>();
        // добавили элементы
        cards.add(new Card("Место1", "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        cards.add(new Card("Место2", "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        cards.add(new Card("Место3", "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        Response response =
        given()
                .header("Content-type", "application/json")
                .auth().oauth2(".......token.......")
                .body(cards)
                .when()
                .post("/api/cards");
        System.out.println("Response Body: ");
        response.prettyPrint();
        System.out.println("Status Code: " + response.getStatusCode());
        // Проверяем, что в ответе есть массив data и каждый элемент массива содержит _id
        response.then().assertThat()
                .statusCode(201)
                .body("data._id", everyItem(notNullValue()));  // Проверка, что каждый элемент в массиве data содержит поле _id
    }*/

    //POST-запрос на ссоздание новых карточек мест массивом НО ПО ОДНОЙ, ручка /api/cards
    //Выводим тело и код ответа на экран
    @Test
    public void createMassiveofNewPlacesAndCheckResponse2() {
        // создаем список карточек
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("Место1", "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        cards.add(new Card("Место2", "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        cards.add(new Card("Место3", "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        for (Card card : cards) {
            Response response =
                    given()
                            .header("Content-type", "application/json")
                            .auth().oauth2(".......token.......")
                            .body(card)  // Отправляем каждую карточку отдельно
                            .when()
                            .post("/api/cards");
            System.out.println("Response Body: ");
            response.prettyPrint();
            System.out.println("Status Code: " + response.getStatusCode());
            response.then().assertThat().body("data._id", notNullValue())  // Проверяем наличие ID созданной карточки
                    .and()
                    .statusCode(201);
        }
    }
}


