package laufyeconomybot.activity.utils;

import com.wallferjdi.laufyeconomybot.activity.entity.RoleIgnoredData;
import com.wallferjdi.laufyeconomybot.activity.service.RoleIgnoredServiceImpl;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class IgnoreRoleTable {

    @Autowired
    private RoleIgnoredServiceImpl roleIgnoredService;
    private List<String> ignoredRoleList;

    public List<String> getIgnoredRoleList() {
        return ignoredRoleList;
    }


    @PostConstruct
    public List<String> loadIgnoreList() throws IOException {

         ignoredRoleList = new ArrayList<>();

        try {
            List<RoleIgnoredData> roleIgnoredDataList = roleIgnoredService.getAllIgnoredRole();
            for (RoleIgnoredData ignoredData:roleIgnoredDataList)
            ignoredRoleList.add(ignoredData.getId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return ignoredRoleList;
    }
    public void deleteIgnoredRole(String id){
        try {
            roleIgnoredService.deleteIgnoredRole(id);
            loadIgnoreList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public List<RoleIgnoredData> getAllIgnoredRoles(){
        return roleIgnoredService.getAllIgnoredRole();
    }

    public String addIgnore(String id) throws IOException {

        try{
            roleIgnoredService.addIgnoreRole(id);
            loadIgnoreList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "Success writed";
    }

    public boolean checkIgnoredRole(Member member) {
        try {
            List<Role> roleList = member.getRoles();
            List<String> ignoreRolesList = getIgnoredRoleList();
            for (Role role : roleList) {
                for (String idIgnore : ignoreRolesList) {
                    if (role.getId().equals(idIgnore)) {
                        System.out.println("role ignored");
                        return true;
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
