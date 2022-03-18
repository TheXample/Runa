package edu.kit.informatik.characters.monsters;

import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.card.Stack;
import edu.kit.informatik.characters.Monster;

public class Spider_King extends Monster {

    private Stack abilityStack;

    public Spider_King(Stack abilities, String name, MagicType primaryType, MagicType secondaryType) {
        super(abilities, name, primaryType, secondaryType);
    }
}
