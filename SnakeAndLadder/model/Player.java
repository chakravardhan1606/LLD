package model;

import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private String playerId;
    private String playerName;
    private Color color;

    public Player(String playerId, String playerName, Color color) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.color = color;
    }

    public int roll() {
        return ThreadLocalRandom.current().nextInt(1, 7);
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
