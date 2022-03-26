package edu.kit.informatik.structure.card.characters;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.Card;
import edu.kit.informatik.structure.card.abilities.MagicType;

/**
 * The type Character.
 *
 * @author Hanne
 * @version 0.1
 */
public class Character extends Card {

    /**
     * The Focus points.
     */
    protected int focusPoints;

    /**
     * The Health points.
     */
    protected int healthPoints;

    private Ability lastMove;

    private final MagicType primaryType;

    /**
     * Instantiates a new Character.
     *
     * @param name         the name
     * @param healthPoints the health points
     * @param focusPoints  the focus points
     * @param primaryType  the primary type
     */
    public Character(String name, int healthPoints, int focusPoints, MagicType primaryType) {
        super(name);
        this.healthPoints = healthPoints;
        this.focusPoints = focusPoints;
        this.primaryType = primaryType;
        lastMove = null;
    }

    /**
     * Gets health points.
     *
     * @return the health points
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Gets focus points.
     *
     * @return the focus points
     */
    public int getFocusPoints() {
        return focusPoints;
    }

    /**
     * Sets health points.
     *
     * @param healthPoints the health points
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
        if (this.healthPoints < 0) {
            this.healthPoints = 0;
        }
    }

    /**
     * Sets focus points.
     *
     * @param focusPoints the focus points
     */
    public void setFocusPoints(int focusPoints) {
        this.focusPoints = focusPoints;
    }

    /**
     * Equals boolean.
     *
     * @param input the input
     * @return the boolean
     */
    public boolean equalsCharacter(Character input) {
        return this.getName().equals(input.getName());
    }

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    public boolean isDead() {
        return getHealthPoints() == 0;
    }

    /**
     * Gets last move.
     *
     * @return the last move
     */
    public Ability getLastMove() {
        return lastMove;
    }

    /**
     * Sets last move.
     *
     * @param lastMove the last move
     */
    public void setLastMove(Ability lastMove) {
        this.lastMove = lastMove;
    }

    public MagicType getPrimaryType() {
        return primaryType;
    }
}
