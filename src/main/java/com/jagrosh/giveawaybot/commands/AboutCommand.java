/*
 * Copyright 2017 John Grosh (john.a.grosh@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jagrosh.giveawaybot.commands;

import com.jagrosh.giveawaybot.Bot;
import com.jagrosh.giveawaybot.Constants;
import com.jagrosh.giveawaybot.entities.Giveaway;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.JDAUtilitiesInfo;
import java.util.List;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;

/**
 *
 * @author John Grosh (john.a.grosh@gmail.com)
 */
public class AboutCommand extends Command 
{
    private final static String STATS = "\uD83D\uDCCA"; // 📊
    private final static String LINKS = "\uD83C\uDF10"; // 🌐
    private final Bot bot;
    public AboutCommand(Bot bot) 
    {
        this.bot = bot;
        name = "about";
        aliases = new String[]{"info"};
        help = "shows info about the bot";
        guildOnly = false;
        botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
    }
    
    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        MessageBuilder mb = new MessageBuilder();
        mb.append(Constants.YAY+" All about **Giveaways+** "+Constants.YAY);
        //eb.setThumbnail("http://i.imgur.com/sCEbmKa.png");
        eb.setTitle("Hold giveaways quickly and easily!");
        eb.setDescription("Hello! I'm **Giveaways+**, and I'm here to make it as easy as possible to hold "
                + "giveaways on your Discord server! I was created by " + Constants.OWNER
                + "(<@191410544278765568>) using the [JDA](" + JDAInfo.GITHUB + ") library (" + JDAInfo.VERSION + ") and "
                + "[JDA-Utilities](" + JDAUtilitiesInfo.GITHUB + ") (" + JDAUtilitiesInfo.VERSION + "). Check out my "
                + "commands by typing `" + event.getClient().getPrefix() + "help`, and checkout my website at **" + Constants.WEBSITE + "**.");
        eb.addField(STATS + " Stats", event.getClient().getTotalGuilds() + " servers\n" + event.getJDA().getShardInfo().getShardTotal() + " shards", true);
        List<Giveaway> current = bot.getDatabase().giveaways.getGiveaways();
        eb.addField(Constants.TADA + " Giveaways", current==null ? "?" : current.size()+" right now!", true);
        eb.addField(LINKS + " Links", "[Website](" + Constants.WEBSITE + ")\n[Invite](" + Constants.INVITE + ")\n[Support](" + Constants.SUPPORT + ")", true);
        eb.setFooter("Last restart", null);
        eb.setTimestamp(Constants.STARTUP);
        eb.setColor(Constants.BLURPLE);
        mb.setEmbed(eb.build());
        event.getChannel().sendMessage(mb.build()).queue();
    }
    
}
