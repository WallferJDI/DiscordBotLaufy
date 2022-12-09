package laufyeconomybot.activity.strategy.activity_rank_system;

import com.wallferjdi.laufyeconomybot.activity.entity.UserData;
import com.wallferjdi.laufyeconomybot.activity.service.ExpLvlDependencyService;
import com.wallferjdi.laufyeconomybot.activity.service.LevelUpServiceImpl;
import com.wallferjdi.laufyeconomybot.activity.service.UserServiceImpl;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ActivityRankSystem {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ExpLvlDependencyService expLvlDependencyService;

    @Autowired
    private LevelUpServiceImpl levelUpService;


    public void setExp(Member member, int expCount){
        UserData userData = userService.getUserInfo(member.getUser());

        userData.setExp(expCount);
        checkDependency(userData,member);
    }

    public void increaseExpMessage(Member member, int expIncreaseCount) {
        UserData userData = userService.getUserInfo(member.getUser());
        userData.setMessages(userData.getMessages()+1);
        userData.setExp(userData.getExp() + expIncreaseCount);
        checkDependency(userData,member);
    }
    public void increaseExp(Member member, int expIncreaseCount) {
        UserData userData = userService.getUserInfo(member.getUser());
        userData.setExp(userData.getExp() + expIncreaseCount);
        checkDependency(userData,member);
    }

    public void decreaseExp(Member member, int expIncreaseCount) {
        UserData userData = userService.getUserInfo(member.getUser());
        userData.setExp(userData.getExp() - expIncreaseCount);
        checkDependency(userData,member);
    }

    public void checkDependency(UserData userData,Member member){
            int lvl = expLvlDependencyService.resolveExpToLevelDependency(userData.getExp());
            if(userData.getLvl()!=lvl){
                levelUpService.performLevelUp(lvl,member);
                userData.setLvl(lvl);
            }
            userService.addUser(userData);
    }

    public void setLvl(Member member, int lvlCount) {

        UserData userData = userService.getUserInfo(member.getUser());
        userData.setLvl(lvlCount);
        int expCount = expLvlDependencyService.resolveLevelToExpDependency(lvlCount);
        userData.setExp(expCount);
        userService.addUser(userData);
        levelUpService.performLevelUp(lvlCount,member);
    }
    public void setVoiceTime(User user, int voiceTime) {
        UserData userData = userService.getUserInfo(user);
        userData.setVoice(userData.getVoice()+voiceTime);
        userService.addUser(userData);
    }




}




