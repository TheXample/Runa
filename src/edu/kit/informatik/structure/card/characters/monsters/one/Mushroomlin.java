package edu.kit.informatik.structure.card.characters.monsters.one;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.magical.defensive.Deflect;
import edu.kit.informatik.structure.card.abilities.physical.offensive.Scratch;
import edu.kit.informatik.structure.card.characters.Monster;
import edu.kit.informatik.structure.dice.Dice;
import edu.kit.informatik.structure.dice.DiceType;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The type Mushroomlin.
 *
 * @author Hanne
 * @version 0.1
 */
public class Mushroomlin extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Deflect(1),
            new Scratch(1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.NONE;

    private static final String NAME = "Mushroomlin";

    private static final int HEALTHPOINTS = 20;

    private static final DiceType DICETYPE = DiceType.D_FOUR;


    /**
     * Instantiates a new Mushroomlin.
     * @param seed
     */
    public Mushroomlin(int seed) {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS,
                new Dice(DICETYPE, seed), DICETYPE.getValue(), HEALTHPOINTS);
    }
}
