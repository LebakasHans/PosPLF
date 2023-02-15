package net.htlgkr.posplf.aufgabeeins;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MyReader {
    static List<String> fileAsList(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
