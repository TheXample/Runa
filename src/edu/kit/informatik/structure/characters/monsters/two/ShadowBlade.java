package edu.kit.informatik.structure.characters.monsters.two;

import edu.kit.informatik.structure.abilities.Ability;
import edu.kit.informatik.structure.abilities.MagicType;
import edu.kit.informatik.structure.abilities.magical.Focus;
import edu.kit.informatik.structure.abilities.magical.offensive.LightningMonster;
import edu.kit.informatik.structure.abilities.physical.offensive.Scratch;
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
public class ShadowBlade extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Scratch(2),
            new Focus(2), new LightningMonster(2, 2)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.LIGHTNING;

    private static final String NAME = "Shadow Blade";

    private static final int HEALTHPOINTS = 27;


    /**
     * Instantiates a new Frog.
     */
    public ShadowBlade() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
