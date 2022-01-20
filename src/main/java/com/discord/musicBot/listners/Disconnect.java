package com.discord.musicBot.listners;

import com.discord.musicBot.audio.AudioUtils;
import com.discord.musicBot.audio.ResultHandler;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Disconnect extends ListenerAdapter {
   public static BlockingQueue<AudioTrack> tracks;
   public static Map<AudioTrack, String> audioTrackStringMap;
   public static boolean isAnyThingPlaying;

    @Override
    public void onDisconnect(@NotNull DisconnectEvent event) {
        isAnyThingPlaying = AudioUtils.getInstance().isAnythingPlaying();
        if (isAnyThingPlaying){
            tracks = ResultHandler.getTracks();
            audioTrackStringMap = ResultHandler.getAudioTrackStringMap();
            System.out.println("size of queue is "+tracks.size()+" map  "+audioTrackStringMap.size());
        }



        super.onDisconnect(event);
    }

    @Override
    public void onException(@NotNull ExceptionEvent event) {
        super.onException(event);
    }
}
