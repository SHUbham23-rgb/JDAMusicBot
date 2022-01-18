package com.discord.musicBot.Utilities.buttons;

import com.discord.musicBot.Utilities.Utils;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.Button;

public class Buttons {
    private static Buttons INSTANCE;

    public static Buttons getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Buttons();
        }
        return INSTANCE;
    }

    public Button getPrimaryButton(String label, String unicode) {
        return Button.primary(label, Emoji.fromUnicode(unicode));
    }

    public Button getLinkButton(String url, String unicode) {
        return Button.link(url, Emoji.fromUnicode(unicode));
    }

    public Button getSuccessButton(String label, String unicode) {
        return Button.success(label, Emoji.fromUnicode(unicode));
    }

    public Button getSecondaryButton(String label, String unicode) {
       return Button.secondary(label, unicode);
    }
    public void displayButtons(){
        Utils.getMessageRecievedEvent().getGuildChannel().sendMessage("vg").setActionRow(getPrimaryButton("grdu","U+25B6")).complete();
    }

}
