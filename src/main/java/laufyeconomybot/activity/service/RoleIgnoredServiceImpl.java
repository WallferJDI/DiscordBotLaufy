package laufyeconomybot.activity.service;

import com.wallferjdi.laufyeconomybot.activity.entity.RoleIgnoredData;
import com.wallferjdi.laufyeconomybot.activity.repository.RoleIgnoredRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleIgnoredServiceImpl implements RoleIgnoredService{

    private final RoleIgnoredRepository repository;

    @Override
    public void addIgnoreRole(String id) {
        RoleIgnoredData roleIgnoredData = new RoleIgnoredData();
        roleIgnoredData.setId(id);
        repository.save(roleIgnoredData);
    }

    @Override
    public List<RoleIgnoredData> getAllIgnoredRole() {
        return repository.findAll();
    }

    @Override
    public void deleteIgnoredRole(String id) {
        repository.deleteById(id);
    }
}
