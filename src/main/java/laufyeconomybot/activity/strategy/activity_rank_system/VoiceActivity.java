package laufyeconomybot.activity.strategy.activity_rank_system;


import com.wallferjdi.laufyeconomybot.configuration.ActivityConfiguration;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class VoiceActivity {
    private Map<String, Date> timeJoinedMembers = new HashMap<>();

    @Autowired
    private ActivityConfiguration activityConfiguration;

    public void addVoiceMember(GuildVoiceJoinEvent event){
        List<Member> membersInChat = event.getChannelJoined().getMembers();
        for (Member member: membersInChat) {

            System.out.println(member);
            for (Role role: member.getRoles()){
                if(role.getId().equals(activityConfiguration.getBotsRoleId())){
                    membersInChat.remove(member);
                    break;
                }

            }

        }

        if(membersInChat.size()>= 2){
           List<Member> members= event.getChannelJoined().getMembers();
            for(Member member:members){
                if(!member.getVoiceState().isMuted()){
                    if(timeJoinedMembers.get(member.getId())==null){
                        timeJoinedMembers.put(member.getUser().getId(),new Date());
                    }
                }

            }
        }
    }

    public void addUnMutedMember(GuildVoiceMuteEvent event){
        timeJoinedMembers.put(event.getMember().getUser().getId(),new Date());
    }

    public long getVoiceMemberTime(Member member){


        Date date = new Date();
        try{
            Date date1 = timeJoinedMembers.get(member.getUser().getId());
            long time_in_chat =(date.getTime() - date1.getTime()) /1000;
            timeJoinedMembers.remove(member.getUser().getId());
            return time_in_chat;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;

    }

    public long checkLastMember(GuildVoiceLeaveEvent event){
        try {
            Date date = new Date();
                Member memberAlone = event.getChannelLeft().getMembers().get(0);
                Date date1 = timeJoinedMembers.get(memberAlone.getUser().getId());
                long time_in_chat = (date.getTime() - date1.getTime()) / 1000;
                timeJoinedMembers.remove(memberAlone.getUser().getId());
                return time_in_chat;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return 0;
    }

}
