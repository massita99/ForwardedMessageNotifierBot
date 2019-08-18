package com.massita.coreapi.game;

public enum Resource {

    MONEY("\uD83D\uDCB0"),
    TIME("\u23F0"),
    LOYALITY("\uD83D\uDE3B"),
    NERVES("\uD83E\uDD2F"),
    TEAM("\uD83C\uDF08");

    public final String emoji;

    private Resource(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }

}
