package edu.kit.informatik.characters;

import edu.kit.informatik.card.Card;

public class Character extends Card {

    private int healthPoints;

    private int focusPoints;

    public Character(String name, int healthPoints, int focusPoints) {
        super(name);
        this.healthPoints = healthPoints;
        this.focusPoints = focusPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getFocusPoints() {
        return focusPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setFocusPoints(int focusPoints) {
        this.focusPoints = focusPoints;
    }
}
