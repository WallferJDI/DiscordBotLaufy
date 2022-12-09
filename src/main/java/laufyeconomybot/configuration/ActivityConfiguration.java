package laufyeconomybot.configuration;

import net.dv8tion.jda.api.entities.Guild;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivityConfiguration {
    @Value("${activity.exp.message}")
    private int messageExpIncrease;

    @Value("${activity.exp.voice}")
    private int voiceExpIncrease;

    @Value("${performedLevelChatID}")
    private String performedLevelChatID;

    @Value("${role.zero.id}")
    private String zeroRoleId;

    public String getPerformedLevelChatID() {
        return performedLevelChatID;
    }

    public String getZeroRoleId() {
        return zeroRoleId;
    }

    @Value("${botsRole.id}")
    private String botsRoleId;

    public String getBotsRoleId() {
        return botsRoleId;
    }

    private Guild guild;

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    private int voiceCountStyle = 60;

    public int getMessageExpIncrease() {
        return messageExpIncrease;
    }

    public int getVoiceExpIncrease() {
        return voiceExpIncrease;
    }

    public int getVoiceCountStyle() {
        return voiceCountStyle;
    }
}
