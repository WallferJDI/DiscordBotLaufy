package laufyeconomybot.activity.entity.sql;//package com.wallferjdi.laufyeconomybot.activity.entity.sql;
//
//import com.wallferjdi.laufyeconomybot.activity.entity.RoleIgnoredData;
//import com.wallferjdi.laufyeconomybot.activity.entity.RoleLvlData;
//import com.wallferjdi.laufyeconomybot.activity.entity.UserData;
//import com.wallferjdi.laufyeconomybot.activity.repository.RoleIgnoredRepository;
//import com.wallferjdi.laufyeconomybot.activity.repository.RoleLvlRepository;
//import com.wallferjdi.laufyeconomybot.activity.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class CopyServiceSqlToMongo {
//
//    private final RoleIIgnoredSqlRepository roleIIgnoredSqlRepository;
//    private final RoleLvlSqlRepository roleLvlSqlRepository;
//    private final UserDataSqlRepository userDataSqlRepository;
//
//    private final RoleIgnoredRepository roleIgnoredRepository;
//    private final RoleLvlRepository roleLvlRepository;
//    private final UserRepository userRepository;
//
//    public void copyUserData(){
//        List<UserDataSql> userDataSqlList = userDataSqlRepository.findAll();
//        List<UserData> userDataList = new ArrayList<>();
//        for (UserDataSql u:userDataSqlList) {
//            UserData userData = new UserData();
//            userData.setId(u.getId());
//            userData.setName(u.getName());
//            userData.setVoice(u.getVoice());
//            userData.setMessages(u.getMessages());
//            userData.setExp(u.getExp());
//            userData.setLvl(u.getLvl());
//            userDataList.add(userData);
//        }
//        userRepository.saveAll(userDataList);
//        //System.out.println(userDataList);
//    }
//
//    public void copyRoleLvl(){
//        List<RoleLvlDataSql> roleLvlDataSqls = roleLvlSqlRepository.findAll();
//        List<RoleLvlData> roleLvlData = new ArrayList<>();
//        for (RoleLvlDataSql data: roleLvlDataSqls){
//            RoleLvlData roleLvlData1 = new RoleLvlData();
//            roleLvlData1.setRoleId(data.getRoleId());
//            roleLvlData1.setLvl(data.getLvl());
//            roleLvlData.add(roleLvlData1);
//        }
//        roleLvlRepository.saveAll(roleLvlData);
//    }
//
//    public void copyRoleIgnored() {
//    List<RoleIgnoredDataSql> roleIgnoredDataSqls = roleIIgnoredSqlRepository.findAll();
//    List<RoleIgnoredData> roleIgnoredDataList = new ArrayList<>();
//        for (RoleIgnoredDataSql data: roleIgnoredDataSqls) {
//            RoleIgnoredData roleIgnoredData = new RoleIgnoredData();
//            roleIgnoredData.setId(data.getId());
//            roleIgnoredDataList.add(roleIgnoredData);
//        }
//        roleIgnoredRepository.saveAll(roleIgnoredDataList);
//    }
//    }
