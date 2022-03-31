package edu.kit.informatik.structure.card.characters.monsters.one;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.magical.Focus;
import edu.kit.informatik.structure.card.abilities.magical.offensive.Fire;
import edu.kit.informatik.structure.card.characters.Monster;
import edu.kit.informatik.structure.dice.Dice;
import edu.kit.informatik.structure.dice.DiceType;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The type Gorgon.
 *
 * @author Hanne
 * @version 0.1
 */
public class Gorgon extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Focus(1),
            new Fire(1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.FIRE;

    private static final String NAME = "Gorgon";

    private static final int HEALTHPOINTS = 13;

    private static final DiceType DICETYPE = DiceType.D_FOUR;


    /**
     * Instantiates a new Gorgon.
     *
     * @param seed the seed
     */
    public Gorgon(int seed) {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS,
                new Dice(DICETYPE, seed), DICETYPE.getValue(), HEALTHPOINTS);
    }
}
