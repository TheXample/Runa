package edu.kit.informatik.characters;

import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.card.Stack;

public class Monster {

    private final Stack abilities;

    private final String name;

    private final MagicType primaryType;

    private final MagicType secondaryType;

    public Monster(Stack abilities, String name, MagicType primaryType, MagicType secondaryType) {
        this.abilities = abilities;
        this.name = name;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }
}
