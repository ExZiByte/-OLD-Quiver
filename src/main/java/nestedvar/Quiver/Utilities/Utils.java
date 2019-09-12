package nestedvar.Quiver.Utilities;

import com.mongodb.client.MongoCollection;
import net.dv8tion.jda.core.entities.TextChannel;
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
        Document oldPrefix = guild.find(eq("guildID", event.getGuild().getId())).first();
        System.out.println(oldPrefix.getString("guildName"));

        Bson newPrefix = new Document("prefix", prefix);
        Bson updatePrefix = new Document("$set", newPrefix);
        guild.findOneAndUpdate(oldPrefix, updatePrefix);

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

    public static TextChannel getLogChannel(GuildMessageReceivedEvent event) {
        TextChannel channel;
        db.connect();
        MongoCollection<Document> guild = db.getCollection("guilds");
        String channelID = guild.find(eq("guildID", event.getGuild().getId())).first().getString("logChannelID");
        db.close();
        if(channelID.equalsIgnoreCase("Not Set")){
            return null;
        } else {
            channel = event.getGuild().getTextChannelById(channelID);
            return channel;
        }
    }

}
