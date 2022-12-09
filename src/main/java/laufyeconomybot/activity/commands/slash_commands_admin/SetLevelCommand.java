package laufyeconomybot.activity.commands.slash_commands_admin;


import com.wallferjdi.laufyeconomybot.configuration.OptionDataConfiguration;
import com.wallferjdi.laufyeconomybot.controllers.ActivityController;
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
public class SetLevelCommand extends AbstractCommandData {

    @Autowired
    private OptionDataConfiguration dataConfiguration;
    @Autowired
    private ActivityController activityController;

    @PostConstruct
    public void setCommand() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(dataConfiguration.getUserOption());
        optionData.add(dataConfiguration.getCountOption());
        setOptionData(optionData);
        setCommand(new SubcommandData(getName(),getDescription()).addOptions(getOptionData()));
    }

    @Override
    public EmbedBuilder request(SlashCommandInteractionEvent event) {
        activityController.setLevel(event.getInteraction().getOption(dataConfiguration.getUserOption().getName()).getAsMember(),event
                .getInteraction().getOption(dataConfiguration.getCountOption().getName()).getAsInt());
        EmbedBuilder  embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(getDescription());
        embedBuilder.setColor(Color.decode("#2f3136"));
        embedBuilder.setDescription(event.getInteraction()
                .getOption(dataConfiguration.getUserOption().getName()).getAsUser().getAsMention() + " Был установлен " + event.getInteraction()
                .getOption(dataConfiguration.getCountOption().getName()).getAsInt()+" level");
        return embedBuilder;
    }

    @Override
    @Value("${command.set_lvl.name}")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("${command.set_lvl.description}")
    public void setDescription(String description) {
        super.description = description;
    }


}
