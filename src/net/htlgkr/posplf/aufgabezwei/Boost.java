package net.htlgkr.posplf.aufgabezwei;

import java.util.Arrays;
import java.util.List;

public record Boost(
    int exerciseId,
    String name,
    BoostType boostType,
    double duration,
    int difficulty,
    List<BoostTag> tagsList) {


    public static Boost deserialize(String cvsLine){
        String[] splitCvsLine = cvsLine.split(",");
        return new Boost(
                 Integer.parseInt(splitCvsLine[0])
                ,splitCvsLine[1]
                ,BoostType.valueOf(splitCvsLine[2])
                ,Double.valueOf(splitCvsLine[3])
                ,Integer.parseInt(splitCvsLine[4])
                ,getTagsFromString(splitCvsLine[5]));
    }

    private static List<BoostTag> getTagsFromString(String tagsString) {
        String[] tagsArray = tagsString.split(";");
        return Arrays.stream(tagsArray)
                .map(BoostTag::tagFromString)
                .toList();
    }
}
