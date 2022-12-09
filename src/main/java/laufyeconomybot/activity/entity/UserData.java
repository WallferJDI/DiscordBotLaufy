package laufyeconomybot.activity.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class UserData {

    @Id
    private String id;

    private String name;
    private long messages;
    private long voice;
    private int lvl;
    private int exp;

    public UserData() {
    }

    public UserData(String id, String name) {

        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "\n"+
                "User  info \n{" +
                ", Name='" + name + '\n' +
                "Id ='" + id + '\'' +
                ", Messages Count=" + messages +
                ", Voice time =" + voice +
                ", lvl=" + lvl +
                ", exp=" + exp +
                "} \n";
    }
}
