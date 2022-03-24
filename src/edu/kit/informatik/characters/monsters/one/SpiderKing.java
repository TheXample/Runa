package edu.kit.informatik.characters.monsters.one;

import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.physical.offensive.Bite;
import edu.kit.informatik.abilities.physical.defensive.Parry;
import edu.kit.informatik.abilities.magical.Focus;
import edu.kit.informatik.abilities.magical.offensive.LightningMonster;
import edu.kit.informatik.characters.Monster;

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

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Bite(1), new Parry(1),
            new Focus(1), new LightningMonster(1, 1)));

    private static final boolean ISBOSS = true;

    private static final MagicType PRIMARYTYPE = MagicType.LIGHTNING;

    private static final String NAME = "Spider King";

    private static final int HEALTHPOINTS = 50;


    /**
     * Instantiates a new Spider king.
     */
    public SpiderKing() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
