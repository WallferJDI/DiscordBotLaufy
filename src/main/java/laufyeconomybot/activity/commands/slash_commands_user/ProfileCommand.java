package laufyeconomybot.activity.commands.slash_commands_user;


import com.wallferjdi.laufyeconomybot.activity.entity.UserData;
import com.wallferjdi.laufyeconomybot.activity.service.UserServiceImpl;
import com.wallferjdi.laufyeconomybot.activity.utils.RankTableMap;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;


@Component
public class ProfileCommand extends AbstractCommandDataUsers {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RankTableMap rankTableMap;

    @PostConstruct
    public void setCommand() {
        setCommand(Commands.slash(getName(),getDescription()) );
    }

    @Override
    public EmbedBuilder request(SlashCommandInteractionEvent event)  {
        try {
            return profileImage(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private EmbedBuilder profileImage(SlashCommandInteractionEvent event) throws IOException {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.orange);
        embedBuilder.setDescription(event.getUser().getAsMention());
        embedBuilder.setTitle(":ledger: "+"Ваш профиль Laufy Squad");
        int rank = 0;
        List<UserData> userDataList = userService.getAllUsers();
        userDataList.sort(new Comparator<UserData>() {
            @Override
            public int compare(UserData o1, UserData o2) {
                return o2.getExp() - o1.getExp();
            }
        });
        for (int i = 0;i<userDataList.size();i++){
            if(userDataList.get(i).getId().equals(event.getUser().getId())){
                rank = i+1;
                break;
            }
        }
        UserData user = userService.getUserInfo(event.getUser());
         embedBuilder.addField("Rank ", "`#" +rank +"`",true);
            embedBuilder.addField("Level ", "`"+(user.getLvl())+"`",true);
            embedBuilder.addField("Exp ", "`" +user.getExp() +"`"+" / `"+rankTableMap.getXpPerLevelTable().get(user.getLvl()+1)+"`",false);
            embedBuilder.addField("Voice ", "`" +user.getVoice()/60 +"`"+" hours",false);
            embedBuilder.addField("Messages ", "`" +user.getMessages()+"`",false);


        embedBuilder.setThumbnail((event.getUser().getAvatarUrl()));
        return  embedBuilder;
    }
    @Override
    @Value("${command.profile.name}")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("${command.profile.description}")
    public void setDescription(String description) {
        super.description = description;
    }

}
