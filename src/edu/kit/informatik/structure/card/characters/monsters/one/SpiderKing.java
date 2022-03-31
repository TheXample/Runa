package edu.kit.informatik.structure.card.characters.monsters.one;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.physical.defensive.Block;
import edu.kit.informatik.structure.card.abilities.physical.offensive.Bite;
import edu.kit.informatik.structure.card.abilities.magical.Focus;
import edu.kit.informatik.structure.card.abilities.magical.offensive.Lightning;
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
public class SpiderKing extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Bite(1),
            new Block(1), new Focus(1), new Lightning(1)));

    private static final boolean ISBOSS = true;

    private static final MagicType PRIMARYTYPE = MagicType.LIGHTNING;

    private static final String NAME = "Spider King";

    private static final int HEALTHPOINTS = 50;

    private static final DiceType DICETYPE = DiceType.D_SIX;


    /**
     * Instantiates a new Spider king.
     */
    public SpiderKing(int seed) {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS,
                new Dice(DICETYPE, seed), DICETYPE.getValue(), HEALTHPOINTS);
    }
}
