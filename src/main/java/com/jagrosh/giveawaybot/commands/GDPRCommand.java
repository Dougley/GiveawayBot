package com.jagrosh.giveawaybot.commands;

import com.jagrosh.giveawaybot.Bot;
import com.jagrosh.giveawaybot.entities.Giveaway;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.utils.MiscUtil;

import java.util.List;

public class GDPRCommand extends Command {
    private final Bot bot;

    public GDPRCommand(Bot bot) {
        this.bot = bot;
        this.name = "gdpr";
        this.help = "automated removal of user data respecting the GDPR\n" +
                "Example: `g+gdpr 198137282018934784`";
        this.ownerCommand = true;
        this.guildOnly = false;
        this.hidden = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getArgs().isEmpty()) {
            event.replyError("`g+gdpr <userid>`");
            return;
        }

        String[] parts = event.getArgs().split("\\s+", 2);

        long userId;

        try {
            userId = MiscUtil.parseSnowflake(parts[0]);
        } catch (Exception exception) {
            event.replyError("The provided userid isn't a valid snowflake following the regex pattern `\\d{1,20}` in radix 10.\n" +
                    exception.getMessage());
            return;
        }

        final long finalUserId = userId;
        event.getJDA().retrieveUserById(userId, false).queue(user -> {
            // Delete all giveaways by their users
            List<Giveaway> giveaways = bot.getDatabase().giveaways.getGiveaways(user);

            for (Giveaway giveaway : giveaways) {
                bot.deleteGiveaway(giveaway.channelId, giveaway.messageId);
                bot.getDatabase().giveaways.deleteGiveaway(giveaway.messageId);
            }

            event.replySuccess("Successfully removed userdata for userid: `" + user.getId() + "`");
        }, failure -> event.replyError("No user exists with id: `" + finalUserId + "`, can't delete their data."));
    }
}