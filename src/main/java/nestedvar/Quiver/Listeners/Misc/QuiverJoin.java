package nestedvar.Quiver.Listeners.Misc;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import nestedvar.Quiver.Utilities.Database;
import nestedvar.Quiver.Utilities.Utils;
import nestedvar.Quiver.Utilities.Webhooks;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bson.Document;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class QuiverJoin extends ListenerAdapter {
    Database db = new Database();

    public void onGuildJoin(GuildJoinEvent event) {
        Utils utils = new Utils();


        db.connect();
        MongoCollection guilds = db.getCollection("guilds");
        MongoCollection members = db.getCollection("members");

        if (guilds.find(eq("guildID", event.getGuild().getId())).first() != null) {
            try {
                Webhooks webhook = new Webhooks(System.getenv("QUIVERWEBHOOK"));
                webhook.addEmbed(new Webhooks.EmbedObject()
                        .setTitle("⚠Discord has triggered a GuildJoinEvent for a Guild already in the database")
                        .setDescription("Guild in question: " + event.getGuild().getName() + "\\nGuild ID: " + event.getGuild().getId())
                );
                webhook.execute();
                db.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            db.connect();
            try {
                Webhooks webhook = new Webhooks(System.getenv("QUIVERWEBHOOK"));
                webhook.addEmbed(new Webhooks.EmbedObject()
                        .setTitle("I've been added to a new guild!")
                        .setDescription("Guild in question: " + event.getGuild().getName() + "\\nGuild ID: " + event.getGuild().getId())
                        .setColor(new Color(0x6bfa69))
                );
                webhook.execute();
                List<BasicDBObject> memberInformation = new ArrayList<>();
                for (Member member : event.getGuild().getMembers()) {
                    if (!member.getUser().isBot()) {
                        BasicDBObject thing = new BasicDBObject(member.getUser().getId(), new BasicDBObject("name", member.getUser().getName() + "#" + member.getUser().getDiscriminator()).append("level", 0).append("xp", 0));
                        if(members.find(eq("id", member.getUser().getId())).first() != null){

                        } else {
                            Document memberDoc = new Document("id", member.getUser().getId()).append("name", member.getUser().getName() + "#" + member.getUser().getDiscriminator()).append("facebook", "Not Set").append("instagram", "Not Set").append("youtube", "Not Set").append("twitch", "Not Set").append("mixer", "Not Set").append("imgur", "Not Set").append("tiktok", "Not Set").append("steam", "Not Set").append("blizzard", "Not Set").append("epic", "Not Set").append("twitter", "Not Set").append("origin", "Not Set").append("reddit", "Not Set").append("spotify", "Not Set").append("skype", "Not Set").append("xboxlive", "Not Set").append("psn", "Not Set").append("slack", "Not Set").append("snapchat", "Not Set").append("teamspeak", "Not Set").append("mumble", "Not Set").append("stackoverflow", "Not Set").append("tumblr", "Not Set").append("giphy", "Not Set");
                            members.insertOne(memberDoc);
                        }
                        memberInformation.add(thing);
                    }
                }
                Document guild = new Document("guildID", event.getGuild().getId())
                        .append("guildName", event.getGuild().getName())
                        .append("prefix", "Q!")
                        .append("members", memberInformation)
                        .append("isBlacklisted", false)
                        .append("isChannelSystemEnabled", true);

                guilds.insertOne(guild);
                db.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//
////        } else {
//
//
////                Statement stmt = conn.createStatement();
////                stmt.execute("INSERT INTO `guilds` VALUES('"+ event.getGuild().getId() + "', '" + event.getGuild().getName() + "', 'Q!', 'true', 'false')");
////
////                conn.close();
////
////                for(Member member: event.getGuild().getMembers()){
////                    stmt = conn.createStatement();
////                    stmt.execute("");
////                }
////            } catch(SQLException | IOException e){
////                e.printStackTrace();
////            }
////        }
//        }
//    }
