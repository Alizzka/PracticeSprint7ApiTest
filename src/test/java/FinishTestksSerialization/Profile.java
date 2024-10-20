package FinishTestksSerialization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile {
    private String name;
    private String about;

    // конструктор со всеми параметрами
    public Profile(String name, String about) {
        this.name = name;
        this.about = about;
    }


    // конструктор без параметров
    public Profile() {
    }
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
}
