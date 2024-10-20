package FinishTestksDeserialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TeskDeserializationGetTest {

    private static final String TOKEN = ".......";

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    //Get-запрос на обновление данных пользователя, ручка /api/users/me
    //Тест без параметров
    @Test
    public void checkUserName() {
        Response response = given()
                .auth().oauth2(TOKEN)
                .when()
                .get("/api/users/me");
        // Выводим тело ответа на экран
        System.out.println("Response Body: ");
        response.prettyPrint();
        // Выводим статус код на экран
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        // Десериализуем ответ в объект User
        User user = response
                .then()
                .statusCode(200) // проверяем, что статус ответа 200
                .extract()
                .body()
                .as(User.class);
        // Проверяем, что объект не null
        assertThat(user, notNullValue());
    }

    //Get-запросом на получение данных пользователся, ручка /api/users/me
    //Тест с параметрами
    @Test
    public void checkUserData() {
        // Отправляем GET запрос и получаем ответ
        Response response = given()
                .auth().oauth2(TOKEN)
                .when()
                .get("/api/users/me");
        // Выводим код ответа и тело ответа на экран
        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        // Десериализуем ответ в объект User
        User user = response
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(User.class);
        // Проверяем, что объект не равен null
        assertThat(user, notNullValue());
        // Получаем объект Data через геттер
        Data data = user.getData();
        assertThat(data, notNullValue());
        // Проверяем значения всех полей через геттеры
        MatcherAssert.assertThat(data.getName(), is("Анастасия"));
        MatcherAssert.assertThat(data.getAbout(), is("Самый крутой исследователь"));
        MatcherAssert.assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        MatcherAssert.assertThat(data.get_id(), notNullValue());
        MatcherAssert.assertThat(data.getEmail(), is("aaaaaaa@mail.ru"));

        // Используем сеттеры для изменения значений
        data.setName("Новое Имя");
        data.setAbout("Самый крутой тестировщик");
        data.setAvatar("https://example.com/new_avatar.jpg");
        data.setEmail("newemail@example.com");
        data.set_id("new_id");

        // Проверяем, что сеттеры изменили значения
        MatcherAssert.assertThat(data.getName(), is("Новое Имя"));
        MatcherAssert.assertThat(data.getAbout(), is("Самый крутой тестировщик"));
        MatcherAssert.assertThat(data.getAvatar(), is("https://example.com/new_avatar.jpg"));
        MatcherAssert.assertThat(data.getEmail(), is("newemail@example.com"));
        MatcherAssert.assertThat(data.get_id(), is("new_id"));

        // Используем сеттер в классе User для замены Data
        Data newData = new Data();
        newData.setName("Другое Имя");
        newData.setAbout("Другое описание");
        newData.setAvatar("https://example.com/another_avatar.jpg");
        newData.setEmail("anotheremail@example.com");
        newData.set_id("another_id");

        user.setData(newData); // Устанавливаем новые данные в User

        // Проверяем, что новые данные успешно установлены
        MatcherAssert.assertThat(user.getData().getName(), is("Другое Имя"));
        MatcherAssert.assertThat(user.getData().getAbout(), is("Другое описание"));
        MatcherAssert.assertThat(user.getData().getAvatar(), is("https://example.com/another_avatar.jpg"));
        MatcherAssert.assertThat(user.getData().getEmail(), is("anotheremail@example.com"));
        MatcherAssert.assertThat(user.getData().get_id(), is("another_id"));
    }
}

