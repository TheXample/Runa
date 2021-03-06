package edu.kit.informatik.abilities.magical.offensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.MagicAbility;

public class IceMonster extends MagicAbility {

    private static final String DESCRIPTION = "10n + 2 magischer Schaden";

    private static final String NAME = "Ice";

    public IceMonster(int cost, int abilityLevel) {
        super(NAME, AbilityType.OFFENSIVE, DESCRIPTION, false, cost, MagicType.ICE, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        return 10 * this.getAbilityLevel() + 2;
    }
}
