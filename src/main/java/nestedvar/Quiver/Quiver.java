package nestedvar.Quiver;

import javax.security.auth.login.LoginException;

import nestedvar.Quiver.Listeners.Misc.*;
import nestedvar.Quiver.Utilities.Resources;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import java.net.UnknownHostException;

public class Quiver {
    static ShardManager manager;
    static Resources res = new Resources();
    static final DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException, UnknownHostException {
        builder.setToken(System.getenv("QUIVERTOKEN"));
        builder.addEventListeners(
                new Ready(),
                new QuiverJoin(),
                new MemberJoin()
        );
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setGame(Game.watching("the loading bar fill!"));
        builder.setShardsTotal(4);

        manager = builder.build();

        System.out.println("Bullseye Quiver is online!\n\nCurrently using " + res.getCPULoad() + "% CPU and " + res.getRAMUsage() + "MB of RAM");
    }

}
