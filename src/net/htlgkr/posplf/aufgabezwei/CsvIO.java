package net.htlgkr.posplf.aufgabezwei;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvIO {
    public static List<Boost> readBoostCsv(String filename) throws IOException {
        return Files.readAllLines(
                Paths.get(filename))
                .stream()
                .skip(1)
                .map(Boost::deserialize)
                .toList();
    }
}
