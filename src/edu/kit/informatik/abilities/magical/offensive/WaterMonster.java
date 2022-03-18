package edu.kit.informatik.abilities.magical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.magicAbility;

public class WaterMonster extends magicAbility{

    private static final String DESCRIPTION = "8n + 2 magischer Schaden";

    private static final String NAME = "Water";

    public WaterMonster(int cost, int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, cost, MagicType.WATER, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        return 8 * this.getAbilityLevel() + 2;
    }
}
