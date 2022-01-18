package com.discord.musicBot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CommandInterface {



    void commandInterpreter(List<String> args, @NotNull MessageReceivedEvent event);


    String setCommand();

    String getCommand();

    String getHelp();


}
