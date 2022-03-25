package edu.kit.informatik.structure.characters.monsters.one;

import edu.kit.informatik.structure.abilities.Ability;
import edu.kit.informatik.structure.abilities.MagicType;
import edu.kit.informatik.structure.abilities.magical.defensive.Deflect;
import edu.kit.informatik.structure.abilities.physical.offensive.Scratch;
import edu.kit.informatik.structure.characters.Monster;
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


    /**
     * Instantiates a new Mushroomlin.
     */
    public Mushroomlin() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
