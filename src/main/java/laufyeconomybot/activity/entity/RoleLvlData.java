package laufyeconomybot.activity.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "rolelvl")
public class RoleLvlData {


    private int lvl;
    @Id
    private String roleId;


    public RoleLvlData() {
    }

    public RoleLvlData(int lvl, String roleId) {
        this.lvl = lvl;
        this.roleId = roleId;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RoleLvlData{" +
                "lvl=" + lvl +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
