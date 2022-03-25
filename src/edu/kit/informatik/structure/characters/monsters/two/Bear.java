package edu.kit.informatik.structure.characters.monsters.two;

import edu.kit.informatik.structure.abilities.Ability;
import edu.kit.informatik.structure.abilities.MagicType;
import edu.kit.informatik.structure.abilities.magical.defensive.Deflect;
import edu.kit.informatik.structure.abilities.physical.defensive.Block;
import edu.kit.informatik.structure.abilities.physical.offensive.Claw;
import edu.kit.informatik.structure.characters.Monster;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The type Frog.
 *
 * @author Hanne
 * @version 0.1
 */
public class Bear extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Claw(2),
            new Deflect(2), new Block(2)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.NONE;

    private static final String NAME = "Bear";

    private static final int HEALTHPOINTS = 40;


    /**
     * Instantiates a new Frog.
     */
    public Bear() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
