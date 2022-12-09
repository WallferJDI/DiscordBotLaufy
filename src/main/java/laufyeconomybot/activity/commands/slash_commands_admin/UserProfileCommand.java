package laufyeconomybot.activity.commands.slash_commands_admin;


import com.wallferjdi.laufyeconomybot.configuration.OptionDataConfiguration;
import com.wallferjdi.laufyeconomybot.activity.entity.UserData;
import com.wallferjdi.laufyeconomybot.activity.service.UserServiceImpl;
import com.wallferjdi.laufyeconomybot.activity.utils.RankTableMap;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class UserProfileCommand extends AbstractCommandData{
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private OptionDataConfiguration dataConfiguration;
    @Autowired
    private RankTableMap rankTableMap;

    @PostConstruct
    public void setCommand() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(dataConfiguration.getUserOption());
        setOptionData(optionData);
        setCommand(new SubcommandData(getName(),getDescription()).addOptions(getOptionData()));
    }

    @Override
    public EmbedBuilder request(SlashCommandInteractionEvent event)  {
        try {
            return profileImage(event.getInteraction().getOption(dataConfiguration.getUserOption().getName()).getAsUser());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private EmbedBuilder profileImage(User user) throws IOException {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.orange);
        embedBuilder.setDescription(user.getAsMention());
        embedBuilder.setTitle(":ledger: "+"Профиль учатника Laufy Squad");
        int rank = 0;
        UserData user1 = userService.getUserInfo(user);

        List<UserData> userDataList = userService.getAllUsers();
        userDataList.sort(new Comparator<UserData>() {
            @Override
            public int compare(UserData o1, UserData o2) {
                return o2.getExp() - o1.getExp();
            }
        });
        for (int i = 0;i<userDataList.size();i++){
            if(userDataList.get(i).getId().equals(user.getId())){
                rank = i+1;
                break;
            }
        }

        embedBuilder.addField("Rank ", "`#" +rank +"`",true);
        embedBuilder.addField("Level ", "`"+(user1.getLvl())+"`",true);
        embedBuilder.addField("Exp ", "`" +user1.getExp() +"`"+" / `"+rankTableMap.getXpPerLevelTable().get(user1.getLvl()+1)+"`",false);
        embedBuilder.addField("Voice ", "`" +user1.getVoice()/60 +"`"+" hours",false);
        embedBuilder.addField("Messages ", "`" +user1.getMessages()+"`",false);


        embedBuilder.setThumbnail((user.getAvatarUrl()));
        return  embedBuilder;
    }
    @Override
    @Value("profile_user")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("Посмотреть профиль участника")
    public void setDescription(String description) {
        super.description = description;
    }

}
