package net.htlgkr.posplf.aufgabezwei;

import java.util.List;

public interface BoostAnalyzer {
    List<Boost> findBoostsWhereNameContains(String name);
    Boost findBoostWithItemId(int id);
    List<Boost> findBoostsWithTag(BoostTag boostTag);
    double sumDuration();
    double averageDuration();
    Boost findMostDifficultBoost();
    List<Boost> findDifficultyUnder(int difficulty);
    int boostTypeToNumber(Boost boost);
}
