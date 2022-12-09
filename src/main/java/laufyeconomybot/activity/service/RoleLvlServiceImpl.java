package laufyeconomybot.activity.service;


import com.wallferjdi.laufyeconomybot.activity.repository.RoleLvlRepository;
import com.wallferjdi.laufyeconomybot.configuration.ActivityConfiguration;
import com.wallferjdi.laufyeconomybot.activity.entity.RoleLvlData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleLvlServiceImpl implements RoleLvlService{

    private final RoleLvlRepository repository;

    private final ActivityConfiguration activityConfiguration;


    @Override
    public String getRoleByLvl(Integer lvl) {
        RoleLvlData roleLvlData =  repository.findByLvl(lvl);
        return  roleLvlData.getRoleId();
    }

    @Override
    public void setRoleOnLvl(Integer lvl, String id) {
        repository.save(new RoleLvlData(lvl, id));
    }

    @Override
    public List<RoleLvlData> getAllRoleLvl() {

        List<RoleLvlData> roleLvlDataList = repository.findAll();
        roleLvlDataList.add(new RoleLvlData(0, activityConfiguration.getZeroRoleId()));
        return roleLvlDataList;
    }

    @Override
    public void deleteRoleFoLevel(Integer lvl) {
    repository.deleteByLvl(lvl);
    }
}
