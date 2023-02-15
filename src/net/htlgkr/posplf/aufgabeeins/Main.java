package net.htlgkr.posplf.aufgabeeins;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> names = MyReader.fileAsList("names.txt");

            //ForkJoinPool da jeder Task nur 5 Namen abarbeiten soll
            //kein Parameter da dann Runtime.getRuntime().availableProcessors() viele Prozessoren benutzt werden(so viele wie vorhanden)
            String longestName = new ForkJoinPool().invoke(
                    new NameLengthTask(
                            names,
                            5
                    )
            );
            System.out.println(longestName);
        } catch (IOException e) {
            System.err.println("Couldn't read file");
        }
    }
}