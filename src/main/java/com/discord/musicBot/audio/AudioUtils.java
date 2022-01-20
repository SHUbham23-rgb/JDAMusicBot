package com.discord.musicBot.audio;

import com.discord.musicBot.Utilities.Utils;
import com.sedmelluq.discord.lavaplayer.filter.equalizer.EqualizerFactory;
import com.sedmelluq.discord.lavaplayer.player.AudioConfiguration;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import static com.discord.musicBot.Utilities.Utils.ping;
import static com.discord.musicBot.audio.URLResolver.isUrl;


public class AudioUtils {
    public static AudioUtils Instance;
    private static AudioPlayerManager audioPlayerManager;
    private static AudioPlayer audioPlayer;
    EqualizerFactory equalizerFactory;

    public AudioUtils() {

        equalizerFactory = new EqualizerFactory();
        //creating a default player manager
        setSendingHandler();
        audioPlayerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
        AudioSourceManagers.registerLocalSource(audioPlayerManager);
        audioPlayerManager.getConfiguration().setResamplingQuality(AudioConfiguration.ResamplingQuality.HIGH);
        audioPlayerManager.getConfiguration().setOpusEncodingQuality(AudioConfiguration.OPUS_QUALITY_MAX);
        audioPlayerManager.getConfiguration().setFilterHotSwapEnabled(true);
        audioPlayer = audioPlayerManager.createPlayer();
        addListener(audioPlayer);
    }
    public static AudioPlayer getseperatePlayerinstsance(){
        return audioPlayerManager.createPlayer();
    }

    public static AudioUtils getInstance() {
        if (Instance == null) {
            Instance = new AudioUtils();
        }
        return Instance;
    }

    public static void setSendingHandler() {
        Utils.getGuild().getAudioManager().setSendingHandler(new AudioPlayerSendHandler(audioPlayer));
    }

    public static AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public AudioPlayerManager getAudioPlayerManager() {
        return audioPlayerManager;
    }

    private void addListener(AudioPlayer player) {
        player.addListener(new TrackScheduler(player));
    }

    public boolean isAnythingPlaying() {
        if (audioPlayer.getPlayingTrack() == null) {
            return false;
        } else {
            return true;
        }
    }

    public void play(String s) {
        Thread loaderThread = new Thread(){
            @Override
            public void run() {
                try {
                    audioPlayerManager.loadItem(isUrl(s), new ResultHandler(audioPlayer));
                    System.out.println("loading item " + isUrl(s));
                    //setMediumBassBoost(0.5f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //  System.out.println("i'm from else part");

            }
        };
loaderThread.start();
    }


    public void stop() {
        AudioUtils.getAudioPlayer().stopTrack();
    }

    public void next() {
        ResultHandler.next();
    }

    public void pause() {
        audioPlayer.setPaused(true);
    }

    public void resume() {
        audioPlayer.setPaused(false);
    }

    public String getVolume() {
        return String.valueOf(audioPlayer.getVolume());
    }

    public void shutdownPlayer() {
        audioPlayerManager.shutdown();
    }

    public void seVol(int n) {
        if (n <= 100) {
            audioPlayer.setVolume(n);
        }else {
            ping("Please enter a valid entry between 1-100");
        }

    }

    public void setMediumBassBoost(float f) {
        //factory.setGain(4, f);

        equalizerFactory.setGain(2, f);
        audioPlayer.setFilterFactory(equalizerFactory);

    }

    public String getBassBoost() {
        return String.valueOf(equalizerFactory.getGain(2));
    }

    public void searchResults() {
        AudioTrackInfo info = audioPlayer.getPlayingTrack().getInfo();
        ping(info.title);
    }


    public void seek(int inSecs) {
        long miliSec = inSecs * 1000;
        audioPlayer.getPlayingTrack().setPosition(miliSec);//in milliseconds

        // GuildMusicManager.audioPlayer.getPlayingTrack().setPosition(miliSec);
        //   ping(String.valueOf(GuildMusicManager.audioPlayer.getPlayingTrack().getPosition()));

    }

    public long getPosition() {
        return audioPlayer.getPlayingTrack().getPosition();
    }

    public void setBufferDuration(int duration) {
        audioPlayer.setFrameBufferDuration(duration);
    }

    public void setAudioResamplingQuality(String s) {
        switch (s) {
            case "high": {
                getAudioConfiguration().setResamplingQuality(AudioConfiguration.ResamplingQuality.HIGH);
            }
            case "low": {
                getAudioConfiguration().setResamplingQuality(AudioConfiguration.ResamplingQuality.LOW);
            }
            case "medium": {
                getAudioConfiguration().setResamplingQuality(AudioConfiguration.ResamplingQuality.MEDIUM);
            }

        }


    }

    public void setOpusEncodingQuality(String s) {
        switch (s) {
            case "high": {
                getAudioConfiguration().setOpusEncodingQuality(AudioConfiguration.OPUS_QUALITY_MAX);
            }
        }
    }

    public void setFilterHotSwapEnabled(boolean b) {
        getAudioConfiguration().setFilterHotSwapEnabled(true);
    }

    public AudioConfiguration getAudioConfiguration() {
        return audioPlayerManager.getConfiguration();
    }

}
