package edu.kit.informatik.structure.characters.monsters.one;

import edu.kit.informatik.structure.abilities.Ability;
import edu.kit.informatik.structure.abilities.MagicType;
import edu.kit.informatik.structure.abilities.magical.Focus;
import edu.kit.informatik.structure.abilities.magical.offensive.IceMonster;
import edu.kit.informatik.structure.characters.Monster;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The type Ghost.
 *
 * @author Hanne
 * @version 0.1
 */
public class Ghost extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Focus(1),
            new IceMonster(1, 1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.ICE;

    private static final String NAME = "Ghost";

    private static final int HEALTHPOINTS = 15;


    /**
     * Instantiates a new Ghost.
     */
    public Ghost() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
