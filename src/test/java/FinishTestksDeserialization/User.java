package FinishTestksDeserialization;

public class User {
    private Data data; // Поле типа Data, соответствующее JSON-объекту
    // Геттер для поля data
    public Data getData() {
        return data;
    }
    // Сеттер для поля data
    public void setData(Data data) {
        this.data = data;
    }
}