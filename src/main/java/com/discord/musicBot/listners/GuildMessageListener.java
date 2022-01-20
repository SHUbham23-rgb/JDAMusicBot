package com.discord.musicBot.listners;


import com.discord.musicBot.Utilities.Utils;
import com.discord.musicBot.commands.Commandimpl;
import net.dv8tion.jda.api.entities.GuildMessageChannel;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GuildMessageListener extends ListenerAdapter {

    public static void autoDeleteMessage(GuildMessageChannel message, int delay) {
        Thread deleteMessageThread = new Thread() {
            @Override
            public void run() {
                MessageHistory history = new MessageHistory(message);
                List<Message> listRestAction = history.retrievePast(delay).complete();
                System.out.println(listRestAction.size());
                message.deleteMessages(listRestAction).queue();
            }
        };
        deleteMessageThread.start();
        if (deleteMessageThread.isAlive()) {
            System.out.println(deleteMessageThread.getName() + " " + deleteMessageThread.isAlive());
        } else if (deleteMessageThread.isInterrupted()) {
            System.out.println(deleteMessageThread.getName() + " " + "is intruppted");
        }
    }

    public static void deleteMessagesInHistoryByBot() {
        Thread botMessagesDeleteThread = new Thread() {
            @Override
            public void run() {
                try {
                    MessageHistory history = new MessageHistory(Utils.getGuildMessageChannel());
                    List<Message> listRestAction = history.retrievePast(50).complete();
                    List<Message> messageList = new ArrayList<>();
                    for (int i = 0; i < listRestAction.size(); i++) {
                        if (listRestAction.get(i).getAuthor().isBot()) {
                            messageList.add(listRestAction.get(i));
                        }
                    }

                    System.out.println(listRestAction.size());
                    Utils.getGuildMessageChannel().deleteMessages(messageList).queue();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }
        };
        botMessagesDeleteThread.start();
    }

    public static void deleteBotMessage() {
        Thread botMessagesDeleteThread = new Thread() {
            @Override
            public void run() {
                try {
                    MessageHistory history = new MessageHistory(Utils.getGuildMessageChannel());
                    List<Message> listRestAction = history.retrievePast(100).complete();
                    List<Message> listRestAction1 = history.retrievePast(100).complete();
                    List<Message> messageList = new ArrayList<>();
                    List<Message> messageList1 = new ArrayList<>();
                    for (int i = 0; i < listRestAction.size(); i++) {
                        if (listRestAction.get(i).getAuthor().isBot()) {
                            messageList.add(listRestAction.get(i));
                            messageList1.add(listRestAction1.get(i));
                        }
                    }

                    Utils.getGuildMessageChannel().deleteMessages(messageList).queue();
                    Utils.getGuildMessageChannel().deleteMessages(messageList1).queue();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }
        };
        botMessagesDeleteThread.start();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Utils.getINSTANCE().setMessageRecievedEvent(event);
        System.out.println(event.getMessage().getContentRaw());
          /*
        if (event.getMessage().getContentRaw().contains("!t")){
            final String[] args1 = Utils.getMessageRecievedEvent().getMessage().getContentRaw().replaceFirst(
                    "!", "").split("\s+");

            final String invoke = args1[0].toLowerCase();

            for (int i = 0; i < args1.length; i++) {
                System.out.println(args1[i]);
            }
        }

       else if (event.getMessage().getContentRaw().equalsIgnoreCase("j")) {
            Threads.VoiceJoinThread();
//83
//
//\Q means "start of literal text" (i.e. regex "open quote")
//\E means "end of literal text" (i.e. regex "close quote")
//
//Calling the Pattern.quote() method wraps the string in \Q...\E, which turns the text is into a regex literal.
            //(?i) starts case-insensitive mode
            //(?-i) turns off case-insensitive mode
            //16
            //
            //split("\\s+") will split the string into string of array with separator as space or multiple spaces.
            // \s+ is a regular expression for one or more spaces.


            final String[] args = Utils.getMessageRecievedEvent().getMessage().getContentRaw().replaceFirst(
                    "(?i)" + Pattern.quote("!"), "").split("\\s+");
            final String invoke = args[0].toLowerCase();
            System.out.println(invoke);
            System.out.println(args);


        }
        if (event.getMessage().getContentRaw().equalsIgnoreCase("l")) {
            Threads.voiceLeaveThread();
        }
        if (event.getMessage().getContentRaw().equalsIgnoreCase("ping")) {
            MessageAction pong = event.getChannel().sendMessage("pong");

            autoDeleteMessage(event.getGuildChannel(), 10);

        }
        if (event.getMessage().getContentRaw().contains("p")) {
            if (Threads.VoiceJoinThread()) {
                AudioUtils.getInstance().play(event.getMessage().getContentRaw().substring(2));
                System.out.println("hj" + event.getMessage().getContentRaw().substring(2));
            }

        }

 */
        if (event.getMessage().getContentRaw().startsWith("!")) {
            Commandimpl.getINSTANCE().handel(event.getMessage().getContentRaw(), event);
        }


    }

    public void allmessagedelete() {
        Utils.getMessageRecievedEvent().getChannel().delete().queueAfter(10, TimeUnit.SECONDS);
    }
}
