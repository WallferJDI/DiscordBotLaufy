package laufyeconomybot.activity.listeners;

import com.wallferjdi.laufyeconomybot.configuration.ActivityConfiguration;
import com.wallferjdi.laufyeconomybot.controllers.CommandController;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommandListener extends ListenerAdapter {

    @Autowired
    private CommandController commandController;

    @Autowired
    private ActivityConfiguration activityConfiguration;
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        commandController.commandInteraction(event);

    }


    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        activityConfiguration.setGuild(event.getGuild());
        commandController.guildSet(event);
    }
}
