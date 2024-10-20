package LessonsTesks.toJsonAndFromJsonMethods;

import com.google.gson.Gson;

public class User {
    private Data data;

    public User(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    // Метод toJson для сериализации объекта в JSON
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Метод fromJson для десериализации JSON в объект
    public static User fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }
}
