package net.htlgkr.posplf.aufgabezwei;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Main {
    private List<Boost> boostList;
    private BoostAnalyzer boostAnalyzer;

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.boostList = CsvIO.readBoostCsv("boosts.csv");
            main.initializeBoostAnalyzer();
            main.boostAnalyzer.findBoostsWhereNameContains("Shoulder").stream().forEach(System.out::println);
            System.out.println("----------------------------------------------------");
            System.out.println(main.boostAnalyzer.findBoostWithItemId(2));
            System.out.println("----------------------------------------------------");
            main.boostAnalyzer.findBoostsWithTag(new BoostTag("sex", "male")).stream().forEach(System.out::println);
            System.out.println("----------------------------------------------------");
            System.out.println(main.boostAnalyzer.sumDuration());
            System.out.println("----------------------------------------------------");
            System.out.println(main.boostAnalyzer.averageDuration());
            System.out.println("----------------------------------------------------");
            System.out.println(main.boostAnalyzer.findMostDifficultBoost());
            System.out.println("----------------------------------------------------");
            main.boostAnalyzer.findDifficultyUnder(6).stream().forEach(System.out::println);
            System.out.println("----------------------------------------------------");
            main.boostList.forEach(boost -> System.out.println(main.boostAnalyzer.boostTypeToNumber(boost)));
            System.out.println("----------------------------------------------------");
        } catch (IOException e) {
            System.err.println("Couldn't read file");
        }

        MyFunctionalInterface myFunctionalInterfaceAsLambda = (a, b) -> a + " + " + b;
        System.out.println(myFunctionalInterfaceAsLambda.foo(1,3));
        System.out.println("----------------------------------------------------");
        MyFunctionalInterfaceAsClass myFunctionalInterfaceAsClass = new MyFunctionalInterfaceAsClass();
        System.out.println(myFunctionalInterfaceAsClass.foo(1,3));
        System.out.println("----------------------------------------------------");
        System.out.println(useInnerAnonymousClass((a, b) -> a + " + " + b));
    }

    private static String useInnerAnonymousClass(MyFunctionalInterface myFunctionalInterface) {
        return myFunctionalInterface.foo(1, 3);
    }

    private  void initializeBoostAnalyzer() {
        boostAnalyzer = new BoostAnalyzer() {

            @Override
            public List<Boost> findBoostsWhereNameContains(String name) {
                return boostList.stream()
                        .filter(boost -> boost.name().contains(name))
                        .toList();
            }

            @Override
            public Boost findBoostWithItemId(int id) {
                return boostList.stream()
                        .filter(boost -> boost.exerciseId() == id)
                        .toList()
                        .get(0);
            }

            @Override
            public List<Boost> findBoostsWithTag(BoostTag boostTag) {
                return boostList.stream()
                        .filter(boost -> boost.tagsList().contains(boostTag))
                        .toList();
            }

            @Override
            public double sumDuration() {
                return boostList.stream()
                        .mapToDouble(Boost::duration)
                        .sum();
            }

            @Override
            public double averageDuration() {
                return boostList.stream()
                        .mapToDouble(Boost::duration)
                        .average()
                        .orElse(0);
            }

            @Override
            public Boost findMostDifficultBoost() {
                return boostList.stream()
                        .max(Comparator.comparingInt(Boost::difficulty))
                        .get();
            }

            @Override
            public List<Boost> findDifficultyUnder(int difficulty) {
                return boostList.stream()
                        .filter(boost -> boost.difficulty() < difficulty)
                        .toList();
            }

            @Override
            public int boostTypeToNumber(Boost boost) {
                //boost.boostType() kann nur JOB oder MINDFUL sein da sonst keine werte im Enum stehen
                return switch (boost.boostType()){
                    case JOB -> 1;
                    case MINDFUL -> 2;
                };
            }
        };
    }
}