package laufyeconomybot.activity.service;

import com.wallferjdi.laufyeconomybot.activity.entity.RoleIgnoredData;

import java.util.List;

public interface RoleIgnoredService {
    void addIgnoreRole(String id);
    List<RoleIgnoredData> getAllIgnoredRole();
    void deleteIgnoredRole(String id);
}
