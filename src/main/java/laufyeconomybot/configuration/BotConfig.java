package laufyeconomybot.configuration;

import com.wallferjdi.laufyeconomybot.activity.listeners.ActivityListener;
import com.wallferjdi.laufyeconomybot.activity.listeners.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;


@Configuration
public class BotConfig {


    @Autowired
    private CommandListener commandListener;

    @Autowired
    private ActivityListener activityListener;


    @Value("${discord.bot.token}")
    private String botToken;



    @PostConstruct
    public JDA jdaBot() throws LoginException {
        return  JDABuilder.createDefault(botToken).setMemberCachePolicy(MemberCachePolicy.ALL).setChunkingFilter(ChunkingFilter.ALL).enableIntents(GatewayIntent.GUILD_MEMBERS).addEventListeners(commandListener).addEventListeners(activityListener).build();
    }


}
