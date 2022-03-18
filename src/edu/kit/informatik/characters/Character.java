package edu.kit.informatik.characters;

import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.card.Card;

import java.util.List;

public class Character extends Card {

    private int healthPoints;

    private int focusPoints;

    private final List<Ability> abilities;

    public Character(String name, int healthPoints, int focusPoints, List<Ability> abilities) {
        super(name);
        this.healthPoints = healthPoints;
        this.focusPoints = focusPoints;
        this.abilities = abilities;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getFocusPoints() {
        return focusPoints;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setFocusPoints(int focusPoints) {
        this.focusPoints = focusPoints;
    }
}
