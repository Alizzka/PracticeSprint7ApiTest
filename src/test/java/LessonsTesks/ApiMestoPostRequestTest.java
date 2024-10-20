package LessonsTesks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiMestoPostRequestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    //POST-запрос на здание карточки нового места через файл, ручка /api/cards
    //Выводим тело и код ответа на экран
    @Test
    public void createNewPlaceAndCheckResponse(){
        File json = new File("src/test/resources/newCard.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(".......token.......")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/cards");
        // Выводим полный ответ в консоль
        System.out.println("Response Body: ");
        response.prettyPrint();
        System.out.println("Status Code: " + response.getStatusCode());
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
    }

    //POST-запрос на здание карточки нового места через строковую переменную, ручка /api/cards
    //Выводим тело и код ответа на экран
    @Test
    public void createNewPlaceAndCheckResponse2(){
        //Если JSON небольшой, его можно передать в тело запроса через строковую переменную, а не в файле
        //POST-запрос обычно добавляет данные
        String json = "{\"name\": \"Очень интересное место\", \"link\": \"https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(".......token.......")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/cards");
        // Выводим полный ответ в консоль
        System.out.println("Response Body: ");
        response.prettyPrint();
        System.out.println("Status Code: " + response.getStatusCode());
        response.then().assertThat().body("data._id", notNullValue())
                .statusCode(201);
    }
}

