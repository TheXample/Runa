package edu.kit.informatik.characters.monsters.one;

import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.magical.Focus;
import edu.kit.informatik.abilities.magical.offensive.WaterMonster;
import edu.kit.informatik.card.Stack;
import edu.kit.informatik.characters.Monster;

import java.util.List;

public class Frog extends Monster {

    private static final Stack ABILITYSTACK = new Stack(List.of(new Focus(1),
            new WaterMonster(1, 1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.WATER;

    private static final String NAME = "Frog";

    private static final int HEALTHPOINTS = 16;


    public Frog() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }

    public Frog(int newHealth, int focusPoints) {
        super(NAME, newHealth, focusPoints, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
