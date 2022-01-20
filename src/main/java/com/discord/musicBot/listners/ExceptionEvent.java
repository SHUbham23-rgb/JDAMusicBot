package com.discord.musicBot.listners;

import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ExceptionEvent extends ListenerAdapter {
    @Override
    public void onException(@NotNull net.dv8tion.jda.api.events.ExceptionEvent event) {
        event.getCause().getMessage();

        super.onException(event);
    }

    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        System.out.println("on exception coccoured");
        super.onShutdown(event);
    }

}
