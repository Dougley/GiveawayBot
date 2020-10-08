package com.jagrosh.giveawaybot.commands;

import com.jagrosh.giveawaybot.Bot;
import com.jagrosh.giveawaybot.entities.Giveaway;
import com.jagrosh.giveawaybot.rest.RestJDA;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.List;

public class GDPRCommand extends Command {
    private final Bot bot;

    public GDPRCommand(Bot bot) {
        this.bot = bot;
        this.name = "gdpr";
        this.help = "automated gdpr removal of user data";
        this.ownerCommand = true;
        this.guildOnly = false;
        this.hidden = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getArgs().isEmpty()) {
            // TODO: Change message to be better
            event.replyError("No arguments specified!");
            return;
        }

        String[] parts = event.getArgs().split("\\s+", 2);

        long userId = Long.parseLong(parts[0]);

        RestJDA rest = bot.getRestJDA();

        rest.retrieveUserById(userId).queue(user -> {
            // Delete all giveaways by their users
            List<Giveaway> giveaways = bot.getDatabase().giveaways.getGiveaways(user);

            for (Giveaway giveaway : giveaways) {
                bot.deleteGiveaway(giveaway.channelId, giveaway.messageId);
                bot.getDatabase().giveaways.deleteGiveaway(giveaway.messageId);
            }

            // TODO: Ask Dougley what else has to be deleted (probably donator status)
        }, failure -> {
            event.replyError("User does not exist by their ID, can't delete their data.");
        });
    }
}