package laufyeconomybot.activity.listeners;

import com.wallferjdi.laufyeconomybot.configuration.ActivityConfiguration;
import com.wallferjdi.laufyeconomybot.controllers.RequestController;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ActivityListener extends ListenerAdapter {


    @Autowired
    private RequestController requestController;

    @Autowired
    private ActivityConfiguration activityConfiguration;



    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        System.out.println(event.getMember()+ " JOOOINEDDD "+ event.getGuild().getRoleById(activityConfiguration.getZeroRoleId()));
        event.getGuild().addRoleToMember(event.getMember(),event.getGuild().getRoleById(activityConfiguration.getZeroRoleId())).queue();
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        System.out.println(event.getGuild().getName() + " "+ event.getGuild().getId()+" Joined");
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        requestController.increaseXp(event);
    }

    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent event) {
        requestController.addVoiceMember(event);

    }


    @Override
    public void onGuildVoiceLeave(@NotNull GuildVoiceLeaveEvent event) {
           requestController.increaseXpVoice(event);
    }


    @Override
    public void onGuildVoiceMute(@NotNull GuildVoiceMuteEvent event) {

        if(event.isMuted()){
            requestController.selfMute(event);
        }else{
            requestController.addUnMutedMember(event);
        }


    }


}
