package com.discord.musicBot.listners;

import com.discord.musicBot.threads.Threads;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ShutdownListener extends ListenerAdapter {
    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        System.out.println("time of shut down "+event.getTimeShutdown());
        Threads.voiceLeaveThread();
        super.onShutdown(event);
    }
}
