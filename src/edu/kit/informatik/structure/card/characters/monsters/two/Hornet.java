package edu.kit.informatik.structure.card.characters.monsters.two;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.magical.Focus;
import edu.kit.informatik.structure.card.abilities.magical.offensive.Fire;
import edu.kit.informatik.structure.card.abilities.physical.offensive.Scratch;
import edu.kit.informatik.structure.card.characters.Monster;
import edu.kit.informatik.structure.dice.Dice;
import edu.kit.informatik.structure.dice.DiceType;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The type Frog.
 *
 * @author Hanne
 * @version 0.1
 */
public class Hornet extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Scratch(2),
            new Focus(2), new Fire(1), new Fire(2)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.FIRE;

    private static final String NAME = "Hornet";

    private static final int HEALTHPOINTS = 32;

    private static final DiceType DICETYPE = DiceType.D_SIX;


    /**
     * Instantiates a new Frog.
     * @param seed
     */
    public Hornet(int seed) {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS,
                new Dice(DICETYPE, seed), DICETYPE.getValue(), HEALTHPOINTS);
    }
}
