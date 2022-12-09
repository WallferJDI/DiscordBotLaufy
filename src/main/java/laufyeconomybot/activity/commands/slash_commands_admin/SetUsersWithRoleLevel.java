package laufyeconomybot.activity.commands.slash_commands_admin;


import com.wallferjdi.laufyeconomybot.configuration.OptionDataConfiguration;
import com.wallferjdi.laufyeconomybot.activity.strategy.activity_rank_system.ActivityRankSystem;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SetUsersWithRoleLevel extends AbstractCommandData {

    @Autowired
    private OptionDataConfiguration dataConfiguration;

    @Autowired
    private ActivityRankSystem activityRankSystem;


    @PostConstruct
    public void setCommand() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(dataConfiguration.getRoleOption());
        optionData.add(dataConfiguration.getCountOption());
        setOptionData(optionData);
        setCommand(new SubcommandData(getName(),getDescription()).addOptions(getOptionData()));
    }

    @Override
    public EmbedBuilder request(SlashCommandInteractionEvent event) {
       List<Member> membersList =  event.getGuild().getMembersWithRoles(event.getInteraction()
               .getOption(dataConfiguration.getRoleOption().getName()).getAsRole());
        System.out.println(membersList.size());
       int level = event.getInteraction().getOption(dataConfiguration.getCountOption().getName()).getAsInt();

        System.out.println(membersList);
        for (Member member: membersList) {
            System.out.println(member);
          activityRankSystem.setLvl(member,level);
        }
        EmbedBuilder  embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(getDescription());
        embedBuilder.setColor(Color.decode("#2f3136"));
        embedBuilder.setDescription(event.getInteraction()
                .getOption(dataConfiguration.getRoleOption().getName()).getAsRole().getAsMention() + " был установлен уровень "+level);
        return embedBuilder;
    }


    @Override
    @Value("${command.set_roleusers_level.name}")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("${command.set_roleusers_level.description}")
    public void setDescription(String description) {
        System.out.println("описание "+ description);
        super.description = description;
    }
}
