package edu.kit.informatik.structure.card.characters.monsters.one;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.physical.defensive.Block;
import edu.kit.informatik.structure.card.abilities.physical.offensive.Claw;
import edu.kit.informatik.structure.card.characters.Monster;
import edu.kit.informatik.structure.dice.Dice;
import edu.kit.informatik.structure.dice.DiceType;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The type Rat.
 *
 * @author Hanne
 * @version 0.1
 */
public class Rat extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Block(1),
            new Claw( 1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.NONE;

    private static final String NAME = "Rat";

    private static final int HEALTHPOINTS = 14;

    private static final DiceType DICETYPE = DiceType.D_FOUR;


    /**
     * Instantiates a new Rat.
     *
     * @param seed the seed
     */
    public Rat(int seed) {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS,
                new Dice(DICETYPE, seed), DICETYPE.getValue(), HEALTHPOINTS);
    }
}
