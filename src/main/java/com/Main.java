package com;

import com.discord.musicBot.Utilities.Utils;
import org.apache.commons.lang3.ThreadUtils;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        if (isConnectedToInternet()) {
            System.out.println("connected");
           Utils.getMainBotThread().start();
        }else {
            System.out.println(isConnectedToInternet());
            System.out.println("Internet not available");
        }

        getRunningThreads();
    }


    public static boolean isConnectedToInternet(){
        boolean[] isConnected = new boolean[1];
                try {
                    URL url = new URL("https://www.geeksforgeeks.org/");
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    System.out.println("Connection Successful");
isConnected[0]=true;
                }
                catch (Exception e) {
                    System.out.println("Internet Not Connected");
                    isConnected[0] = false;
                }
        return isConnected[0];
        }



    public static void runCMD(){
        Process process;
        try {
            process = Runtime.getRuntime().exec("sfc");
            //int x = process.waitFor();
            // System.out.println(Arrays.toString(process.getInputStream().readAllBytes()));
            printResults(process);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void getRunningThreads(){
        for (Thread t : ThreadUtils.getAllThreads()) {
            System.out.println(t.getName() + ", " + t.isDaemon());
        }
    }
    public void runTasks(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
              System.gc();
                }
            };
        timer.scheduleAtFixedRate(timerTask,100,10000);
        };




    public static void printResults(@NotNull Process process) throws IOException {
        Scanner reader = new Scanner(new InputStreamReader(process.getInputStream()));
       // reader.next();
        StringBuilder builder = new StringBuilder();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sfc.txt"));
        while (reader.hasNext()) {
            //System.out.println(line.toString().replaceAll(" ",""));
            //builder.append(reader.next());
           // builder.append(reader.next()+"\n ");
            bufferedWriter.write(reader.nextLine()+"\n ".replaceAll("nul",","));

        }
        bufferedWriter.flush();
        bufferedWriter.close();
        reader.close();
      //  builder.replace(0,builder.length(), String.valueOf(builder.charAt(0)));


        System.out.println("builder "+builder);
    }
}
