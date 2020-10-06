## Giveaways+
Hold giveaways quickly and easily on your Discord server! Giveaways+ is powered by [JDA](https://github.com/DV8FromTheWorld/JDA/) and [JDA-Utilities](https://github.com/jagrosh/JDA-Utilities).<br>
<br>
![Example](http://i.imgur.com/bMjO8UA.png)

## Invite
If you'd like to add **Giveaways+** to your server, use the following link:<br>
🔗 **https://giveaways.dougley.com/invite**

## Usage
* **g+help** - Provides the bot's commands via Direct Message
* **g+create** - Interactive giveaway setup
* **g+start \<time> [winners] [prize]** - Starts a new giveaway in the current channel. Users can react with a 🎉 to enter the giveaway. The time can be in seconds, minutes, hours, or days. Specify the time unit with an "s", "m", "h", or "d", for example `30s` or `2h`. If you include a number of winners, it must be in the form #w, for example `2w` or `5w`.
* **g+reroll [messsageId]** - Re-rolls a winner. If you provided a message ID, it rerolls the giveaway at that ID. If you leave it blank, it looks in the current channel for the most recent giveaway and rerolls from that.
* **g+end [messageId]** - Ends a giveaway immediately. If you provided a message ID it will end the giveaway at that ID. If you leave it blank, it looks in the current channel for the most recent giveaway and ends that.
* **g+list** - Lists currently-running giveaways on the server.

## Self-Hosting
Self-hosting your own copy of this bot is not supported; the source code is provided here so users and other bot developers can see how the bot functions. No help will be provided for editing, compiling, or building any code in this repository, and any changes must be documented as per the [license](./LICENSE).