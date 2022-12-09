package laufyeconomybot.configuration;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OptionDataConfiguration {
    @Value("${option_data.user}")
    private String nameUserOption;
    @Value("${option_data.user.description}")
    private String descriptionUserOption;

    @Value("${option_data.count}")
    private String nameCountOption;
    @Value("${option_data.count.description}")
    private String descriptionCountOption;


    @Value("${option_data.role}")
    private String nameRoleOption;
    @Value("${option_data.role.description}")
    private String descriptionRoleOption;
    @Value("choice")
    private String nameChoiceOption;
    @Value(("выберите действие"))
    private String descriptionChoiceOption;


    private OptionData userOption;
    private OptionData countOption;
    private OptionData roleOption;
    private OptionData choiceOption;
 

    public void createChoiceOption(){
        choiceOption = new OptionData(OptionType.STRING,nameChoiceOption,descriptionChoiceOption,true)
                .addChoice("Добавить","add")
                .addChoice("Уменьшить","decrease")
                .addChoice("Установить","set");
    }

    public OptionData getChoiceOption(){
        if(choiceOption==null)
            createChoiceOption();

        return choiceOption;
    }

    public OptionData getRoleOption(){
        if(roleOption==null){
            createRoleOption();
        }
        return roleOption;
    }



    private void createRoleOption(){
        roleOption = new OptionData(OptionType.ROLE,nameRoleOption,descriptionRoleOption,true);

    }
    public OptionData getUserOption() {
       if(userOption==null)
           createUserOption();

       return  userOption;
    }

    private void createUserOption(){
        userOption = new OptionData(OptionType.USER,nameUserOption,descriptionUserOption,true);
    }


    public OptionData getCountOption() {
        if(countOption==null)
            createCountOption();

        return  countOption;
    }

    private void createCountOption(){
        countOption = new OptionData(OptionType.INTEGER,nameCountOption,descriptionCountOption,true).setMinValue(1);
    }

}
