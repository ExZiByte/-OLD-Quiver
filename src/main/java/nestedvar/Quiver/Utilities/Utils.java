package nestedvar.Quiver.Utilities;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class Utils {

    Database db = new Database();

    public static String getPrefix(GuildMessageReceivedEvent event){

        return null;
    }

    public static void setPrefix(GuildMessageReceivedEvent event){

    }

    public static String getSelfAvatar(GuildMessageReceivedEvent event){
        return event.getJDA().getSelfUser().getEffectiveAvatarUrl();
    }

    public static int getColor(){

        Random obj = new Random();
        int random_number = obj.nextInt(0xffffff + 1);

        return random_number;
    }

}
