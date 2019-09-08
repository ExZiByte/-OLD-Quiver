package nestedvar.Quiver.Utilities;

import com.mongodb.client.MongoCollection;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Random;

import static com.mongodb.client.model.Filters.eq;

public class Utils {

    static Database db = new Database();

    public static String getPrefix(GuildMessageReceivedEvent event){
        String prefix;
        db.connect();
        MongoCollection<Document> guild = db.getCollection("guilds");
        prefix = guild.find(eq("guildID", event.getGuild().getId())).first().getString("prefix");
        db.close();
        return prefix;
    }

    public static void setPrefix(GuildMessageReceivedEvent event, String prefix){
        db.connect();

        MongoCollection<Document> guild = db.getCollection("guilds");
        String oldPrefix = guild.find(eq("guildID", event.getGuild().getId())).first().getString("prefix");

        Bson filter = new Document("prefix", oldPrefix);
        Bson newPrefix = new Document("prefix", prefix);
        Bson updatePrefix = new Document("$set", newPrefix);
        guild.findOneAndUpdate(filter, updatePrefix);

        db.close();

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
