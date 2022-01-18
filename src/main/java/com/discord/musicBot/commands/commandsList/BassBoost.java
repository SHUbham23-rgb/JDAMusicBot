package com.discord.musicBot.commands.commandsList;

import com.discord.musicBot.commands.CommandInterface;
import com.discord.musicBot.audio.AudioUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BassBoost implements CommandInterface {
    @Override
    public void commandInterpreter(List<String> args, @NotNull MessageReceivedEvent event) {
        AudioUtils.getInstance().setMediumBassBoost(Float.parseFloat(args.get(0)));
    }

    @Override
    public String setCommand() {
        return "setBass";
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
