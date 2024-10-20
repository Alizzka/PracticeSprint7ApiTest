package LessonsTesks;

import io.restassured.response.Response;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;

// импортируем RestAssured
import static io.restassured.RestAssured.*;
// импортируем Before
import org.junit.Before;
// импортируем Test
import org.junit.Test;
// дополнительный статический импорт нужен, чтобы использовать given(), get() и then()
import java.util.concurrent.TimeUnit;


public class ApiMestoGetRequestTest {

    private WebDriver driver;

    // аннотация Before показывает, что метод будет выполняться перед каждым тестовым методом
    @Before
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage"); // Убрали "--headless" для отображения UI
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://qa-mesto.praktikum-services.ru");

        // повторяющуюся для разных ручек часть URL лучше записать в переменную в методе Before
        // если в классе будет несколько тестов, указывать её придётся только один раз
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    // Get запрос на получение кода ответа зарегистрированного пользоватиеля, ручка /api/users/me
    @Test
    public void getMyInfoStatusCode() {
        // метод given() помогает сформировать запрос
        given()
                // указываем протокол и данные авторизации
                .auth().oauth2(".......token.......")
                // отправляем GET-запрос с помощью метода get, недостающую часть URL (ручку) передаём в него в качестве параметра
                .get("/api/users/me")
                // проверяем, что статус-код ответа равен 200
                .then().statusCode(200);

        System.out.println("Тест getMyInfoStatusCode прошёл успешно. Статус-код ответа: 200");
    }

    //Get запрос на получение имени зарегистрированного пользователя, ручка /api/users/me
    @Test
    public void checkUserName() {
        given()
                .auth().oauth2(".......token.......")
                .get("/api/users/me")
                .then().assertThat().body("data.name", equalTo("Анастасия"));

        System.out.println("Тест checkUserName прошёл успешно. Имя пользователя совпадает: Анастасия");
    }

    //Get запрос на получение имени зарегистрированного пользователя, ручка /api/users/me
    // Выводим тело ответа в консоль
    @Test
    public void checkUserNameAndPrintResponseBody() {
        // отправляет запрос и сохраняет ответ в переменную response, экземпляр класса Response
        Response response = given().auth().oauth2(".......token.......").get("/api/users/me");
        // проверяет, что в теле ответа ключу name соответствует нужное имя пользователя
        response.then().assertThat().body("data.name",equalTo("Анастасия"));
        // Выводим полный ответ в консоль
        response.prettyPrint();
        // выводит тело ответа на экран
        System.out.println(response.body().asString());

    }

    //Get запрос на получение кода ответа для всех карточек мест, ручка /api/cards
    @Test
    public void checkCardsStatusCode() {
        // проверяем статус-код ответа на запрос «Получение всех карточек»
        given()
                .auth().oauth2(".......token.......")
                .get("/api/cards")
                .then().statusCode(200);
        System.out.println("Статус-код ответа: 200");
    }

    //Get запрос на получение занятия зарегистрированного пользователя по имени, ручка /api/users/me
    //Выводим тело ответа в консоль
    @Test
    public void checkUserActivityAndPrintResponseBody() {
        // отправляет запрос и сохраняет ответ в переменную response, экзмепляр класса Response
        Response response = given().auth().oauth2(".......token.......").get("/api/users/me");
        // проверяет, что в теле ответа ключу about соответствует нужное занятие
        response.then().assertThat().body("data.name",equalTo("Анастасия"));
        // Выводим полный ответ в консоль
        response.prettyPrint();
        // выводит тело ответа на экран
        System.out.println(response.body().asString());
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}