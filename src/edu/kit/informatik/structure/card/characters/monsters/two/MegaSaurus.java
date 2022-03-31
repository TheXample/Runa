package edu.kit.informatik.structure.card.characters.monsters.two;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.magical.Focus;
import edu.kit.informatik.structure.card.abilities.magical.offensive.Fire;
import edu.kit.informatik.structure.card.abilities.magical.offensive.Lightning;
import edu.kit.informatik.structure.card.abilities.physical.defensive.Block;
import edu.kit.informatik.structure.card.abilities.physical.offensive.Bite;
import edu.kit.informatik.structure.card.characters.Monster;
import edu.kit.informatik.structure.dice.Dice;
import edu.kit.informatik.structure.dice.DiceType;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The type Spider king.
 *
 * @author Hanne
 * @version 0.1
 */
public class MegaSaurus extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Bite(1),
            new Block(2), new Focus(2),
            new Fire(1), new Lightning(1)));

    private static final boolean ISBOSS = true;

    private static final MagicType PRIMARYTYPE = MagicType.LIGHTNING;

    private static final String NAME = "Mega Saurus";

    private static final int HEALTHPOINTS = 100;

    private static final DiceType DICETYPE = DiceType.D_TEN;


    /**
     * Instantiates a new Spider king.
     */
    public MegaSaurus(int seed) {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS,
                new Dice(DICETYPE, seed), DICETYPE.getValue(), HEALTHPOINTS);
    }
}
