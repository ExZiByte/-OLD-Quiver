package nestedvar.Quiver.Utilities;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Count{

    public int getGuildsServiced(ReadyEvent event){
        int guildCount = 0;
        guildCount = event.getGuildAvailableCount();
        return guildCount;
    }
    
    public int getMemberCount(ReadyEvent event){
        int memberCount = 0;

        for(Guild guild: event.getJDA().getGuilds()){
            memberCount = guild.getMembers().size();
        }

        return memberCount;
    }
    
    public int getMemberCount(GuildMessageReceivedEvent event){
        int memberCount = 0;

        for(Guild guild: event.getJDA().getGuilds()){
            memberCount = guild.getMembers().size();
        }

        return memberCount;
    }
}
