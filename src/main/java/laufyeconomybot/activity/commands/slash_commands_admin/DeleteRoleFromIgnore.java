package laufyeconomybot.activity.commands.slash_commands_admin;


import com.wallferjdi.laufyeconomybot.configuration.OptionDataConfiguration;
import com.wallferjdi.laufyeconomybot.activity.utils.IgnoreRoleTable;
import net.dv8tion.jda.api.EmbedBuilder;
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
public class DeleteRoleFromIgnore extends AbstractCommandData {
    @Autowired
    private OptionDataConfiguration dataConfiguration;

    @Autowired
    private IgnoreRoleTable ignoreRoleTable;

    @PostConstruct
    public void setCommand() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(dataConfiguration.getRoleOption());
        setOptionData(optionData);
        setCommand(new SubcommandData(getName(),getDescription()).addOptions(getOptionData()));
    }

    @Override
    public EmbedBuilder request(SlashCommandInteractionEvent event) {

        try {
           ignoreRoleTable.deleteIgnoredRole(event.getInteraction()
                   .getOption(dataConfiguration.getRoleOption().getName()).getAsRole().getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        EmbedBuilder  embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(getDescription());
        embedBuilder.setColor(Color.decode("#2f3136"));
        embedBuilder.setDescription(event.getInteraction()
                .getOption(dataConfiguration.getRoleOption().getName()).getAsRole().getAsMention() + " удалена из игнора ");
        return embedBuilder;
    }


    @Override
    @Value("${command.delete_role_from_ignore.name}")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("${command.delete_role_from_ignore.description}")
    public void setDescription(String description) {
        System.out.println("описание "+ description);
        super.description = description;
    }

}
