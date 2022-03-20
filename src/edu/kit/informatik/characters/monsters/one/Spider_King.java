package edu.kit.informatik.characters.monsters.one;

import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.physical.offensive.Bite;
import edu.kit.informatik.abilities.physical.defensive.Parry;
import edu.kit.informatik.abilities.magical.Focus;
import edu.kit.informatik.abilities.magical.offensive.LightningMonster;
import edu.kit.informatik.card.Stack;
import edu.kit.informatik.characters.Monster;

import java.util.List;

public class Spider_King extends Monster {

    private static final Stack ABILITYSTACK = new Stack(List.of(new Bite(1), new Parry(1),
            new Focus(1), new LightningMonster(1, 1)));

    private static final boolean ISBOSS = true;

    private static final MagicType PRIMARYTYPE = MagicType.LIGHTNING;

    private static final String NAME = "Spider King";

    private static final int HEALTHPOINTS = 50;


    public Spider_King() {
        super(NAME, HEALTHPOINTS, 0, ABILITYSTACK, PRIMARYTYPE, ISBOSS);
    }
}
