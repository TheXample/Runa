package edu.kit.informatik.structure.card.characters;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.Card;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.dice.Dice;
import edu.kit.informatik.structure.dice.DiceType;

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

    private final int maxHealth;

    private int maxFocus;

    private Ability lastMove;

    private final MagicType primaryType;

    private Dice dice;

    /**
     * Instantiates a new Character.
     *
     * @param name         the name
     * @param healthPoints the health points
     * @param focusPoints  the focus points
     * @param primaryType  the primary type
     * @param dice         the dice
     * @param maxFocus     the max focus
     * @param maxHealth    the max health
     */
    public Character(String name, int healthPoints, int focusPoints, MagicType primaryType, Dice dice,
                     int maxFocus, int maxHealth) {
        super(name);
        this.healthPoints = healthPoints;
        this.focusPoints = focusPoints;
        this.primaryType = primaryType;
        this.dice = dice;
        this.maxFocus = maxFocus;
        this.maxHealth = maxHealth;
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

    /**
     * Gets primary type.
     *
     * @return the primary type
     */
    public MagicType getPrimaryType() {
        return primaryType;
    }

    /**
     * Gets dice.
     *
     * @return the dice
     */
    public Dice getDice() {
        return dice;
    }

    /**
     * Upgrade dice boolean.
     */
    public void upgradeDice() {
        dice.upgrade();
        this.maxFocus = dice.getType().getValue();
    }

    /**
     * Gets max focus.
     *
     * @return the max focus
     */
    public int getMaxFocus() {
        return maxFocus;
    }

    /**
     * Gets max health.
     *
     * @return the max health
     */
    public int getMaxHealth() {
        return maxHealth;
    }
}
