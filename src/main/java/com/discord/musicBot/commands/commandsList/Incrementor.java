package com.discord.musicBot.commands.commandsList;

import com.discord.musicBot.audio.ResultHandler;
import com.discord.musicBot.commands.CommandInterface;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Incrementor implements CommandInterface {


    @Override
    public void commandInterpreter(List<String> args, @NotNull MessageReceivedEvent event) {
        ResultHandler.playTrackReversePlay();
    }

    @Override
    public String setCommand() {
        return "prev";
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
