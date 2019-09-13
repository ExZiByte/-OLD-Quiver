package nestedvar.Quiver.Listeners.Backend;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import nestedvar.Quiver.Utilities.Database;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class RebuildDatabase extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Database db = new Database();
        if (args[0].equalsIgnoreCase("Q!rebuild")) {
            db.connect();
            MongoCollection guilds = db.getCollection("guilds");
            MongoCollection members = db.getCollection("members");
            List<BasicDBObject> memberInformation = new ArrayList<>();
            for (Member member : event.getGuild().getMembers()) {
                if (!member.getUser().isBot()) {
                    BasicDBObject thing = new BasicDBObject(member.getUser().getId(), new BasicDBObject("name", member.getUser().getName() + "#" + member.getUser().getDiscriminator()).append("level", 0).append("xp", 0));
                    if (members.find(eq("id", member.getUser().getId())).first() != null) {
                        System.out.println("User already in DB ignoring");
                    } else {
                        Document memberDoc = new Document("id", member.getUser().getId()).append("name", member.getUser().getName() + "#" + member.getUser().getDiscriminator()).append("facebook", "Not Set").append("instagram", "Not Set").append("youtube", "Not Set").append("twitch", "Not Set").append("mixer", "Not Set").append("imgur", "Not Set").append("tiktok", "Not Set").append("steam", "Not Set").append("blizzard", "Not Set").append("epic", "Not Set").append("twitter", "Not Set").append("origin", "Not Set").append("reddit", "Not Set").append("spotify", "Not Set").append("skype", "Not Set").append("xboxlive", "Not Set").append("psn", "Not Set").append("slack", "Not Set").append("snapchat", "Not Set").append("teamspeak", "Not Set").append("mumble", "Not Set").append("stackoverflow", "Not Set").append("tumblr", "Not Set").append("giphy", "Not Set").append("github", "Not Set").append("gitlab", "Not Set");
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
                    .append("isChannelSystemEnabled", true)
                    .append("isBanned", false);

            guilds.insertOne(guild);
            db.close();
        }

    }
}