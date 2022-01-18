package com.discord.musicBot.commands.commandsList;

import com.discord.musicBot.commands.CommandInterface;
import com.discord.musicBot.threads.Threads;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JoinCommand implements CommandInterface {

    @Override
    public void commandInterpreter(List<String> args, @NotNull MessageReceivedEvent event) {
        Threads.VoiceJoinThread();
    }


    @Override
    public String setCommand() {
        return "join";
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
