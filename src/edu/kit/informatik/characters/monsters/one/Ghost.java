package edu.kit.informatik.characters.monsters.one;

import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.magical.Focus;
import edu.kit.informatik.abilities.magical.offensive.IceMonster;
import edu.kit.informatik.abilities.magical.offensive.WaterMonster;
import edu.kit.informatik.card.Stack;
import edu.kit.informatik.characters.Monster;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Ghost extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Focus(1),
            new IceMonster(1, 1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.ICE;

    private static final String NAME = "Ghost";

    private static final int HEALTHPOINTS = 15;


    public Ghost() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
