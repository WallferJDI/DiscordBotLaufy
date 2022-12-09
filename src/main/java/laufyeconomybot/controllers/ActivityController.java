package laufyeconomybot.controllers;

import com.wallferjdi.laufyeconomybot.configuration.ActivityConfiguration;
import com.wallferjdi.laufyeconomybot.activity.strategy.activity_rank_system.ActivityRankSystem;
import com.wallferjdi.laufyeconomybot.activity.utils.IgnoreRoleTable;
import net.dv8tion.jda.api.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ActivityController {

    @Autowired
    private ActivityRankSystem activityRankSystem;

    @Autowired
    private IgnoreRoleTable ignoreRoleTable;
    @Autowired
    private ActivityConfiguration activityConfiguration;

    public void setExp(Member member,int expCount){
        activityRankSystem.setExp(member, expCount);
    }

    public void setLevel(Member member,int lvlCount){
        activityRankSystem.setLvl(member, lvlCount);
    }
    public void increaseXpMessage(Member member){
        if(ignoreRoleTable.checkIgnoredRole(member)){
            return;
        }
        activityRankSystem.increaseExpMessage(member, activityConfiguration.getMessageExpIncrease());

    }
    public void increaseXpVoice(Member member, long timeSpendSeconds ){
        int timeSpendMinute = (int) (timeSpendSeconds/ activityConfiguration.getVoiceCountStyle());
        if(ignoreRoleTable.checkIgnoredRole(member)){
            return;
        }
        activityRankSystem.setVoiceTime(member.getUser(),timeSpendMinute);
        activityRankSystem.increaseExp(member,timeSpendMinute* activityConfiguration.getVoiceExpIncrease());
    }

    public void increaseXp(Member member,int expCount){
        activityRankSystem.increaseExp(member,expCount);
    }

    public void decreaseXp(Member member,int expCount){
        activityRankSystem.decreaseExp(member, expCount);
    }


    }





