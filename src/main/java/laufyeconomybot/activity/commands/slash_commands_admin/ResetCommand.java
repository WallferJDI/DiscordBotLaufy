package laufyeconomybot.activity.commands.slash_commands_admin;


import com.wallferjdi.laufyeconomybot.configuration.OptionDataConfiguration;
import com.wallferjdi.laufyeconomybot.activity.service.UserServiceImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
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
public class ResetCommand  extends AbstractCommandData {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OptionDataConfiguration dataConfiguration;

    @PostConstruct
    public void setCommand() {
        List<OptionData> optionDataList = new ArrayList<OptionData>();
        optionDataList.add(dataConfiguration.getUserOption());
        setOptionData(optionDataList);

        setCommand(new SubcommandData(getName(),getDescription()).addOptions(getOptionData()));


    }

    @Override
    public EmbedBuilder request(SlashCommandInteractionEvent event) {
             User eventUser = event.getInteraction().getOption(dataConfiguration.getUserOption().getName()).getAsUser();
             userService.deleteUser(eventUser.getId());
             EmbedBuilder  embedBuilder = new EmbedBuilder();
             embedBuilder.setTitle(getDescription());
             embedBuilder.setColor(Color.decode("#2f3136"));
             embedBuilder.setDescription(event.getInteraction().getOption(dataConfiguration.getUserOption().getName()).getAsUser().getAsMention()+" Был сброшен уровень");
        return embedBuilder;
    }

    @Override
    @Value("${command.reset.name}")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("${command.reset.description}")
    public void setDescription(String description) {
        super.description = description;
    }
}
