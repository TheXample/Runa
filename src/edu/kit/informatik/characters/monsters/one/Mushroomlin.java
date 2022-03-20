package edu.kit.informatik.characters.monsters.one;

import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.magical.Focus;
import edu.kit.informatik.abilities.magical.defensive.Deflect;
import edu.kit.informatik.abilities.magical.offensive.WaterMonster;
import edu.kit.informatik.abilities.physical.offensive.Scratch;
import edu.kit.informatik.card.Stack;
import edu.kit.informatik.characters.Monster;

import java.util.ArrayList;
import java.util.List;

public class Mushroomlin extends Monster {

    private static final Stack ABILITYSTACK = new Stack(new ArrayList<>(List.of(new Frog(), new Ghost(), new Goblin(), new Gorgon(), new Mushroomlin(),
            new Skeleton(), new Rat(), new Spider())));

    private static final boolean ISBOSS = false;

    private static final MagicType PRIMARYTYPE = MagicType.NONE;

    private static final String NAME = "Mushroomlin";

    private static final int HEALTHPOINTS = 20;


    public Mushroomlin() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }

    public Mushroomlin(int newHealth, int focusPoints) {
        super(NAME, newHealth, focusPoints, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
