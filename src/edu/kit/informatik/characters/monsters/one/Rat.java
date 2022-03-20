package edu.kit.informatik.characters.monsters.one;

import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.magical.Focus;
import edu.kit.informatik.abilities.magical.offensive.WaterMonster;
import edu.kit.informatik.abilities.physical.defensive.Parry;
import edu.kit.informatik.abilities.physical.offensive.Claw;
import edu.kit.informatik.card.Stack;
import edu.kit.informatik.characters.Monster;

import java.util.List;

public class Rat extends Monster {

    private static final Stack ABILITYSTACK = new Stack(List.of(new Parry(1),
            new Claw( 1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.NONE;

    private static final String NAME = "Rat";

    private static final int HEALTHPOINTS = 14;


    public Rat() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
