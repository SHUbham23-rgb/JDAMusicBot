package com.discord.musicBot.commands.commandsList;

import com.discord.musicBot.audio.TrackScheduler;
import com.discord.musicBot.commands.CommandInterface;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Repreat implements CommandInterface {

   public static boolean altFlag;
    @Override
    public void commandInterpreter(List<String> args, @NotNull MessageReceivedEvent event) {

        altFlag = TrackScheduler.alt();
        System.out.println(altFlag);
       // GuildMessageListener.deleteMessagesInHistoryByBot();
    }

    @Override
    public String setCommand() {
        return "rep";
    }

    @Override
    public String getCommand() {
        return setCommand();
    }

    @Override
    public String getHelp() {
        return null;
    }
}
