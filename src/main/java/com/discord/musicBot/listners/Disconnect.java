package com.discord.musicBot.listners;

import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Disconnect extends ListenerAdapter {
    @Override
    public void onDisconnect(@NotNull DisconnectEvent event) {
        super.onDisconnect(event);
    }

    @Override
    public void onException(@NotNull ExceptionEvent event) {
        super.onException(event);
    }
}
