package LessonsTesks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiMestoPatchRequestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    // PATCH-запрос, ручка /api/cards (нужна актуальная ручка!)
    //Обновляет данные места через файл и выводим тело ответа и код ответа на экран:
    /*
    @Test
    public void createNewPlaceAndCheckResponse3(){
        //Обновляет данные нового места:
        File json = new File("src/test/resources/newCard2.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(".......token.......")
                        .and()
                        .body(json)
                        .when()
                        .patch("/api/cards");
        /*response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
        // Выводим полный ответ в консоль
        response.prettyPrint();
        // Проверяем статус код и тело ответа
        response.then().assertThat().statusCode(201)
                .and()
                .body("data._id", notNullValue());
    }*/

    // PATCH-запрос на ручку /api/users/me
    //Обновляет данные имени и занятия пользователя через файл и выводим тело и код ответа на экран:
    @Test
    public void updateProfileAndCheckStatusCode() {
        File json = new File("src/test/resources/updateProfile.json"); // запиши файл в файловую переменную
        Response response =
                given()
                        .header("Content-type", "application/json") // заполни header
                        .auth().oauth2(".......token.......")
                        .and()
                        .body(json) // заполни body
                        .when()
                        .patch("/api/users/me"); // отправь запрос на ручку
        // Выводим полный ответ в консоль
        System.out.println("Response Body: ");
        response.prettyPrint();
        System.out.println("Status Code: " + response.getStatusCode());
        response.then().assertThat()
                .statusCode(200) // проверь статус ответа
                .body("data.name", equalTo("Анастасия")); // проверь поле name
    }
}

