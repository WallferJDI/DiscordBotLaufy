package laufyeconomybot.activity.commands.slash_commands_admin;


import com.wallferjdi.laufyeconomybot.activity.entity.RoleLvlData;
import com.wallferjdi.laufyeconomybot.activity.service.RoleLvlServiceImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.List;

@Component
public class GetAllRoleLvlCommand extends AbstractCommandData {
    @Autowired
    private RoleLvlServiceImpl roleLvlService;

    @PostConstruct
    public void setCommand() {
        setCommand(new SubcommandData(getName(),getDescription()));
    }

    @Override
    public EmbedBuilder request(SlashCommandInteractionEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.ORANGE);
        embedBuilder.setDescription(getDescription());
        try {
            List<RoleLvlData> roleLvlDataList = roleLvlService.getAllRoleLvl();

            for (RoleLvlData data: roleLvlDataList){
                System.out.println(data);
                embedBuilder.addField("","Уровень: "+ data.getLvl()+ " Роль:"+ event.getGuild().getRoleById(data.getRoleId()).getAsMention() ,false);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



        return embedBuilder;
    }

    @Override
    @Value("${command.get_leader_board}")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("${command.get_leader_board.description}")
    public void setDescription(String description) {
        super.description = description;
    }

}
