package com.discord.musicBot.commands.commandsList;

import com.discord.musicBot.commands.CommandInterface;
import com.discord.musicBot.audio.AudioUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.discord.musicBot.Utilities.Utils.ping;

public class GetPosition implements CommandInterface {
    @Override
    public void commandInterpreter(List<String> args, @NotNull MessageReceivedEvent event) {
        long l = AudioUtils.getInstance().getPosition();
        ping(String.valueOf(l));

    }

    @Override
    public String setCommand() {
        return "getPosition";
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
