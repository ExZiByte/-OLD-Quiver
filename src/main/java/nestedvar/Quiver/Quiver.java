package nestedvar.Quiver;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import nestedvar.Quiver.Listeners.Misc.MemberJoin;
import nestedvar.Quiver.Listeners.Misc.QuiverJoin;
import nestedvar.Quiver.Listeners.Misc.Ready;
import nestedvar.Quiver.Utilities.Resources;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;

import javafx.application.Application;
import javafx.stage.Stage;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.net.UnknownHostException;

public class Quiver{

    static ShardManager manager;
    static Resources res = new Resources();
    static final DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
    static final Object[] listeners = {new Ready(), new QuiverJoin(), new MemberJoin()};

    public Quiver(String token) throws LoginException, RateLimitedException, InterruptedException, UnknownHostException {
        builder.setToken(token);
        builder.addEventListeners(listeners);
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setGame(Game.watching("the loading bar fill!"));
        builder.setShardsTotal(-1);

        manager = builder.build();

        System.out.println("Bullseye Quiver is online!\n\nCurrently using " + res.getCPULoad() + "% CPU and " + res.getRAMUsage() + "MB of RAM");
    }

    public void destroy(){
        builder.removeEventListeners(listeners);
        manager.shutdown();
    }
}
