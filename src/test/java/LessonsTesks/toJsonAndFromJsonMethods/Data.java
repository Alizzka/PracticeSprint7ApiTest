package LessonsTesks.toJsonAndFromJsonMethods;

public class Data {
    private String name;
    private String about;
    private String avatar;
    private String email;
    private String _id;

    // конструктор со всеми параметрами
    public Data(String name, String about, String avatar, String email, String _id) {
        this.name = name;
        this.about = about;
        this.avatar = avatar;
        this.email = email;
        this._id = _id;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    // Конструктор без параметров
    public Data() {
        this.name = "Имя по умолчанию";
        this.about = "Информация по умолчанию";
        this.avatar = "https://example.com/default_avatar.jpg";
        this.email = "default@example.com";
        this._id = "default_id";
    }
}
