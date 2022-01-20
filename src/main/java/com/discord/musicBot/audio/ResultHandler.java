package com.discord.musicBot.audio;

import com.discord.musicBot.Utilities.Utils;
import com.discord.musicBot.listners.GuildMessageListener;
import com.discord.musicBot.threads.Threads;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.discord.musicBot.Utilities.Utils.*;


public class ResultHandler implements AudioLoadResultHandler {
    public static BlockingQueue<AudioTrack> tracks;
    public static boolean isPlaylist;
    private static Map<AudioTrack, String> audioTrackStringMap;
    private static AudioPlayer player;
    private static Map<AudioTrack, List<String>> map = new HashMap<>();
    private static List<String> list = new ArrayList<>();
   static List<AudioTrack> audioTrackList = new ArrayList<>();
    static List<AudioTrack> audioTrackList1  = new ArrayList<>();

    public ResultHandler(AudioPlayer audioPlayer) {
        tracks = new LinkedBlockingQueue<>();
        audioTrackStringMap = new HashMap<>();
        player = audioPlayer;

    }

    public static Map<AudioTrack, String> getAudioTrackStringMap() {
        return audioTrackStringMap;
    }

    public static void setAudioTrackStringMap(Map<AudioTrack, String> audioTrackStringMap) {
        ResultHandler.audioTrackStringMap = audioTrackStringMap;
    }

    private static void addToQueue(List<AudioTrack> playlist) {
        Thread addToQueueThread = new Thread(){
            @Override
            public void run() {
                System.out.println("in agg queuwe");
                if (isPlayList()) {
                    tracks.addAll(playlist);
                    audioTrackList.addAll(playlist);

                    EmbedBuilder builder = new EmbedBuilder();
                    Iterator<AudioTrack> iterator = playlist.iterator();
                    int i = 0;
                    while (iterator.hasNext()) {
                        builder.setTitle("Adding to the Queue")
                                .addField("Title", iterator.next().getInfo().title, true)
                                .addBlankField(true);
                        audioTrackStringMap.put(playlist.get(i), "gg" + getMessageRecievedEvent().getAuthor().getAsMention());
                        i++;
                    }
                    pings(builder);
                    playTrack();
                } else {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.addField("Searching for ",Utils.getMessage()
                            +" Requested by "+Utils.getMessageRecievedEvent().getMessage().getAuthor().getAsMention(),false);
                    embedBuilder.addField("Added to the queue ",playlist.get(0).getInfo().title+
                            " Requested by "+Utils.getMessageRecievedEvent().getAuthor().getAsMention(),true);
                    pings(embedBuilder);
                    System.out.println("in else");
                    tracks.offer(playlist.get(0));
                    audioTrackStringMap.put(playlist.get(0), "gg" + Utils.getMessageRecievedEvent().getAuthor().getAsMention());
                    audioTrackList1.add(playlist.get(0));
                    // AddingToQueueEmbed.addingToQueue(playlist.getTracks().get(0));
                    playTrack();
                }
            }
        };
       addToQueueThread.start();

    }

    public static Thread playTrack() {
        Thread playerThread = new Thread(){
            @Override
            public void run() {
                if (player.getPlayingTrack() == null) {
                    System.out.println("in play");
                    //player.playTrack(audioTrackList.get(Threads.incrementer()));
                    player.playTrack(tracks.poll());
                } else {
                    System.out.println("A track is playing");
                }

            }
        };
        playerThread.start();
        return playerThread;
    }

    public static void playTrackReversePlay(){
        Thread playerThread = new Thread(){
            @Override
            public void run() {
                if (AudioUtils.getInstance().isAnythingPlaying()) {
                    System.out.println("in play");

                } else {


                    System.out.println("in play else");
                }

            }
        };
        playerThread.start();
    }

    public static void setTracks(BlockingQueue<AudioTrack> tracks) {
        ResultHandler.tracks = tracks;
    }

    public static List<AudioTrack>  getAudioTrackList() {
        return audioTrackList;
    }

    public static void next() {
        if (tracks.isEmpty()) {
            ping("queue is empty");
            if (player.getPlayingTrack() == null) {
                Threads.voiceLeaveThread();
                GuildMessageListener.deleteMessagesInHistoryByBot();
            }
        } else {
            player.playTrack(tracks.poll());
        }
        /*

           if (audioTrackList.isEmpty()) {
            ping("queue is empty");
            if (player.getPlayingTrack() == null) {
                Threads.voiceLeaveThread();
                GuildMessageListener.deleteMessagesInHistoryByBot();
            }

        } else {
            player.playTrack(audioTrackList.get(Threads.incrementer()));
        }

         */
    }

    public static BlockingQueue<AudioTrack> getTracks() {
        return tracks;
    }

    public static boolean isPlayList() {
        String s = Utils.getMessage();
        if (s.contains("&list=")) {
            return true;
        } else if (s.contains("&start_radio=")) {
            return true;
        } else if (s.contains("playlist")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void trackLoaded(AudioTrack audioTrack) {
        //for when link is pasted
        audioTrackList.add(audioTrack);
        addToQueue(audioTrackList);

    }

    @Override
    public void playlistLoaded(AudioPlaylist audioPlaylist) {
        addToQueue(audioPlaylist.getTracks());
        // Audio.AudioUtils.getInstance().startTrack(audioPlaylist.getTracks().get(0));
        //AudioUtils.getInstance().getAudioPlayer().playTrack(audioPlaylist.getTracks().get(0));
    }

    @Override
    public void noMatches() {
        System.out.println("no matches found");

    }

    @Override
    public void loadFailed(FriendlyException e) {
        System.out.println("load failed");

    }

}