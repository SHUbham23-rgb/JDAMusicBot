package com.discord.musicBot.threads;

import com.discord.musicBot.Utilities.Utils;
import com.discord.musicBot.commands.CommandInterface;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.ArrayList;
import java.util.List;

public class Threads {
    private static boolean isConnected;

    public static boolean VoiceJoinThread() {

        Thread voiceJoin = new Thread() {
            @Override
            public void run() {
                AudioChannel connectedChannel = Utils.getMessageRecievedEvent().getMember().getVoiceState().getChannel();
                AudioManager audioManager = Utils.getMessageRecievedEvent().getGuild().getAudioManager();
                if (connectedChannel == null) {
                    System.out.println("join the audio channel");
                    isConnected = false;

                } else if (audioManager.isConnected()) {
                    System.out.println("already connected");
                    isConnected = true;
                } else if (audioManager.isConnected() == false) {
                    audioManager.openAudioConnection(connectedChannel);
                    isConnected = true;
                }

            }
        };
        voiceJoin.start();
        return isConnected;
    }

    public static void voiceLeaveThread() {
        Thread voiceLeave = new Thread() {
            @Override
            public void run() {
                AudioChannel connectedChannel = Utils.getMessageRecievedEvent().getMember().getVoiceState().getChannel();
                AudioManager audioManager = Utils.getMessageRecievedEvent().getGuild().getAudioManager();
                if (connectedChannel == null) {
                    System.out.println("first join a channel");
                } else if (audioManager.isConnected()) {
                    audioManager.closeAudioConnection();
                } else if (audioManager.isConnected() == false) {
                    System.out.println("already left");
                }

            }
        };
        voiceLeave.start();
    }
    public static boolean isIsConnected(){
        AudioChannel connectedChannel = Utils.getMessageRecievedEvent().getMember().getVoiceState().getChannel();
        AudioManager audioManager = Utils.getMessageRecievedEvent().getGuild().getAudioManager();
        if (connectedChannel == null) {
            System.out.println("join the audio channel");
            return false;

        } else if (audioManager.isConnected()) {
            System.out.println("already connected");
           return true;
        } else if (audioManager.isConnected() == false) {

            return true;
        }else {
            return false;
        }
    }
    public static void getCommandClasses(){
        List<CommandInterface> commandInterfaceList = new ArrayList<>();

    }

}
