package edu.kit.informatik.characters.monsters.two;

import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.physical.defensive.Block;
import edu.kit.informatik.abilities.physical.offensive.Bite;
import edu.kit.informatik.abilities.physical.offensive.Scratch;
import edu.kit.informatik.characters.Monster;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The type Frog.
 *
 * @author Hanne
 * @version 0.1
 */
public class Tarantula extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Bite(2), new Block(2),
            new Scratch(2)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.NONE;

    private static final String NAME = "Tarantula";

    private static final int HEALTHPOINTS = 33;


    /**
     * Instantiates a new Frog.
     */
    public Tarantula() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
