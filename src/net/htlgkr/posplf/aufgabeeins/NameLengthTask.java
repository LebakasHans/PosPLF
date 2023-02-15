package net.htlgkr.posplf.aufgabeeins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveTask;

public class NameLengthTask extends RecursiveTask<String> {
    private List<String> names;
    private int maxProblemSizeInclusive;

    public NameLengthTask(List<String> names, int maxProblemSizeInclusive) {
        this.names = names;
        this.maxProblemSizeInclusive = maxProblemSizeInclusive;
    }

    @Override
    protected String compute() {
        if(names.size() <= maxProblemSizeInclusive) {
            return findLongestString(names);
        } else {
            NameLengthTask left = new NameLengthTask(names.subList(0, Math.ceilDiv(names.size(), 2)), maxProblemSizeInclusive);
            NameLengthTask right = new NameLengthTask(names.subList(Math.ceilDiv(names.size(), 2), names.size()), maxProblemSizeInclusive);
            //invokeAll: Führt alle Aufgaben aus.
            //invokeAny: Fuhrt alle Aufgaben aus, aber liefert das Ergebnis eines Ausführers, der als Erster fertig ist.

            //ich will alle Ergebnisse daher invokeAll
            invokeAll(left, right);

            return findLongestString(Arrays.asList(
                    left.join(),
                    right.join()
            ));
        }
    }

    private String findLongestString(List<String> strings){
        return strings.stream()
                .max(Comparator.comparingInt(String::length))
                .orElseThrow();
    }
}
