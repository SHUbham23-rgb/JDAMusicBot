package com.discord.musicBot.commands.commandsList;

import com.discord.musicBot.commands.CommandInterface;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import com.discord.musicBot.threads.Threads;

import java.util.List;

public class Leave implements CommandInterface {
    @Override
    public void commandInterpreter(List<String> args, @NotNull MessageReceivedEvent event) {
        Threads.voiceLeaveThread();
    }

    @Override
    public String setCommand() {
        return "leave";
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
