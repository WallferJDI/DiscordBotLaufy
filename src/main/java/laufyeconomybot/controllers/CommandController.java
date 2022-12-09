package laufyeconomybot.controllers;

import com.wallferjdi.laufyeconomybot.activity.commands.slash_commands_admin.AbstractCommandData;
import com.wallferjdi.laufyeconomybot.activity.commands.slash_commands_user.AbstractCommandDataUsers;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommandController {
    @Autowired
    List<AbstractCommandData> commandsList = new ArrayList<>();

    @Autowired
    List<AbstractCommandDataUsers> commandDataUsersList = new ArrayList<>();

    public void commandInteraction(SlashCommandInteractionEvent event){
        if(event.getInteraction().getSubcommandName()!=null){
            for (AbstractCommandData command :commandsList) {
                if(event.getInteraction().getSubcommandName().equals(command.getName())){
                    event.replyEmbeds(command.request(event).build()).queue();
                }
            }
        }
        for (AbstractCommandDataUsers command :commandDataUsersList) {
            if(event.getName().equals(command.getName())){
                try {

                    event.replyEmbeds(command.request(event).build()).queue();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void guildSet(GuildReadyEvent event){

        SlashCommandData slashCommandData = Commands.slash("admin","admins command");

        for (AbstractCommandData command :commandsList) {
            slashCommandData.addSubcommands(command.getCommand());
        }

        List<SlashCommandData> slashCommandDataUsers = new ArrayList<>();
        for (AbstractCommandDataUsers commandDataUsers: commandDataUsersList){
            slashCommandDataUsers.add(commandDataUsers.getCommand());

        }
        event.getGuild().updateCommands().addCommands(slashCommandData).addCommands(slashCommandDataUsers).queue();
    }


}
