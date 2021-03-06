package edu.kit.informatik.characters.monsters.one;

import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.magical.Focus;
import edu.kit.informatik.abilities.magical.offensive.WaterMonster;
import edu.kit.informatik.abilities.physical.defensive.Parry;
import edu.kit.informatik.abilities.physical.offensive.Bite;
import edu.kit.informatik.card.Stack;
import edu.kit.informatik.characters.Monster;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Spider extends Monster {

    private static final Queue<Ability> ABILITYSTACK = new LinkedList<>(List.of(new Bite(1),
            new Parry( 1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.NONE;

    private static final String NAME = "Spider";

    private static final int HEALTHPOINTS = 15;


    public Spider() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
