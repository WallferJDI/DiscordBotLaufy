package laufyeconomybot.activity.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "ignoredroles")
public class RoleIgnoredData {

    @Id
    private String id;

    public RoleIgnoredData() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleIgnoredData(String id) {
        this.id = id;
    }
}
