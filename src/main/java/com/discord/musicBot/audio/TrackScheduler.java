package com.discord.musicBot.audio;

import com.discord.musicBot.Utilities.Utils;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.EmbedBuilder;


public class TrackScheduler extends AudioEventAdapter {
    public TrackScheduler(AudioPlayer player) {

    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {

    EmbedBuilder builder = new EmbedBuilder();
    builder.setTitle("now playing")
            .addField("NAme - ",track.getInfo().title,true)
            .addField("Requested By",ResultHandler.getAudioTrackStringMap().get(track)
                   .replaceFirst("gg",""), true);
    Utils.pings(builder);
        //PlayingTrackInfo.playingTrackInfo(track);
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            System.out.println("playing normally " + endReason);
            ResultHandler.next();
        }

    }
   static int i=0;
public static boolean alt(){
        if (i==0){
            i=1;
            return true;
        }else {
            i=0;
            return false;
        }

}
}