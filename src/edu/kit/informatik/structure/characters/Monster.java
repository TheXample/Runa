package edu.kit.informatik.structure.characters;

import edu.kit.informatik.structure.abilities.Ability;
import edu.kit.informatik.structure.abilities.MagicType;

import java.util.Queue;

/**
 * The type Monster.
 *
 * @author Hanne
 * @version 0.1
 */
public class Monster extends Character {

    private final Queue<Ability> abilities;

    private final MagicType primaryType;

    private final boolean isBoss;

    /**
     * Instantiates a new Monster.
     *
     * @param name         the name
     * @param healthPoints the health points
     * @param focusPoints  the focus points
     * @param abilities    the abilities
     * @param primaryType  the primary type
     * @param isBoss       the is boss
     */
    public Monster(String name, int healthPoints, int focusPoints, Queue<Ability> abilities,
                   MagicType primaryType, boolean isBoss) {
        super(name, healthPoints, focusPoints);
        this.abilities = abilities;
        this.primaryType = primaryType;
        this.isBoss = isBoss;
    }

    /**
     * Instantiates a new Monster.
     *
     * @param toCopy       the to copy
     * @param healthPoints the health points
     */
    public Monster(Monster toCopy, int healthPoints) {
        super(toCopy.getName(), healthPoints, toCopy.getFocusPoints());
        this.abilities = toCopy.getAbilities();
        this.primaryType = toCopy.getPrimaryType();
        this.isBoss = toCopy.isBoss();
    }

    /**
     * Gets abilities.
     *
     * @return the abilities
     */
    public Queue<Ability> getAbilities() {
        return abilities;
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
     * Is boss boolean.
     *
     * @return the boolean
     */
    public boolean isBoss() {
        return isBoss;
    }

    /**
     * Gets next move.
     *
     * @return the next move
     */
    public Ability getNextMove() {
        return abilities.peek();
    }

    /**
     * Rm top.
     */
    public void rmTop() {
        abilities.add(abilities.poll());
    }

}
