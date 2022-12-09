package laufyeconomybot.activity.commands.slash_commands_admin;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import java.util.List;

public abstract class AbstractCommandData {

    private List<OptionData> optionData;
    public String name;
    public String description;
    private SubcommandData command;

    public void setOptionData(List<OptionData> optionData) {
        this.optionData = optionData;
    }
    public abstract EmbedBuilder request(SlashCommandInteractionEvent event);

    public abstract void setName(String name);
    public abstract void setDescription(String description);

    public void setCommand(SubcommandData subcommandData) {
        this.command = subcommandData;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public SubcommandData getCommand() {
        return command;
    }

    public List<OptionData>  getOptionData() {
        return optionData;
    }

}
