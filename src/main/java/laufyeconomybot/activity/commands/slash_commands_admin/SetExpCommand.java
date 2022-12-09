package laufyeconomybot.activity.commands.slash_commands_admin;


import com.wallferjdi.laufyeconomybot.configuration.OptionDataConfiguration;
import com.wallferjdi.laufyeconomybot.controllers.ActivityController;
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
public class SetExpCommand extends AbstractCommandData {

    @Autowired
    private OptionDataConfiguration dataConfiguration;

    @Autowired
    private ActivityController activityController;

    @PostConstruct
    public void setCommand() {

        List<OptionData> optionData = new ArrayList<>();
        optionData.add(dataConfiguration.getUserOption());
        optionData.add(dataConfiguration.getCountOption());
        optionData.add(dataConfiguration.getChoiceOption());

        setOptionData(optionData);
        SubcommandData subcommandData = new SubcommandData(getName(),getDescription()).addOptions(getOptionData());
        setCommand(subcommandData);
    }

    @Override
    public EmbedBuilder request(SlashCommandInteractionEvent event) {

        Member member = event.getInteraction()
                .getOption(dataConfiguration.getUserOption().getName()).getAsMember();

        int count = event.getInteraction()
                .getOption(dataConfiguration.getCountOption().getName()).getAsInt();

        String requestValue = event.getInteraction()
                .getOption(dataConfiguration.getChoiceOption().getName()).getAsString();


        EmbedBuilder  embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(getDescription());
        embedBuilder.setColor(Color.decode("#2f3136"));

        System.out.println(requestValue);
        if(requestValue.equals("add")){
            embedBuilder.setDescription(member.getAsMention() + " Было добавлено " + count+" exp");
            activityController.increaseXp(member,count);

        }else if(requestValue.equals("decrease")){
            embedBuilder.setDescription(member.getAsMention() + " Было удалено " + count+" exp");
            activityController.decreaseXp(member,count);

        }else if(requestValue.equals("set")){
            embedBuilder.setDescription(member.getAsMention() + " Было установлено " + count+" exp");
            activityController.setExp(member,count);

        }







        return embedBuilder;
    }


    @Override
    @Value("${command.set_exp.name}")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("${command.set_exp.description}")
    public void setDescription(String description) {
        super.description = description;
    }


}
