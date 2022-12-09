package laufyeconomybot.activity.service;

import com.wallferjdi.laufyeconomybot.activity.entity.UserData;
import com.wallferjdi.laufyeconomybot.activity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{



    private final UserRepository repository;

    @Override
    public List<UserData> getAllUsers() {

        return repository.findAll();
    }

    @Override
    public void setUserExp(String id,int expPoint){
        UserData userData = repository.findById(id).get();
        userData.setExp(expPoint);
        repository.save(userData);
    }

    @Override
    public void setUserMessages(String id,long messagesCount){
        UserData userData = repository.findById(id).get();
        userData.setMessages(messagesCount);
        repository.save(userData);
    }


    @Override
    public void setUserLvl(String id,int lvlCount){
        UserData userData = repository.findById(id).get();
        userData.setLvl(lvlCount);
        repository.save(userData);
    }
    @Override
    public void addUser(User user) {
        String name = user.getName();
        name = name.replaceAll("[^\\x20-\\x7e]", " ");
        System.out.println(user.getName());
        repository.save(new UserData(user.getId(),name ));
    }
    public void addUser(UserData userData) {

        repository.save(userData);
    }
    public UserData getUserInfo(User user) {


       UserData userData;
        if (repository.findById(user.getId()).isPresent()){
            userData = repository.findById(user.getId()).get();
            return userData;
        }else {
            addUser(user);
            return repository.findById(user.getId()).get();
        }
    }

    @Override
    @Transactional
    public void deleteUser(String id) {
        try {
            repository.deleteById(id);
        }catch (NullPointerException exception){
            System.out.println(exception.getMessage());
        }

    }
}
