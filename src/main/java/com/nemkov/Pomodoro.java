package com.nemkov;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Pomodoro {
    static final int PROGRESSBAR_SIZE=30;

    public static void main(String[] args){

        int workTime=25;
        int breakTime=5;
        int count=1;

        String[] cmd = new Scanner(System.in).nextLine().split(" ");

        for (int i = 0; i < cmd.length; i++) {
            switch (cmd[i]) {
                case "-help" -> printHelp();
                case "-w" -> workTime = Integer.parseInt(cmd[++i]);
                case "-b" -> breakTime = Integer.parseInt(cmd[++i]);
                case "-c" -> count = Integer.parseInt(cmd[++i]);
            }
        }
        long startTime = System.currentTimeMillis();
        for(int i=0; i< count; i++) {
            printProgress("work", workTime);
            printProgress("work", breakTime);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Pomodor таймер истек: " + (endTime-startTime)/(1000 * 60)+ " min");
    }
    public static void printHelp(){
        System.out.println("-w <time>: время работы в минутах");
        System.out.println("-b <time>: время отдыха в минутах");
        System.out.println("-c <time>: количество повторений");
    }
/*
    public static void Timer(int workTime, int breakTime){

    }
*/
    public static void printProgress(String nameProcess,int time){
        long startTime = System.currentTimeMillis();
        long endTime;
        int index=0;
        int prgs = time*60/PROGRESSBAR_SIZE;//одна * в секундах
        StringBuilder prgsbar = new StringBuilder("-".repeat(PROGRESSBAR_SIZE));

        for(int i=0; i<prgsbar.length();i++) {

            //System.out.print("\r" + nameProcess + ": [" + prgsbar + "]");

           do {
                endTime = System.currentTimeMillis();
                if ((endTime - startTime)%10 == 0){
                    switch(index){
                        case 0-> {
                            prgsbar.setCharAt(i, '/');
                            System.out.print("\r" + nameProcess + ": [" + prgsbar + "]");
                            index++;
                        }
                        case 1-> {
                            prgsbar.setCharAt(i, '|');
                            System.out.print("\r" + nameProcess + ": [" + prgsbar + "]");
                            index++;
                        }
                        case 2-> {
                            prgsbar.setCharAt(i, '-');
                            System.out.print("\r" + nameProcess + ": [" + prgsbar + "]");
                            index++;
                        }
                        case 3-> {
                            prgsbar.setCharAt(i, '\\');
                            System.out.print("\r" + nameProcess + ": [" + prgsbar + "]");
                            index++;
                        }
                        case 4-> {
                            prgsbar.setCharAt(i, '|');
                            System.out.print("\r" + nameProcess + ": [" + prgsbar + "]");
                            index=0;
                        }
                    }

                }
           } while ((endTime - startTime)/1000 != prgs);

           startTime = endTime;
           prgsbar.setCharAt(i, '*');

        }
        System.out.println("\r" + nameProcess + ": [" + "*".repeat(PROGRESSBAR_SIZE) + "]");
    }
}
