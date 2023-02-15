package net.htlgkr.posplf.aufgabezwei;

public record BoostTag(
    String category,
    String name
) {

    public static BoostTag tagFromString(String tag){
        String[] parts = tag.split(":");
        return new BoostTag(
                parts[0],
                parts[1]
        );
    }
}
