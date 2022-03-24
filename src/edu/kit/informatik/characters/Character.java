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
        if (this.healthPoints < 0) {
            this.healthPoints = 0;
        }
    }

    public void setFocusPoints(int focusPoints) {
        this.focusPoints = focusPoints;
    }

    public boolean equals(Character input) {
        return this.getName().equals(input.getName());
    }

    public boolean isDead() {
        return getHealthPoints() == 0;
    }
}
