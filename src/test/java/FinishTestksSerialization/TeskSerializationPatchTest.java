//В прошлом уроке тебе удалось написать запрос к ручке PATCH. JSON попадал в тело запроса в виде файла.
//Вспомни условие задачи: нужно обновить информацию о профиле PATCH-запросом на ручку api/users/me.
//В теле запроса нужно передать такой JSON:
//{
//  "name": "Анастасия",
//  "about": "Самый крутой исследователь"
//}
//
//Напиши такой же запрос, но в этот раз используй сериализацию.
//Сначала создай класс, который описывает входной JSON. Не забудь добавить геттеры и сеттеры:
//как ты знаешь, они не влияют на сериализацию, но лучше писать код сразу с ними.
//Сгенерировать геттеры и сеттеры поможет среда разработки: Generate → Getter and Setter.
//Можно скопировать код в IDEA, а потом обратно в тренажёр.
package FinishTestksSerialization;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class TeskSerializationPatchTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    //Patch-запрос на обновление данных пользователя, ручка /api/users/me
    //Тест с параметрами
    @Test
    public void updateProfile() {
        Profile profile  = new Profile("Анастасия", "Самый крутой исследователь"); // создай объект, который соответствует JSON
        Response response =
                given()
                        .header("Content-type", "application/json") // заполни header
                        .auth().oauth2("........token...........")
                        .body(profile) // заполни body
                        .when()
                        .patch("/api/users/me"); // отправь запрос на ручку
        System.out.println("Response Body: ");
        response.prettyPrint();
        System.out.println("Status Code: " + response.getStatusCode());
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(200);
    }

    //Patch-запрос на обновление данных пользователя, ручка /api/users/me
    //Тест без параметров
    @Test
    public void updateProfileWithNoArgsConstructor() {
        // Используем конструктор без параметров
        Profile profile = new Profile();
        // Задаем значения через сеттеры
        profile.setName("Анастасия");
        profile.setAbout("Самый крутой исследователь");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("......token..........")
                        .body(profile)
                        .when()
                        .patch("/api/users/me");
        // Выводим тело ответа на экран
        System.out.println("Response Body: ");
        response.prettyPrint();
        // Выводим статус код на экран
        System.out.println("Status Code: " + response.getStatusCode());
        // Проверка, что тело ответа содержит ожидаемое значение
        Profile updatedProfile = response.as(Profile.class);
        System.out.println("Updated 'about': " + updatedProfile.getAbout());
        // Проверка, что в ответе есть поле _id и статус код 200
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(200);
    }
}
