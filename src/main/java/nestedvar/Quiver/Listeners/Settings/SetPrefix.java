package nestedvar.Quiver.Listeners.Settings;

import nestedvar.Quiver.Utilities.Permissions;
import nestedvar.Quiver.Utilities.Utils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.time.Instant;

public class SetPrefix extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Utils utils = new Utils();
        Permissions user = new Permissions();
        EmbedBuilder eb = new EmbedBuilder();
        EmbedBuilder success = new EmbedBuilder();
        if (args[0].equalsIgnoreCase(utils.getPrefix(event) + "setprefix")) {
            if (user.isAdministrator(event)) {
                utils.setPrefix(event, args[1]);
                eb.setDescription("Successfully set the prefix to `" + args[1] + "`");
                eb.setColor(new Color(utils.getColor()));
                eb.setFooter("Votrix Set Prefix", utils.getSelfAvatar(event));
                eb.setTimestamp(Instant.now());

                success.setDescription(event.getMember().getAsMention() + " set the prefix to `" + args[1] + "`");
                success.setColor(new Color(utils.getColor()));
                success.setFooter("Quiver Set Prefix Log", utils.getSelfAvatar(event));
                success.setTimestamp(Instant.now());

                event.getChannel().sendMessage(eb.build()).queue((message) -> {
                    eb.clear();
                    utils.getLogChannel(event).sendMessage(success.build()).queue((message2) -> {
                        success.clear();
                    });
                });
            }
        }
    }
}