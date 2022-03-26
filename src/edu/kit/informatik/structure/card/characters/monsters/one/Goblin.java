package edu.kit.informatik.structure.card.characters.monsters.one;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.magical.defensive.Deflect;
import edu.kit.informatik.structure.card.abilities.physical.offensive.Smash;
import edu.kit.informatik.structure.card.characters.Monster;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The type Goblin.
 *
 * @author Hanne
 * @version 0.1
 */
public class Goblin extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Smash(1),
            new Deflect(1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.NONE;

    private static final String NAME = "Goblin";

    private static final int HEALTHPOINTS = 12;


    /**
     * Instantiates a new Goblin.
     */
    public Goblin() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
