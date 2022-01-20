package com.discord.musicBot.Utilities;

import com.discord.musicBot.listners.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildMessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.Button;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static JDA build;
    static Utils INSTANCE;
    private static MessageReceivedEvent MessageRecievedEvent;
    private static Thread mainBotTherad = new Thread() {
        @Override
        public void run() {
            try {
                Path path = Paths.get("C:\\Users\\Rick\\IdeaProjects\\JDABot\\sfc.txt");
                build = JDABuilder.createDefault(Files.readString(path)).build();
                add(build);
                build.setAutoReconnect(true);
                build.setRequestTimeoutRetry(true);
                build.awaitReady();
                System.out.println(build.getInviteUrl(Permission.ADMINISTRATOR));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static Utils getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Utils();
        }
        return INSTANCE;
    }

    public static void add(JDA jda) {
        Thread adding = new Thread() {
            @Override
            public void run() {
                jda.addEventListener(new Ready());
                jda.addEventListener(new GuildMessageListener());
                jda.addEventListener(new Disconnect());
                jda.addEventListener(new Reconnect());
                jda.addEventListener(new ShutdownListener());
                jda.addEventListener(new ButtonClicks());
                jda.addEventListener(new ExceptionEvent());

                System.out.println("Listners added");
            }
        };
        adding.start();
    }

    public static MessageReceivedEvent getMessageRecievedEvent() {
        return MessageRecievedEvent;
    }




    public void setMessageRecievedEvent(MessageReceivedEvent event) {
        MessageRecievedEvent = event;
    }

    public static GuildMessageChannel getGuildMessageChannel() {
        return getMessageRecievedEvent().getGuildChannel();
    }

    public static JDA getBuild() {
        return build;
    }

    public static Guild getGuild() {
        return getMessageRecievedEvent().getGuild();
    }

    public static Thread getMainBotThread() {
        return mainBotTherad;
    }

    public static void ping(String s) {
        Thread pingingThread = new Thread("pingingThread"){
            @Override
            public void run() {
                getMessageRecievedEvent().getGuildChannel().sendMessage(s).queue();
            }
        };
       pingingThread.start();

    }

    public static void pings(EmbedBuilder builder) {
        Thread pingingThread = new Thread("pingingThread"){
            @Override
            public void run() {
                getMessageRecievedEvent().getMessage().replyEmbeds(builder.build()).complete();
            }
        };
        pingingThread.start();


    }

    public static String getMessage() {
        String contentRaw = getMessageRecievedEvent().getMessage().getContentRaw();
        return contentRaw;
    }
    public static void get(EmbedBuilder builder){
        getMessageRecievedEvent().getGuildChannel().sendMessageEmbeds(builder.build()).setActionRow(Button.primary("ll", Emoji.fromUnicode("U+25B6"))).queue();
    }
}
