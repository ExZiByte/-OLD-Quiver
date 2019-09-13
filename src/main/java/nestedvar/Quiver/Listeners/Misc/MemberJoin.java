package nestedvar.Quiver.Listeners.Misc;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import nestedvar.Quiver.Utilities.Database;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MemberJoin extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        Database db = new Database();
        db.connect();
        MongoCollection guilds = db.getCollection("guilds");
        MongoCollection members = db.getCollection("members");
        if(guilds.find(eq("members." + event.getMember().getUser().getId())).first() != null){
            System.out.println("User already in DB ignoring");
        } else {
            List<BasicDBObject> memberInformation = new ArrayList<>();
            if (!event.getMember().getUser().isBot()) {
                BasicDBObject thing = new BasicDBObject(event.getMember().getUser().getId(), new BasicDBObject("name", event.getMember().getUser().getName() + "#" + event.getMember().getUser().getDiscriminator()).append("level", 0).append("xp", 0));
                memberInformation.add(thing);
                guilds.updateOne(eq("guildID", event.getGuild().getId()), new Document("$set", memberInformation));
            }
        }
        if(members.find(eq("id", event.getMember().getUser().getId())).first() != null) {
            System.out.println("User already in DB ignoring");
        } else {
            if (!event.getMember().getUser().isBot()) {
                Document memberDoc = new Document("id", event.getMember().getUser().getId()).append("name", event.getMember().getUser().getName() + "#" + event.getMember().getUser().getDiscriminator()).append("facebook", "Not Set").append("instagram", "Not Set").append("youtube", "Not Set").append("twitch", "Not Set").append("mixer", "Not Set").append("imgur", "Not Set").append("tiktok", "Not Set").append("steam", "Not Set").append("blizzard", "Not Set").append("epic", "Not Set").append("twitter", "Not Set").append("origin", "Not Set").append("reddit", "Not Set").append("spotify", "Not Set").append("skype", "Not Set").append("xboxlive", "Not Set").append("psn", "Not Set").append("slack", "Not Set").append("snapchat", "Not Set").append("teamspeak", "Not Set").append("mumble", "Not Set").append("stackoverflow", "Not Set").append("tumblr", "Not Set").append("giphy", "Not Set").append("github", "Not Set").append("gitlab", "Not Set");
                members.insertOne(memberDoc);
            }
        }
    }
}