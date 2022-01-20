package com.discord.musicBot.listners;

import com.discord.musicBot.commands.Commandimpl;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Ready extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("on ready");
        Commandimpl.getINSTANCE();

    }
}
