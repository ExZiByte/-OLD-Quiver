package nestedvar.Quiver.Utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Permissions {

    public static boolean isAdministrator(GuildMessageReceivedEvent event) {
        return event.getMember().hasPermission(Permission.ADMINISTRATOR);
    }
    public static boolean hasMuteOthers(GuildMessageReceivedEvent event) {
        return event.getMember().hasPermission(Permission.VOICE_MUTE_OTHERS);
    }
}