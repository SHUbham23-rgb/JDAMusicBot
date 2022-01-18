package com.discord.musicBot.listners;

import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Reconnect extends ListenerAdapter {
    @Override
    public void onReconnected(@NotNull ReconnectedEvent event) {
        super.onReconnected(event);
    }
}
