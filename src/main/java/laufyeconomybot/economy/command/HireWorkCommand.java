package laufyeconomybot.economy.command;


//import com.wallferjdi.laufyeconomybot.activity.commands.slash_commands_admin.AbstractCommandData;
//import com.wallferjdi.laufyeconomybot.activity.entity.sql.CopyServiceSqlToMongo;
//import com.wallferjdi.laufyeconomybot.configuration.OptionDataConfiguration;
//import com.wallferjdi.laufyeconomybot.controllers.ActivityController;
//import lombok.RequiredArgsConstructor;
//import net.dv8tion.jda.api.EmbedBuilder;
//import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
//import net.dv8tion.jda.api.interactions.commands.OptionType;
//import net.dv8tion.jda.api.interactions.commands.build.OptionData;
//import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class HireWorkCommand extends AbstractCommandData {
//
//    private final OptionDataConfiguration dataConfiguration;
//    private final ActivityController activityController;
//    private final CopyServiceSqlToMongo sqlToMongo;
//
//    @PostConstruct
//    public void setCommand() {
//
//        List<OptionData> optionData = new ArrayList<>();
//        optionData.add(new OptionData(OptionType.STRING,"choice","выбери работу",true)
//                .addChoice("Шахтер","1")
//                .addChoice("Клоун","2")
//                .addChoice("Чурка","3"));
//
//        setOptionData(optionData);
//        SubcommandData subcommandData = new SubcommandData(getName(), getDescription()).addOptions(getOptionData());
//        setCommand(subcommandData);
//    }
//
//    @Override
//    public EmbedBuilder request(SlashCommandInteractionEvent event) {
//
//
//        String requestValue = event.getInteraction()
//                .getOption(dataConfiguration.getChoiceOption().getName()).getAsString();
//
//
//        EmbedBuilder embedBuilder = new EmbedBuilder();
//        embedBuilder.setTitle(getDescription());
//        embedBuilder.setColor(Color.decode("#2f3136"));
//
//        System.out.println(requestValue);
//        if (requestValue.equals("1")) {
//            embedBuilder.setDescription("ты устроился "+ event.getInteraction()
//                    .getOption(dataConfiguration.getChoiceOption().getName()));
//
//        } else if (requestValue.equals("2")) {
//            embedBuilder.setDescription("ты устроился "+ dataConfiguration.getChoiceOption().getName());
//
//
//        } else if (requestValue.equals("3")) {
//            embedBuilder.setDescription("ты устроился "+ dataConfiguration.getChoiceOption().getName());
//        }
//
//
//        return embedBuilder;
//    }
//
//
//    @Override
//    @Value("hire-work")
//    public void setName(String name) {
//        super.name = name;
//    }
//
//    @Override
//    @Value("устроиться на работу")
//    public void setDescription(String description) {
//        super.description = description;
//    }
//
//
//}

