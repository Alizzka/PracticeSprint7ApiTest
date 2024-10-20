package LessonsTesks.toJsonAndFromJsonMethods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class UserTest {

    private Gson gson; // Создаем экземпляр Gson


    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
        gson = new GsonBuilder().setPrettyPrinting().create(); // Инициализация gson
    }

//Методы toJson

    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с параметрами геттеров и сеттеров класса Data
    //Как сериализовать объект: метод toJson
    @Test
    public void testUserToJson() {
        // Отправляем GET запрос для получения данных пользователя с авторизацией
        Response response = RestAssured.given()
                .auth().oauth2("......token..........")
                .when()
                .get("/api/users/me");

        // Проверяем код ответа
        assertThat(response.getStatusCode(), is(200));

        // Проверяем тело ответа перед десериализацией
        System.out.println("Response body: " + response.getBody().asString());

        // Десериализуем ответ в объект ApiResponse
        ApiResponse apiResponse = response.getBody().as(ApiResponse.class);

        // Получаем объект Data из ApiResponse
        Data data = apiResponse.getData();

        // Проверяем начальные значения через геттеры
        assertThat(data.getName(), is("Анастасия"));
        assertThat(data.getAbout(), is("Самый крутой исследователь"));
        assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        assertThat(data.getEmail(), is("aaaaaaa@mail.ru"));
        assertThat(data.get_id(), is("777"));

        // Используем сеттеры для изменения значений
        data.setName("Новое Имя");
        data.setAbout("Самый крутой тестировщик");
        data.setAvatar("https://example.com/new_avatar.jpg");
        data.setEmail("newemail@example.com");
        data.set_id("new_id");

        // Проверяем, что значения изменены через геттеры
        assertThat(data.getName(), is("Новое Имя"));
        assertThat(data.getAbout(), is("Самый крутой тестировщик"));
        assertThat(data.getAvatar(), is("https://example.com/new_avatar.jpg"));
        assertThat(data.getEmail(), is("newemail@example.com"));
        assertThat(data.get_id(), is("new_id"));

        // Создаем объект User с обновленными данными
        User user = new User(data);

        // Сериализуем объект User в JSON
        String json = user.toJson();

        // Ожидаемый результат
        String expectedJson = "{\"data\":{\"name\":\"Новое Имя\",\"about\":\"Самый крутой тестировщик\",\"avatar\":\"https://example.com/new_avatar.jpg\",\"email\":\"newemail@example.com\",\"_id\":\"new_id\"}}";

        // Выводим сериализованный JSON и ожидаемый JSON на экран
        System.out.println("Serialized JSON: " + json);
        System.out.println("Expected JSON: " + expectedJson);

        // Проверяем, что сериализованный JSON совпадает с ожидаемым
        assertThat(json, is(expectedJson));
    }

    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с конструктором Data без параметров
    //Как сериализовать объект: метод toJson
    @Test
    public void testUserToJson2() {
        // Отправляем GET запрос для получения данных пользователя с авторизацией
        Response response = RestAssured.given()
                .auth().oauth2(".......token.......")
                .when()
                .get("/api/users/me");

        // Проверяем код ответа
        assertThat(response.getStatusCode(), is(200));

        // Проверяем тело ответа перед десериализацией
        System.out.println("Response body: " + response.getBody().asString());

        // Десериализуем ответ в объект ApiResponse
        ApiResponse apiResponse = response.getBody().as(ApiResponse.class);

        // Получаем объект Data из ApiResponse
        Data data = apiResponse.getData();

        // Проверяем начальные значения через геттеры
        assertThat(data.getName(), is("Анастасия"));
        assertThat(data.getAbout(), is("Самый крутой исследователь"));
        assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        assertThat(data.getEmail(), is("aaaaaaa@mail.ru"));
        assertThat(data.get_id(), is("777"));

        // Используем сеттеры для изменения значений
        data.setName("Новое Имя");
        data.setAbout("Самый крутой тестировщик");
        data.setAvatar("https://example.com/new_avatar.jpg");
        data.setEmail("newemail@example.com");
        data.set_id("new_id");

        // Проверяем, что значения изменены через геттеры
        assertThat(data.getName(), is("Новое Имя"));
        assertThat(data.getAbout(), is("Самый крутой тестировщик"));
        assertThat(data.getAvatar(), is("https://example.com/new_avatar.jpg"));
        assertThat(data.getEmail(), is("newemail@example.com"));
        assertThat(data.get_id(), is("new_id"));

        // Создаем объект User с обновленными данными
        User user = new User(data);

        // Сериализуем объект User в JSON
        String json = user.toJson();

        // Ожидаемый результат
        String expectedJson = "{\"data\":{\"name\":\"Новое Имя\",\"about\":\"Самый крутой тестировщик\",\"avatar\":\"https://example.com/new_avatar.jpg\",\"email\":\"newemail@example.com\",\"_id\":\"new_id\"}}";

        // Выводим сериализованный JSON и ожидаемый JSON на экран
        System.out.println("Serialized JSON: " + json);
        System.out.println("Expected JSON: " + expectedJson);

        // Проверяем, что сериализованный JSON совпадает с ожидаемым
        assertThat(json, is(expectedJson));

        // Создаем объект Data с помощью конструктора без параметров
        Data defaultData = new Data();

        // Проверяем значения по умолчанию
        assertThat(defaultData.getName(), is("Имя по умолчанию"));
        assertThat(defaultData.getAbout(), is("Информация по умолчанию"));
        assertThat(defaultData.getAvatar(), is("https://example.com/default_avatar.jpg"));
        assertThat(defaultData.getEmail(), is("default@example.com"));
        assertThat(defaultData.get_id(), is("default_id"));
    }

    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с конструктором Data с параметрами
    //Как сериализовать объект: метод toJson
    @Test
    public void testUserToJson3() {
        // Отправляем GET запрос для получения данных пользователя с авторизацией
        Response response = RestAssured.given()
                .auth().oauth2(".......token.......")
                .when()
                .get("/api/users/me");

        // Проверяем код ответа
        assertThat(response.getStatusCode(), is(200));

        // Проверяем тело ответа перед десериализацией
        System.out.println("Response body: " + response.getBody().asString());

        // Десериализуем ответ в объект ApiResponse
        ApiResponse apiResponse = response.getBody().as(ApiResponse.class);

        // Получаем объект Data из ApiResponse
        Data data = apiResponse.getData();

        // Проверяем начальные значения через геттеры
        assertThat(data.getName(), is("Анастасия"));
        assertThat(data.getAbout(), is("Самый крутой исследователь"));
        assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        assertThat(data.getEmail(), is("aaaaaaa@mail.ru"));
        assertThat(data.get_id(), is("777"));

        // Создаем новый объект Data с использованием конструктора
        Data newData = new Data(
                "Новое Имя",
                "Самый крутой тестировщик",
                "https://example.com/new_avatar.jpg",
                "newemail@example.com",
                "new_id"
        );

        // Проверяем значения через геттеры
        assertThat(newData.getName(), is("Новое Имя"));
        assertThat(newData.getAbout(), is("Самый крутой тестировщик"));
        assertThat(newData.getAvatar(), is("https://example.com/new_avatar.jpg"));
        assertThat(newData.getEmail(), is("newemail@example.com"));
        assertThat(newData.get_id(), is("new_id"));

        // Создаем объект User с новыми данными
        User user = new User(newData);

        // Сериализуем объект User в JSON
        String json = user.toJson();

        // Ожидаемый результат
        String expectedJson = "{\"data\":{\"name\":\"Новое Имя\",\"about\":\"Самый крутой тестировщик\",\"avatar\":\"https://example.com/new_avatar.jpg\",\"email\":\"newemail@example.com\",\"_id\":\"new_id\"}}";

        // Проверяем, что сериализованный JSON совпадает с ожидаемым
        assertThat(json, is(expectedJson));
    }


    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с параметрами геттеров и сеттеров класса Data
    //Как сериализовать объект: метод toJson
    //Получить читаемую строку с JSON: разделенную пробелами и переносами строк, конструкция GsonBuilder()
    @Test
    public void testUserToJson4() {
        // Отправляем GET запрос для получения данных пользователя с авторизацией
        Response response = RestAssured.given()
                .auth().oauth2(".......token.......")
                .when()
                .get("/api/users/me");

        // Проверяем код ответа
        assertThat(response.getStatusCode(), is(200));

        // Проверяем тело ответа перед десериализацией
        System.out.println("Response body: " + response.getBody().asString());

        // Десериализуем ответ в объект ApiResponse
        ApiResponse apiResponse = response.getBody().as(ApiResponse.class);

        // Получаем объект Data из ApiResponse
        Data data = apiResponse.getData();

        // Проверяем начальные значения через геттеры
        assertThat(data.getName(), is("Анастасия"));
        assertThat(data.getAbout(), is("Самый крутой исследователь"));
        assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        assertThat(data.getEmail(), is("aaaaaaa@mail.ru"));
        assertThat(data.get_id(), is("777"));

        // Используем сеттеры для изменения значений
        data.setName("Новое Имя");
        data.setAbout("Самый крутой тестировщик");
        data.setAvatar("https://example.com/new_avatar.jpg");
        data.setEmail("newemail@example.com");
        data.set_id("new_id");

        // Проверяем, что значения изменены через геттеры
        assertThat(data.getName(), is("Новое Имя"));
        assertThat(data.getAbout(), is("Самый крутой тестировщик"));
        assertThat(data.getAvatar(), is("https://example.com/new_avatar.jpg"));
        assertThat(data.getEmail(), is("newemail@example.com"));
        assertThat(data.get_id(), is("new_id"));

        // Создаем объект User с обновленными данными
        User user = new User(data);

        // Сериализуем объект User в форматированный JSON
        String json = gson.toJson(user); // Используем Gson для сериализации

        // Ожидаемый результат
        String expectedJson = "{\n" +
                "  \"data\": {\n" +
                "    \"name\": \"Новое Имя\",\n" +
                "    \"about\": \"Самый крутой тестировщик\",\n" +
                "    \"avatar\": \"https://example.com/new_avatar.jpg\",\n" +
                "    \"email\": \"newemail@example.com\",\n" +
                "    \"_id\": \"new_id\"\n" +
                "  }\n" +
                "}";

        // Выводим сериализованный JSON и ожидаемый JSON на экран
        System.out.println("Serialized JSON: " + json);
        System.out.println("Expected JSON: " + expectedJson);

        // Проверяем, что сериализованный JSON совпадает с ожидаемым
        assertThat(json, is(expectedJson));
    }

    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с конструктором Data с параметрами
    //Как сериализовать объект: метод toJson
    //Получить читаемую строку с JSON: разделенную пробелами и переносами строк, конструкция GsonBuilder()
    @Test
    public void testUserToJson5() {
        // Отправляем GET запрос для получения данных пользователя с авторизацией
        Response response = RestAssured.given()
                .auth().oauth2(".......token.......")
                .when()
                .get("/api/users/me");

        // Проверяем код ответа
        assertThat(response.getStatusCode(), is(200));

        // Проверяем тело ответа перед десериализацией
        System.out.println("Response body: " + response.getBody().asString());

        // Получаем данные из JSON вручную
        String name = "Анастасия";
        String about = "Самый крутой исследователь";
        String avatar = "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg";
        String email = "aaaaaaa@mail.ru";
        String id = "777";

        // Создаем объект Data с использованием конструктора с параметрами
        Data data = new Data(name, about, avatar, email, id);

        // Проверяем начальные значения через геттеры
        assertThat(data.getName(), is(name));
        assertThat(data.getAbout(), is(about));
        assertThat(data.getAvatar(), is(avatar));
        assertThat(data.getEmail(), is(email));
        assertThat(data.get_id(), is(id));

        // Используем сеттеры для изменения значений
        data.setName("Новое Имя");
        data.setAbout("Самый крутой тестировщик");
        data.setAvatar("https://example.com/new_avatar.jpg");
        data.setEmail("newemail@example.com");
        data.set_id("new_id");

        // Проверяем, что значения изменены через геттеры
        assertThat(data.getName(), is("Новое Имя"));
        assertThat(data.getAbout(), is("Самый крутой тестировщик"));
        assertThat(data.getAvatar(), is("https://example.com/new_avatar.jpg"));
        assertThat(data.getEmail(), is("newemail@example.com"));
        assertThat(data.get_id(), is("new_id"));

        // Создаем объект User с обновленными данными
        User user = new User(data);

        // Сериализуем объект User в форматированный JSON
        String json = gson.toJson(user); // Используем Gson для сериализации

        // Ожидаемый результат
        String expectedJson = "{\n" +
                "  \"data\": {\n" +
                "    \"name\": \"Новое Имя\",\n" +
                "    \"about\": \"Самый крутой тестировщик\",\n" +
                "    \"avatar\": \"https://example.com/new_avatar.jpg\",\n" +
                "    \"email\": \"newemail@example.com\",\n" +
                "    \"_id\": \"new_id\"\n" +
                "  }\n" +
                "}";

        // Выводим сериализованный JSON и ожидаемый JSON на экран
        System.out.println("Serialized JSON: " + json);
        System.out.println("Expected JSON: " + expectedJson);

        // Проверяем, что сериализованный JSON совпадает с ожидаемым
        assertThat(json, is(expectedJson));
    }

    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с конструктором Data без параметров
    //Как сериализовать объект: метод toJson
    //Получить читаемую строку с JSON: разделенную пробелами и переносами строк, конструкция GsonBuilder()
    @Test
    public void testUserToJson6() {
        try {
            // Отправляем GET запрос для получения данных пользователя с авторизацией
            Response response = RestAssured.given()
                    .auth().oauth2(".......token.......")
                    .when()
                    .get("/api/users/me");

            // Проверяем код ответа
            assertThat(response.getStatusCode(), is(200));

            // Проверяем тело ответа перед десериализацией
            System.out.println("Response body: " + response.getBody().asString());

            // Создаем объект Data с конструктором без параметров
            Data data = new Data();

            // Проверяем начальные значения через геттеры
            assertThat(data.getName(), is("Имя по умолчанию"));
            assertThat(data.getAbout(), is("Информация по умолчанию"));
            assertThat(data.getAvatar(), is("https://example.com/default_avatar.jpg"));
            assertThat(data.getEmail(), is("default@example.com"));
            assertThat(data.get_id(), is("default_id"));

            // Устанавливаем новые значения с помощью сеттеров
            data.setName("Анастасия");
            data.setAbout("Самый крутой исследователь");
            data.setAvatar("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg");
            data.setEmail("aaaaaaa@mail.ru");
            data.set_id("777");

            // Проверяем значения через геттеры после установки
            assertThat(data.getName(), is("Анастасия"));
            assertThat(data.getAbout(), is("Самый крутой исследователь"));
            assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
            assertThat(data.getEmail(), is("aaaaaaa@mail.ru"));
            assertThat(data.get_id(), is("777"));

            // Создаем объект User с данными
            User user = new User(data);

            // Создаем Gson с форматированием
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Сериализуем объект User в форматированный JSON
            String json = gson.toJson(user); // Используем Gson для сериализации с форматированием

            // Ожидаемый результат
            String expectedJson = "{\n" +
                    "  \"data\": {\n" +
                    "    \"name\": \"Анастасия\",\n" +
                    "    \"about\": \"Самый крутой исследователь\",\n" +
                    "    \"avatar\": \"https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg\",\n" +
                    "    \"email\": \"aaaaaaa@mail.ru\",\n" +
                    "    \"_id\": \"777\"\n" +
                    "  }\n" +
                    "}";

            // Выводим сериализованный JSON и ожидаемый JSON на экран
            System.out.println("Serialized JSON: " + json);
            System.out.println("Expected JSON: " + expectedJson);

            // Проверяем, что сериализованный JSON совпадает с ожидаемым
            assertThat(json, is(expectedJson));

        } catch (Exception e) {
            e.printStackTrace();
            fail("An error occurred: " + e.getMessage());
        }
    }


//Методы fromJson

    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с параметрами геттеров и сеттеров класса Data
    //Как сериализовать объект: метод fromJson
    @Test
    public void testUserToJson7() {
        // Отправляем GET запрос для получения данных пользователя с авторизацией
        Response response = RestAssured.given()
                .auth().oauth2(".......token.......")
                .when()
                .get("/api/users/me");

        // Проверяем код ответа
        assertThat(response.getStatusCode(), is(200));

        // Проверяем тело ответа перед десериализацией
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Десериализуем ответ в объект User с использованием fromJson
        User user = User.fromJson(responseBody);

        // Получаем объект Data из User
        Data data = user.getData();

        // Проверяем начальные значения через геттеры
        assertThat(data.getName(), is("Анастасия"));
        assertThat(data.getAbout(), is("Самый крутой исследователь"));
        assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        assertThat(data.getEmail(), is("aaaaaaa@mail.ru"));
        assertThat(data.get_id(), is("777"));

        // Используем сеттеры для изменения значений
        data.setName("Новое Имя");
        data.setAbout("Самый крутой тестировщик");
        data.setAvatar("https://example.com/new_avatar.jpg");
        data.setEmail("newemail@example.com");
        data.set_id("new_id");

        // Проверяем, что значения изменены через геттеры
        assertThat(data.getName(), is("Новое Имя"));
        assertThat(data.getAbout(), is("Самый крутой тестировщик"));
        assertThat(data.getAvatar(), is("https://example.com/new_avatar.jpg"));
        assertThat(data.getEmail(), is("newemail@example.com"));
        assertThat(data.get_id(), is("new_id"));

        // Сериализуем объект User в JSON
        String json = user.toJson();

        // Ожидаемый результат
        String expectedJson = "{\"data\":{\"name\":\"Новое Имя\",\"about\":\"Самый крутой тестировщик\",\"avatar\":\"https://example.com/new_avatar.jpg\",\"email\":\"newemail@example.com\",\"_id\":\"new_id\"}}";

        // Выводим сериализованный JSON и ожидаемый JSON на экран
        System.out.println("Serialized JSON: " + json);
        System.out.println("Expected JSON: " + expectedJson);

        // Проверяем, что сериализованный JSON совпадает с ожидаемым
        assertThat(json, is(expectedJson));
    }

    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с конструктором Data без параметров
    //Как сериализовать объект: метод fromJson
    @Test
    public void testUserToJson8() {
        // Отправляем GET запрос для получения данных пользователя с авторизацией
        Response response = RestAssured.given()
                .auth().oauth2(".......token.......")
                .when()
                .get("/api/users/me");

        // Проверяем код ответа
        assertThat(response.getStatusCode(), is(200));

        // Получаем тело ответа в виде строки
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Используем метод fromJson для десериализации тела ответа в объект User
        User user = User.fromJson(responseBody);

        // Получаем объект Data из User
        Data data = user.getData();

        // Проверяем начальные значения через геттеры
        assertThat(data.getName(), is("Анастасия"));
        assertThat(data.getAbout(), is("Самый крутой исследователь"));
        assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        assertThat(data.getEmail(), is("aaaaaaa@mail.ru"));
        assertThat(data.get_id(), is("777"));

        // Используем сеттеры для изменения значений
        data.setName("Новое Имя");
        data.setAbout("Самый крутой тестировщик");
        data.setAvatar("https://example.com/new_avatar.jpg");
        data.setEmail("newemail@example.com");
        data.set_id("new_id");

        // Проверяем, что значения изменены через геттеры
        assertThat(data.getName(), is("Новое Имя"));
        assertThat(data.getAbout(), is("Самый крутой тестировщик"));
        assertThat(data.getAvatar(), is("https://example.com/new_avatar.jpg"));
        assertThat(data.getEmail(), is("newemail@example.com"));
        assertThat(data.get_id(), is("new_id"));

        // Сериализуем объект User в JSON
        String json = user.toJson();

        // Ожидаемый результат
        String expectedJson = "{\"data\":{\"name\":\"Новое Имя\",\"about\":\"Самый крутой тестировщик\",\"avatar\":\"https://example.com/new_avatar.jpg\",\"email\":\"newemail@example.com\",\"_id\":\"new_id\"}}";

        // Выводим сериализованный JSON и ожидаемый JSON на экран
        System.out.println("Serialized JSON: " + json);
        System.out.println("Expected JSON: " + expectedJson);

        // Проверяем, что сериализованный JSON совпадает с ожидаемым
        assertThat(json, is(expectedJson));

        // Создаем объект Data с помощью конструктора без параметров
        Data defaultData = new Data();

        // Проверяем значения по умолчанию (в зависимости от реализации конструктора без параметров)
        assertThat(defaultData.getName(), is("Имя по умолчанию"));
        assertThat(defaultData.getAbout(), is("Информация по умолчанию"));
        assertThat(defaultData.getAvatar(), is("https://example.com/default_avatar.jpg"));
        assertThat(defaultData.getEmail(), is("default@example.com"));
        assertThat(defaultData.get_id(), is("default_id"));
    }

    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с конструктором Data с параметрами
    //Как сериализовать объект: метод fromJson
    @Test
    public void testUserToJson9() {
        // Отправляем GET запрос для получения данных пользователя с авторизацией
        Response response = RestAssured.given()
                .auth().oauth2(".......token.......")
                .when()
                .get("/api/users/me");

        // Проверяем код ответа
        assertThat(response.getStatusCode(), is(200));

        // Получаем тело ответа в виде строки
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Десериализуем ответ с использованием метода fromJson
        User user = User.fromJson(responseBody);

        // Получаем объект Data из User
        Data data = user.getData();

        // Проверяем начальные значения через геттеры
        assertThat(data.getName(), is("Анастасия"));
        assertThat(data.getAbout(), is("Самый крутой исследователь"));
        assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        assertThat(data.getEmail(), is("aaaaaaa@mail.ru"));
        assertThat(data.get_id(), is("777"));

        // Создаем новый объект Data с использованием конструктора
        Data newData = new Data(
                "Новое Имя",
                "Самый крутой тестировщик",
                "https://example.com/new_avatar.jpg",
                "newemail@example.com",
                "new_id"
        );

        // Проверяем значения через геттеры
        assertThat(newData.getName(), is("Новое Имя"));
        assertThat(newData.getAbout(), is("Самый крутой тестировщик"));
        assertThat(newData.getAvatar(), is("https://example.com/new_avatar.jpg"));
        assertThat(newData.getEmail(), is("newemail@example.com"));
        assertThat(newData.get_id(), is("new_id"));

        // Создаем объект User с новыми данными
        User updatedUser = new User(newData);

        // Сериализуем объект User в JSON
        String json = updatedUser.toJson();

        // Ожидаемый результат
        String expectedJson = "{\"data\":{\"name\":\"Новое Имя\",\"about\":\"Самый крутой тестировщик\",\"avatar\":\"https://example.com/new_avatar.jpg\",\"email\":\"newemail@example.com\",\"_id\":\"new_id\"}}";

        // Выводим сериализованный JSON и ожидаемый JSON на экран
        System.out.println("Serialized JSON: " + json);
        System.out.println("Expected JSON: " + expectedJson);

        // Проверяем, что сериализованный JSON совпадает с ожидаемым
        assertThat(json, is(expectedJson));
    }

    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с параметрами геттеров и сеттеров класса Data
    //Как сериализовать объект: метод fromJson
    //Получить читаемую строку с JSON: разделенную пробелами и переносами строк, конструкция GsonBuilder()
    @Test
    public void testUserToJson10() {
        // Отправляем GET запрос для получения данных пользователя с авторизацией
        Response response = RestAssured.given()
                .auth().oauth2(".......token.......")
                .when()
                .get("/api/users/me");

        // Проверяем код ответа
        assertThat(response.getStatusCode(), is(200));

        // Получаем тело ответа в виде строки
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Десериализуем ответ с использованием метода fromJson
        User user = User.fromJson(responseBody);

        // Получаем объект Data из User
        Data data = user.getData();

        // Проверяем начальные значения через геттеры
        assertThat(data.getName(), is("Анастасия"));
        assertThat(data.getAbout(), is("Самый крутой исследователь"));
        assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        assertThat(data.getEmail(), is("aaaaaaaa@mail.ru"));
        assertThat(data.get_id(), is("777"));

        // Используем сеттеры для изменения значений
        data.setName("Новое Имя");
        data.setAbout("Самый крутой тестировщик");
        data.setAvatar("https://example.com/new_avatar.jpg");
        data.setEmail("newemail@example.com");
        data.set_id("new_id");

        // Проверяем, что значения изменены через геттеры
        assertThat(data.getName(), is("Новое Имя"));
        assertThat(data.getAbout(), is("Самый крутой тестировщик"));
        assertThat(data.getAvatar(), is("https://example.com/new_avatar.jpg"));
        assertThat(data.getEmail(), is("newemail@example.com"));
        assertThat(data.get_id(), is("new_id"));

        // Создаем объект User с обновленными данными
        User updatedUser = new User(data);

        // Используем GsonBuilder для создания форматированного JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(updatedUser);

        // Ожидаемый результат
        String expectedJson = "{\n" +
                "  \"data\": {\n" +
                "    \"name\": \"Новое Имя\",\n" +
                "    \"about\": \"Самый крутой тестировщик\",\n" +
                "    \"avatar\": \"https://example.com/new_avatar.jpg\",\n" +
                "    \"email\": \"newemail@example.com\",\n" +
                "    \"_id\": \"new_id\"\n" +
                "  }\n" +
                "}";

        // Выводим сериализованный JSON и ожидаемый JSON на экран
        System.out.println("Serialized JSON: " + json);
        System.out.println("Expected JSON: " + expectedJson);

        // Проверяем, что сериализованный JSON совпадает с ожидаемым
        assertThat(json, is(expectedJson));
    }

    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с конструктором Data с параметрами
    //Как сериализовать объект: метод fromJson
    //Получить читаемую строку с JSON: разделенную пробелами и переносами строк, конструкция GsonBuilder()
    @Test
    public void testUserToJson11() {
        // Отправляем GET запрос для получения данных пользователя с авторизацией
        Response response = RestAssured.given()
                .auth().oauth2(".......token.......")
                .when()
                .get("/api/users/me");

        // Проверяем код ответа
        assertThat(response.getStatusCode(), is(200));

        // Получаем тело ответа как строку
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Десериализуем ответ с использованием метода fromJson
        User user = User.fromJson(responseBody);

        // Получаем объект Data из User
        Data data = user.getData();

        // Проверяем начальные значения через геттеры
        assertThat(data.getName(), is("Анастасия"));
        assertThat(data.getAbout(), is("Самый крутой исследователь"));
        assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
        assertThat(data.getEmail(), is("aaaaaaa@mail.ru"));
        assertThat(data.get_id(), is("777"));

        // Создаем объект Data с использованием конструктора с параметрами
        Data updatedData = new Data("Новое Имя", "Самый крутой тестировщик", "https://example.com/new_avatar.jpg", "newemail@example.com", "new_id");

        // Проверяем значения нового объекта через геттеры
        assertThat(updatedData.getName(), is("Новое Имя"));
        assertThat(updatedData.getAbout(), is("Самый крутой тестировщик"));
        assertThat(updatedData.getAvatar(), is("https://example.com/new_avatar.jpg"));
        assertThat(updatedData.getEmail(), is("newemail@example.com"));
        assertThat(updatedData.get_id(), is("new_id"));

        // Создаем обертку для Data
        DataWrapper dataWrapper = new DataWrapper(updatedData);

        // Используем Gson с форматированием
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(dataWrapper); // Сериализуем DataWrapper

        // Ожидаемый результат
        String expectedJson = "{\n" +
                "  \"data\": {\n" +
                "    \"name\": \"Новое Имя\",\n" +
                "    \"about\": \"Самый крутой тестировщик\",\n" +
                "    \"avatar\": \"https://example.com/new_avatar.jpg\",\n" +
                "    \"email\": \"newemail@example.com\",\n" +
                "    \"_id\": \"new_id\"\n" +
                "  }\n" +
                "}";

        // Выводим сериализованный JSON и ожидаемый JSON на экран
        System.out.println("Serialized JSON: " + json);
        System.out.println("Expected JSON: " + expectedJson);

        // Проверяем, что сериализованный JSON совпадает с ожидаемым
        assertThat(json, is(expectedJson));
    }

    //Get-запрос на получение данных пользователя, ручка /api/users/me
    //Тест с конструктором Data без параметров
    //Как сериализовать объект: метод fromJson
    //Получить читаемую строку с JSON: разделенную пробелами и переносами строк, конструкция GsonBuilder()
    @Test
    public void testUserToJson12() {
        try {
            // Отправляем GET запрос для получения данных пользователя с авторизацией
            Response response = RestAssured.given()
                    .auth().oauth2(".......token.......")
                    .when()
                    .get("/api/users/me");

            // Проверяем код ответа
            assertThat(response.getStatusCode(), is(200));

            // Получаем тело ответа как строку
            String responseBody = response.getBody().asString();
            System.out.println("Response body: " + responseBody);

            // Десериализуем ответ в объект User с использованием метода fromJson
            Gson gson = new Gson();
            User user = gson.fromJson(responseBody, User.class);

            // Проверяем начальные значения через геттеры
            Data data = user.getData();
            assertThat(data.getName(), is("Анастасия"));
            assertThat(data.getAbout(), is("Самый крутой исследователь"));
            assertThat(data.getAvatar(), is("https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg"));
            assertThat(data.getEmail(), is("aaaaaaa@mail.ru"));
            assertThat(data.get_id(), is("777"));

            // Создаем новый объект Data с использованием конструктора без параметров
            Data newData = new Data(); // Теперь мы используем конструктор по умолчанию

            // Изменяем значения через сеттеры
            newData.setName("Новое Имя");
            newData.setAbout("Самый крутой тестировщик");
            newData.setAvatar("https://example.com/new_avatar.jpg");
            newData.setEmail("newemail@example.com");
            newData.set_id("new_id");

            // Проверяем, что значения изменены через геттеры
            assertThat(newData.getName(), is("Новое Имя"));
            assertThat(newData.getAbout(), is("Самый крутой тестировщик"));
            assertThat(newData.getAvatar(), is("https://example.com/new_avatar.jpg"));
            assertThat(newData.getEmail(), is("newemail@example.com"));
            assertThat(newData.get_id(), is("new_id"));

            // Создаем новый User с измененными данными
            user.setData(newData); // Устанавливаем новый объект Data в User

            // Сериализуем объект User в форматированный JSON с отступами
            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            String json = prettyGson.toJson(user); // Используем Gson для сериализации с измененными данными

            // Ожидаемый результат
            String expectedJson = "{\n" +
                    "  \"data\": {\n" +
                    "    \"name\": \"Новое Имя\",\n" +
                    "    \"about\": \"Самый крутой тестировщик\",\n" +
                    "    \"avatar\": \"https://example.com/new_avatar.jpg\",\n" +
                    "    \"email\": \"newemail@example.com\",\n" +
                    "    \"_id\": \"new_id\"\n" +
                    "  }\n" +
                    "}";

            // Выводим сериализованный JSON и ожидаемый JSON на экран
            System.out.println("Serialized JSON: " + json);
            System.out.println("Expected JSON: " + expectedJson);

            // Проверяем, что сериализованный JSON совпадает с ожидаемым
            assertThat(json, is(expectedJson));

        } catch (Exception e) {
            e.printStackTrace();
            fail("An error occurred: " + e.getMessage());
        }
    }
}

