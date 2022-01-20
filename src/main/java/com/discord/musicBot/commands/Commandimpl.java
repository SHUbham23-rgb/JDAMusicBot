package com.discord.musicBot.commands;


import com.discord.musicBot.commands.commandsList.*;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Commandimpl {


    static Map<String, CommandInterface> commandInterfaceMap = new HashMap<>();//for mapping with the enterd value
    private static Commandimpl INSTANCE;
    List<CommandInterface> commandInterfaceList = new ArrayList<>();
    public void get(){

    }

    public Commandimpl() {
        addCommand(new JoinCommand());
        addCommand(new Leave());
        addCommand(new PlayCommand());
        addCommand(new Pause());
        addCommand(new Resume());
        addCommand(new SetVol());
        addCommand(new GetVol());
        addCommand(new Stop());
        addCommand(new Next());
        addCommand(new GetPosition());
        addCommand(new Seek());
        addCommand(new AudioResamplingQuality());
        addCommand(new Repeat());
        addCommand(new Incrementor());


    }

    public static Commandimpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Commandimpl();
        }
        return INSTANCE;
    }

    public void handel(String message, @NotNull MessageReceivedEvent event) {
        Thread messageHandlerThread = new Thread("messageHandlerThread"){
            @Override
            public void run() {
                System.out.println("Insaide interface handle");
                final String[] args1 = message.replaceFirst(
                        "!", "").split("\s+");
                final String invoke = args1[0].toLowerCase();
                if (commandInterfaceMap.containsKey(invoke)) {
                    commandInterfaceMap.get(invoke).commandInterpreter(
                            Arrays.asList(args1).subList(1, args1.length),event);

            }
        }

        };
        messageHandlerThread.start();
    }

    public void addCommand(CommandInterface commandInterface) {
        Thread commandMapperThread = new Thread("commandMapperThread"){
            @Override
            public void run() {
                System.out.println("In add command " + commandInterface.getCommand());
                commandInterfaceMap.put(commandInterface.getCommand(), commandInterface);
            }
        };
       commandMapperThread.start();
    }
    /*
     public void addCommand(CommandInterface commandInterface){
        System.out.println("In add command "+ commandInterface.getCommand());
        commandInterfaceMap.put(commandInterface.getCommand(),commandInterface);
    }
    public void handel(String message){
        final String[] args1 = message.replaceFirst(
                "!", "").split("\s+");

        final String invoke = args1[0].toLowerCase();
        if (commandInterfaceMap.containsKey(invoke)) {
            commandInterfaceMap.get(invoke).setHandler(Utils.getMessageRecievedEvent(),
                    Arrays.asList(args1).subList(1, args1.length));
        }

     */
}
