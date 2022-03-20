package edu.kit.informatik.characters.monsters.one;

import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.magical.Focus;
import edu.kit.informatik.abilities.magical.offensive.WaterMonster;
import edu.kit.informatik.abilities.physical.defensive.Parry;
import edu.kit.informatik.abilities.physical.offensive.Bite;
import edu.kit.informatik.card.Stack;
import edu.kit.informatik.characters.Monster;

import java.util.List;

public class Spider extends Monster {

    private static final Stack ABILITYSTACK = new Stack(List.of(new Bite(1),
            new Parry( 1)));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.NONE;

    private static final String NAME = "Spider";

    private static final int HEALTHPOINTS = 15;


    public Spider() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }

    public Spider(int newHealth, int focusPoints) {
        super(NAME, newHealth, focusPoints, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
