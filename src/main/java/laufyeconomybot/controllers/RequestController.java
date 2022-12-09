package laufyeconomybot.controllers;

import com.wallferjdi.laufyeconomybot.activity.strategy.activity_rank_system.VoiceActivity;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RequestController {
    @Autowired
    private ActivityController activityController;
    @Autowired
    private VoiceActivity voiceActivity;

    public void increaseXp(MessageReceivedEvent event){

        activityController.increaseXpMessage(event.getMember());
    }
    public void increaseXpVoice(GuildVoiceLeaveEvent event){
        activityController.increaseXpVoice(event.getMember(),voiceActivity.getVoiceMemberTime(event.getMember()));
        if(event.getChannelLeft().getMembers().size()==1){
         activityController.increaseXpVoice(event.getChannelLeft().getMembers().get(0),voiceActivity.checkLastMember(event));
        }
    }

    public void addVoiceMember(GuildVoiceJoinEvent event){
        voiceActivity.addVoiceMember(event);
    }

    public void selfMute( GuildVoiceMuteEvent event){
        activityController.increaseXpVoice(event.getMember(),voiceActivity.getVoiceMemberTime(event.getMember()));

    }

    public void addUnMutedMember(GuildVoiceMuteEvent event){
        voiceActivity.addUnMutedMember(event);
    }



}
