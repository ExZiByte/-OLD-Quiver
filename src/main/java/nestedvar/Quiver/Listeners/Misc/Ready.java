package nestedvar.Quiver.Listeners.Misc;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mongodb.MongoClientException;
import nestedvar.Quiver.Utilities.Count;
import nestedvar.Quiver.Utilities.Database;
import nestedvar.Quiver.Utilities.Utils;
import nestedvar.Quiver.Utilities.Webhooks;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Ready extends ListenerAdapter {

    public void onReady(ReadyEvent event) throws MongoClientException {
        Count counter = new Count();
        Utils utils = new Utils();
        Database db = new Database();
        try{
           db.connect();
           System.out.println("Successfully connecting to MongoDB Database");
        } catch (MongoClientException e){
            System.out.println("Welp i give up");
        }
        try {
            Webhooks webhook = new Webhooks(System.getenv("QUIVERWEBHOOK"));

            webhook.addEmbed(new Webhooks.EmbedObject()
                    .setDescription("HI")
            );

//            webhook.addEmbed(new Webhooks.EmbedObject()
//                .setTitle("Quiver Shard " + event.getJDA().getShardInfo().getShardId() + " is ready")
//                .setDescription("This shard is servicing \n\n```\n" + counter.getGuildsServiced(event) + " guilds\n" + counter.getMemberCount(event) + " members\n```")
//            );
            System.out.println("[Shard " + event.getJDA().getShardInfo().getShardId() + "] 🎯 Loaded! Servicing " + counter.getGuildsServiced(event) + " guilds and " + counter.getMemberCount(event) + " members");
            webhook.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        event.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);

        Game[] games = {Game.playing("with arrows!"), Game.watching("archery"), Game.playing("with my friends"), Game.listening("arrows whistling by!")};
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int game = random.nextInt(games.length);
                event.getJDA().getPresence().setGame(games[game]);
            }
        }, 0, 2, TimeUnit.MINUTES);
    }

}
