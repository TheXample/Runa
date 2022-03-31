package edu.kit.informatik.structure.card.characters.monsters.one;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.magical.Focus;
import edu.kit.informatik.structure.card.abilities.magical.offensive.Lightning;
import edu.kit.informatik.structure.card.characters.Monster;
import edu.kit.informatik.structure.dice.Dice;
import edu.kit.informatik.structure.dice.DiceType;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The type Skeleton.
 *
 * @author Hanne
 * @version 0.1
 */
public class Skeleton extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Focus(1),
            new Lightning(1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.LIGHTNING;

    private static final String NAME = "Skeleton";

    private static final int HEALTHPOINTS = 14;

    private static final DiceType DICETYPE = DiceType.D_FOUR;


    /**
     * Instantiates a new Skeleton.
     * @param seed
     */
    public Skeleton(int seed) {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS,
                new Dice(DICETYPE, seed), DICETYPE.getValue(), HEALTHPOINTS);
    }
}
