package by.tux.spring160.models.enums;

public enum Type {
    CHRISTMAS_TREES("Елка"),
    TOYS("Игрушка"),
    DECORATIONS("Украшение"),
    LIGHTS("Гирлянда"),
    PRESENTS("Подарок");

    Type(String ruValue){
        this.ruValue = ruValue;
    }

    public final String ruValue;
}