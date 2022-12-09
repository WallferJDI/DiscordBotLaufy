package laufyeconomybot.activity.repository;

import com.wallferjdi.laufyeconomybot.activity.entity.RoleLvlData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleLvlRepository extends MongoRepository<RoleLvlData, String> {
            RoleLvlData findByLvl(Integer lvl);
            void deleteByLvl(Integer lvl);
}
