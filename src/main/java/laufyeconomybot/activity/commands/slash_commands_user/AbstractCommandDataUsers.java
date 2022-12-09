package laufyeconomybot.activity.commands.slash_commands_user;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.io.IOException;
import java.util.List;


public abstract class AbstractCommandDataUsers {

    private List<OptionData> optionData;
    public String name;
    public String description;
    private SlashCommandData command;

    public void setOptionData(List<OptionData> optionData) {
        this.optionData = optionData;
    }
    public abstract EmbedBuilder request(SlashCommandInteractionEvent event) throws IOException;
    public abstract void setName(String name);
    public abstract void setDescription(String description);

    public void setCommand(SlashCommandData slashCommandData) {
        this.command = slashCommandData;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public SlashCommandData getCommand() {
        return command;
    }

    public List<OptionData>  getOptionData() {
        return optionData;
    }

}
