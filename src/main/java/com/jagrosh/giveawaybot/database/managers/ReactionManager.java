package com.jagrosh.giveawaybot.database.managers;

import com.jagrosh.easysql.DataManager;
import com.jagrosh.easysql.DatabaseConnector;
import com.jagrosh.easysql.SQLColumn;
import com.jagrosh.easysql.columns.LongColumn;
import com.jagrosh.giveawaybot.entities.Giveaway;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class ReactionManager extends DataManager {

    private final Logger logger = LoggerFactory.getLogger(ReactionManager.class);
    private final boolean preventSelectAll = true; // just for safety

    public final static SQLColumn<Long> GUILD_ID    = new LongColumn("GUILD_ID",    false, 0L);
    public final static SQLColumn<Long> CHANNEL_ID  = new LongColumn("CHANNEL_ID",  false, 0L);
    public final static SQLColumn<Long> MESSAGE_ID  = new LongColumn("MESSAGE_ID",  false, 0L, true);
    public final static SQLColumn<Long> USER_ID     = new LongColumn("USER_ID",     false, 0L);

    public ReactionManager(DatabaseConnector connector) {
        super(connector, "REACTIONS");
    }

    public List<Long> getReactions() {
        logger.warn("Querying to return all reactions!");
        if (preventSelectAll) return Collections.emptyList();
        return getReactions(selectAll());
    }

    public List<Long> getReactions(Guild guild) {
        return getReactions(selectAll(GUILD_ID.is(guild.getIdLong())));
    }

    public List<Long> getReactions(TextChannel textChannel) {
        return getReactions(selectAll(CHANNEL_ID.is(textChannel.getIdLong())));
    }

    public List<Long> getReactions(Giveaway giveaway) {
        return getReactions(selectAll(MESSAGE_ID.is(giveaway.messageId)));
    }

    public List<Long> getReactions(Message message) {
        return getReactions(selectAll(MESSAGE_ID.is(message.getIdLong())));
    }

    public List<Long> getReactions(User user) {
        return getReactions(selectAll(USER_ID.is(user.getIdLong())));
    }

    public List<Long> getReactions(String selection) {
        if (preventSelectAll && selection.equals(selectAll())) return Collections.emptyList();
        return read(selection, results -> {
            List<Long> reactions = new Vector<>(50, 50);
            while (results.next())
                reactions.add(USER_ID.getValue(results));
            return reactions;
        }, Collections.emptyList());
    }
}
