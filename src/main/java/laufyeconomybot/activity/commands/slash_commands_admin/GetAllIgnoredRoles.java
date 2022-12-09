package laufyeconomybot.activity.commands.slash_commands_admin;


import com.wallferjdi.laufyeconomybot.activity.entity.RoleIgnoredData;
import com.wallferjdi.laufyeconomybot.activity.utils.IgnoreRoleTable;
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
public class GetAllIgnoredRoles extends AbstractCommandData{


    @Autowired
    private IgnoreRoleTable ignoreRoleTable;

    @PostConstruct
    public void setCommand() {
        setCommand(new SubcommandData(getName(),getDescription()));
    }

    @Override
    public EmbedBuilder request(SlashCommandInteractionEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.ORANGE);
        embedBuilder.setDescription("Черный список ролей");
        List<RoleIgnoredData> roleIgnoredDataList = ignoreRoleTable.getAllIgnoredRoles();

        for (RoleIgnoredData data: roleIgnoredDataList){

            embedBuilder.addField("",event.getGuild().getRoleById(data.getId()).getAsMention() ,false);
        }


        return embedBuilder;
    }

    @Override
    @Value("${command.ignored_roles_list.name}")
    public void setName(String name) {
        super.name = name;
    }

    @Override
    @Value("${command.ignored_roles_list.description}")
    public void setDescription(String description) {
        super.description = description;
    }
}
