package edu.kit.informatik.characters;

import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.card.Stack;

import java.util.Queue;

public class Monster extends Character{

    private final Queue<Ability> abilities;

    private final MagicType primaryType;

    private final boolean isBoss;

    public Monster(String name, int healthPoints, int focusPoints, Queue<Ability> abilities, MagicType primaryType, boolean isBoss) {
        super(name, healthPoints, focusPoints);
        this.abilities = abilities;
        this.primaryType = primaryType;
        this.isBoss = isBoss;
    }

    public Queue<Ability> getAbilities() {
        return abilities;
    }

    public MagicType getPrimaryType() {
        return primaryType;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public Monster(Monster toCopy, int healthPoints) {
        super(toCopy.getName(), healthPoints, toCopy.getFocusPoints());
        this.abilities = toCopy.getAbilities();
        this.primaryType = toCopy.getPrimaryType();
        this.isBoss = toCopy.isBoss();
    }

    public Ability getNextMove() {
        return abilities.peek();
    }
}
