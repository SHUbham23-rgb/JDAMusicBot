package com.discord.musicBot.listners;

import com.discord.musicBot.audio.AudioUtils;
import com.discord.musicBot.audio.ResultHandler;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.events.ResumedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Reconnect extends ListenerAdapter {
    @Override
    public void onReconnected(@NotNull ReconnectedEvent event) {
        System.out.println("on re connected"+event.getResponseNumber());

        super.onReconnected(event);
    }

    @Override
    public void onResumed(@NotNull ResumedEvent event) {
        System.out.println("on resumed ");
        if (Disconnect.isAnyThingPlaying){
            AudioUtils.getAudioPlayer().stopTrack();
            ResultHandler.getTracks().clear();
            ResultHandler.setTracks(Disconnect.tracks);
            ResultHandler.setAudioTrackStringMap(Disconnect.audioTrackStringMap);
            ResultHandler.playTrack();
        }

        super.onResumed(event);
    }
}
