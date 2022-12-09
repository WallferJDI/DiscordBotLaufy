package laufyeconomybot.activity.service;



import com.wallferjdi.laufyeconomybot.activity.entity.UserData;
import net.dv8tion.jda.api.entities.User;

import java.util.List;

public interface UserService {

    List<UserData> getAllUsers();

    void addUser(User user);
    UserData getUserInfo(User user);

    void deleteUser(String id);
    void setUserMessages(String id,long messagesCount);
    void setUserExp(String id,int expPoint);
    void setUserLvl(String id,int lvl);
}
