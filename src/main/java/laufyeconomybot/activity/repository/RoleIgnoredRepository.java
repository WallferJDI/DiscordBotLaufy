package laufyeconomybot.activity.repository;

import com.wallferjdi.laufyeconomybot.activity.entity.RoleIgnoredData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleIgnoredRepository extends MongoRepository<RoleIgnoredData, String> {
}
