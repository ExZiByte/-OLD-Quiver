package nestedvar.Quiver.Listeners.Misc;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import nestedvar.Quiver.Utilities.Utils;
import nestedvar.Quiver.Utilities.Webhooks;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class QuiverJoin extends ListenerAdapter{ 

    public void onGuildJoin(GuildJoinEvent event){
        Utils utils = new Utils();
//      SQLDriver sql = new SQLDriver();
//
//    if(utils.haveIBeenHere(event)) {
//         try {
//             Webhooks webhook = new Webhooks(System.getenv("QUIVERWEBHOOK"));
//             webhook.addEmbed(new Webhooks.EmbedObject()
//                .setTitle("⚠Discord has triggered a GuildJoinEvent for a Guild already in the database")
//                .setDescription("Guild in question: " + event.getGuild().getName() + "\nGuild ID: " + event.getGuild().getId())
//             );
//             webhook.execute();
//            }catch (IOException e){
//             e.printStackTrace();
//         }
//        } else {
//            try{
//                Connection conn = sql.getConn();
//                Webhooks webhook = new Webhooks(System.getenv("QUIVERWEBHOOK"));
//                webhook.addEmbed(new Webhooks.EmbedObject()
//                    .setTitle("I've been added to a new guild!")
//                    .setDescription("Guild in question: " + event.getGuild().getName() + "\nGuild ID: " + event.getGuild().getId())
//                    .setColor(new Color(0x6bfa69))
//                );
//                webhook.execute();
//
//                Statement stmt = conn.createStatement();
//                stmt.execute("INSERT INTO `guilds` VALUES('"+ event.getGuild().getId() + "', '" + event.getGuild().getName() + "', 'Q!', 'true', 'false')");
//
//                conn.close();
//
//                for(Member member: event.getGuild().getMembers()){
//                    stmt = conn.createStatement();
//                    stmt.execute("");
//                }
//            } catch(SQLException | IOException e){
//                e.printStackTrace();
//            }
//        }
    }
}
