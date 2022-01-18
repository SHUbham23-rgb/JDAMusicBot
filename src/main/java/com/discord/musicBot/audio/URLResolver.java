package com.discord.musicBot.audio;

import java.net.URI;
import java.net.URISyntaxException;

public class URLResolver {
    private static URI uri;
    public static String isUrl(String s) throws URISyntaxException {
        if (s.startsWith("https://") || s.startsWith("http://")) {
            //   System.out.println("link");
            return s;
        } else {
            uri = new URI("ytsearch:" + s);
            ///  System.out.println("true");
            //  System.out.println("uri "+uri);
            return String.valueOf(uri);
        }

    }

}
