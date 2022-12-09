package laufyeconomybot.activity.service;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public interface LevelUpService {

    void performLevelUp(Integer lvl, Member member);
    void checkLvlRole(Integer lvl,Guild guild, Member member);

}
