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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddRoleToIgnore extends AbstractCommandData  {

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
            ignoreRoleTable.addIgnore(event.getInteraction()
                    .getOption(dataConfiguration.getRoleOption().getName()).getAsRole().getId());
            ignoreRoleTable.loadIgnoreList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EmbedBuilder  embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(getDescription());
        embedBuilder.setColor(Color.decode("#2f3136"));
        embedBuilder.setDescription(event.getInteraction()
                .getOption(dataConfiguration.getRoleOption().getName()).getAsRole().getAsMention() + " добавлена в черный список ");
        return embedBuilder;
    }


    @Override
    @Value("${command.role.name}")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("${command.role.description}")
    public void setDescription(String description) {
        System.out.println("описание "+ description);
        super.description = description;
    }


}
