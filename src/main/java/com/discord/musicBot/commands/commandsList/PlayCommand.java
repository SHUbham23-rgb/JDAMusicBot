package com.discord.musicBot.commands.commandsList;

import com.discord.musicBot.audio.AudioUtils;
import com.discord.musicBot.commands.CommandInterface;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.discord.musicBot.Utilities.Utils.ping;
import static com.discord.musicBot.threads.Threads.VoiceJoinThread;
import static com.discord.musicBot.threads.Threads.isIsConnected;

public class PlayCommand implements CommandInterface {
    @Override
    public void commandInterpreter(List<String> args, @NotNull MessageReceivedEvent event) {
        if (isIsConnected()){
            VoiceJoinThread();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < args.size(); i++) {
                s.append(args.get(i)+"+");
            }
            if (s.toString().startsWith("https://")){
                AudioUtils.getInstance().play(args.get(0));
                System.out.println("in play command link selector");
            }else {
                AudioUtils.getInstance().play(s.toString().substring(0,s.length()-1));
            }
        }else {
            ping("Join any voice channel to play music");
        }

    }

    @Override
    public String setCommand() {
        return "p";
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
