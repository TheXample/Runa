package edu.kit.informatik.structure.characters.monsters.one;

import edu.kit.informatik.structure.abilities.Ability;
import edu.kit.informatik.structure.abilities.MagicType;
import edu.kit.informatik.structure.abilities.magical.Focus;
import edu.kit.informatik.structure.abilities.magical.offensive.FireMonster;
import edu.kit.informatik.structure.characters.Monster;

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
            new FireMonster(1, 1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.FIRE;

    private static final String NAME = "Goorgon";

    private static final int HEALTHPOINTS = 13;


    /**
     * Instantiates a new Gorgon.
     */
    public Gorgon() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
