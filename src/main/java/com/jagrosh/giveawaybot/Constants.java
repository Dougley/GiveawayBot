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
package com.jagrosh.giveawaybot;

import java.awt.Color;
import java.time.OffsetDateTime;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 *
 * @author John Grosh (john.a.grosh@gmail.com)
 */
public class Constants 
{
    public static final OffsetDateTime STARTUP = OffsetDateTime.now();
    public static final int PRIZE_MAX   = 250;
    public static final long SERVER_ID  = 510181300406321153L;
    public static final String TADA     = "\uD83C\uDF89"; // 🎉
    public static final String WARNING  = "\uD83D\uDCA5"; // 💥
    public static final String ERROR    = "\uD83D\uDCA5"; // 💥
    public static final String YAY      = "\uD83C\uDF89"; //"<:yay:440620097543864320>";
    public static final String REACTION = "\uD83C\uDF89"; //"yay:440620097543864320";
    public static final Color  BLURPLE  = Color.decode("#7289DA");
    public static final String INVITE   = "https://giveaways.dougley.com/invite";
    public static final int MIN_TIME    = 10;
    public static final String WEBSITE  = "https://giveaways.dougley.com";
    public static final String SUPPORT  = "https://discord.gg/a8YVePH";
    public static final String OWNER    = "**Niflheim**#0064";
    public static final String GITHUB   = "https://github.com/Dougley/GiveawayBot";
    public static final String VERSION  = "2.4";
    public static final String PERMS    = "`Read Messages`, `Write Messages`, `Read Message History`, `Embed Links`, `Use External Emoji`, and `Add Reactions`";
    
    public static final boolean canSendGiveaway(TextChannel channel)
    {
        return channel.getGuild().getSelfMember().hasPermission(channel, Permission.MESSAGE_READ, Permission.MESSAGE_WRITE, 
                Permission.MESSAGE_HISTORY, Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_EXT_EMOJI, Permission.MESSAGE_ADD_REACTION);
    }
}
