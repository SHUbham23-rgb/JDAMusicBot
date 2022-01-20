package com.discord.musicBot.threads;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public  class Generic <T> implements Serializable{
    private static Generic INSTANCE;

    public static Generic getinstance() {
        if (INSTANCE == null) {
            INSTANCE = new Generic();
        }
        return INSTANCE;
    }

    public void writeToCache(List<AudioTrack> t, String filename){
        byte[] serialize = SerializationUtils.serialize((Serializable) t);
        try {
            OutputStream outputStream = new FileOutputStream(filename);
            outputStream.write(serialize);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Object retriveCha(String fileNameOrFilePath) throws IOException {
        File f = new File(".txt");
        byte[] arr = new byte[(int)f.length()];

        InputStream inputStream;
        try {
            // read from a file
            FileInputStream fl = new FileInputStream(f);

            // Now creating byte array of same length as file


            // Reading file content to byte array
            // using standard read() method
          arr =   fl.readAllBytes();
            //fl.read(arr);
            // lastly closing an instance of file input stream
            // to avoid memory leakage
            fl.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Path path = Paths.get(".txt");
        List<AudioTrack> h
         =SerializationUtils.deserialize(Files.readAllBytes(path));
        return h;
    }




}
