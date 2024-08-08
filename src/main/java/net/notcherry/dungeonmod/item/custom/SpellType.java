package net.notcherry.dungeonmod.item.custom;

public enum SpellType {
    NONE,
    EXPLOSION,
    LIGHT,
    HEALING,
    WATER_WALKING;

    public static SpellType getByIndex(int index) {
        return values()[index % values().length];
    }
}