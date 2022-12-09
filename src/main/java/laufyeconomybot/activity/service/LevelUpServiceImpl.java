package laufyeconomybot.activity.service;

import com.wallferjdi.laufyeconomybot.activity.entity.MemberData;
import com.wallferjdi.laufyeconomybot.activity.repository.MemberDataRepository;
import com.wallferjdi.laufyeconomybot.configuration.ActivityConfiguration;
import com.wallferjdi.laufyeconomybot.activity.entity.RoleLvlData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class LevelUpServiceImpl implements LevelUpService {


    @Autowired
    private RoleLvlServiceImpl roleLvlDAO;

    @Autowired
    private MemberDataRepository repository;

    @Autowired
    private ActivityConfiguration activityConfiguration;

    @Override
    public void performLevelUp(Integer lvl, Member member) {
        Guild guild = activityConfiguration.getGuild();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setFooter("Laufy bot");
        embedBuilder.setColor(Color.orange);
        embedBuilder.setTitle(":fireworks: Level Up!");
        embedBuilder.addField("", member.getAsMention() + " Достиг " + lvl + "-го уровня!", true);
        embedBuilder.setThumbnail((member.getUser().getAvatarUrl()));
        guild.getTextChannelById(activityConfiguration.getPerformedLevelChatID()).sendMessageEmbeds(embedBuilder.build()).content(member.getAsMention()).queue();
        checkLvlRole(lvl, guild, member);

    }

    @Override
    public void checkLvlRole(Integer lvl, Guild guild, Member member) {
        try {
            guild.addRoleToMember(member, guild.getRoleById(roleLvlDAO.getRoleByLvl(lvl))).queue();

//            MemberData memberData = new MemberData();
//            memberData.setLevel(lvl);
//            memberData.setId(Long.parseLong(member.getUser().getId()));
//            repository.save(memberData);


            if (member.getRoles().size() > 0) {
                for (RoleLvlData role : roleLvlDAO.getAllRoleLvl()) {
                    try {

                        if (role.getLvl() == lvl) {
                            continue;
                        }
                        guild.removeRoleFromMember(member, guild.getRoleById(role.getRoleId())).queue();
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
