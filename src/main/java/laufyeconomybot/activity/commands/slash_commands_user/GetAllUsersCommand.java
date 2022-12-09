package laufyeconomybot.activity.commands.slash_commands_user;


import com.wallferjdi.laufyeconomybot.activity.entity.UserData;
import com.wallferjdi.laufyeconomybot.activity.service.UserServiceImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.Comparator;
import java.util.List;


@Component
public class GetAllUsersCommand extends AbstractCommandDataUsers {

    @Autowired
    private UserServiceImpl userService;

    @PostConstruct
    public void setCommand() {
        setCommand(Commands.slash(getName(),getDescription()) );
    }

    @Override
    public EmbedBuilder request(SlashCommandInteractionEvent event) {


        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.ORANGE);
        embedBuilder.setDescription("Таблица Лидеров");
        embedBuilder.setTitle(":arrow_forward: "+event.getGuild().getName());
        List<UserData> userList=  userService.getAllUsers();
        userList.sort(new Comparator<UserData>() {
            @Override
            public int compare(UserData o1, UserData o2) {
                return o2.getExp() - o1.getExp();
            }
        });
        for (int i=0; i<userList.size()&&i<=9;i++){
            int j = i+1;
            String place = "#"+j;
            switch (j){
                case 1: place =":first_place:";
                break;
                case 2: place=":second_place:";
                break;
                case 3:place=":third_place:";
                break;
            }
            embedBuilder.addField( "","**"+place +"** :small_orange_diamond:"+ "<@"+userList.get(i).getId()+
                    ">"+"\n**Level**  `" +userList.get(i).getLvl() +"`\n" +" **Exp**  `"+ userList.get(i).getExp() +"`\n",false);
        }

        embedBuilder.setThumbnail((event.getGuild().getIconUrl()));
        return embedBuilder;

    }



    @Override
    @Value("${command.get_user_list.name}")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("${command.get_user_list.description}")
    public void setDescription(String description) {
        super.description = description;
    }


}
