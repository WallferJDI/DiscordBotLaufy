package laufyeconomybot.activity.repository;

import com.wallferjdi.laufyeconomybot.activity.entity.MemberData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberDataRepository extends MongoRepository<MemberData, Long> {
}
