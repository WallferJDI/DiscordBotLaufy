package laufyeconomybot.activity.repository;

import com.wallferjdi.laufyeconomybot.activity.entity.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserData, String> {

}
