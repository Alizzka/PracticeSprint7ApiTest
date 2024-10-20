package LessonsTesks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ApiMestoSerializationObjectPostTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    //POST-запрос на создание нового места через класс Card, ручка /api/cards,
    // Сериализация объекта с параметрами
    //Выводим тело и код ответа на экран
    @Test
    public void createNewPlaceAndCheckResponse2() {
        Card card = new Card("Интересное место", "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg");
        Response response =
        given()
                .header("Content-type", "application/json")
                .auth().oauth2(".......token.......")
                // сюда передали созданный объект с нужными значениями полей
                .body(card)
                .when()
                .post("/api/cards");
        System.out.println("Response Body: ");
        response.prettyPrint();
        System.out.println("Status Code: " + response.getStatusCode());
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
    }

    //POST-запрос на создание нового места через класс Card, ручка /api/cards,
    //Сериализация объекта без параметров
    //Выводим тело и код ответа на экран
    @Test
    public void createNewPlaceAndCheckResponseWithNoArgsConstructor() {
        // Создаем объект Card с использованием конструктора без параметров
        Card card = new Card();
        // Устанавливаем значения полей с помощью сеттеров
        card.setName("Новое место");
        card.setLink("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg");
        // Проверяем, что метод getLink() возвращает правильное значение
        String link = card.getLink();
        System.out.println("Ссылка на изображение: " + link);
        // Выполняем POST-запрос для создания нового места
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(".......token.......")
                        .body(card)  // Отправляем объект Card с заполненными полями
                        .when()
                        .post("/api/cards");
        // Выводим тело ответа и статус-код на экран
        System.out.println("Response Body: ");
        response.prettyPrint();
        System.out.println("Status Code: " + response.getStatusCode());
        // Проверяем, что ответ содержит ID созданного места и статус-код 201
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
    }
}
