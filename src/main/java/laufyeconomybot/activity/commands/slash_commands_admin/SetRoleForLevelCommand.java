package laufyeconomybot.activity.commands.slash_commands_admin;


import com.wallferjdi.laufyeconomybot.configuration.OptionDataConfiguration;
import com.wallferjdi.laufyeconomybot.activity.service.RoleLvlServiceImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
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
public class SetRoleForLevelCommand extends AbstractCommandData {

    @Autowired
    private OptionDataConfiguration dataConfiguration;
    @Autowired
    private RoleLvlServiceImpl roleLvlService;

    @PostConstruct
    public void setCommand() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(dataConfiguration.getCountOption());
        optionData.add(dataConfiguration.getRoleOption());
        setOptionData(optionData);
        setCommand(new SubcommandData(getName(),getDescription()).addOptions(getOptionData()));
    }

    @Override
    public EmbedBuilder request(SlashCommandInteractionEvent event) {
            int lvl =event.getInteraction().getOption(dataConfiguration.getCountOption().getName()).getAsInt();
            Role role = event.getInteraction().getOption(dataConfiguration.getRoleOption().getName()).getAsRole();

            roleLvlService.setRoleOnLvl(lvl,role.getId());


        EmbedBuilder  embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(getDescription());
        embedBuilder.setColor(Color.decode("#2f3136"));
        embedBuilder.setDescription(lvl + " был установлен за "+ role.getAsMention());
        return embedBuilder;
    }

    @Override
    @Value("${command.set_role_for_level.name}")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("${command.set_role_for_level.description}")
    public void setDescription(String description) {
        super.description = description;
    }


}
