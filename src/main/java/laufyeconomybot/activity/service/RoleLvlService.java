package laufyeconomybot.activity.service;

import com.wallferjdi.laufyeconomybot.activity.entity.RoleLvlData;

import java.util.List;

public interface RoleLvlService {
    String getRoleByLvl(Integer lvl);
    void setRoleOnLvl(Integer lvl,String id);
    List<RoleLvlData> getAllRoleLvl();
    void deleteRoleFoLevel(Integer lvl);
}
