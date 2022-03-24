package edu.kit.informatik.abilities.magical.defensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.MagicAbility;

public class Deflect extends MagicAbility {

    private static final String DESCRIPTION = "Reduziere nächsten Angriff um 11n+2 magischen Schaden";

    private static final String NAME = "Deflect";

    private static final int COST = 0;

    public Deflect(int abilityLevel) {
        super(NAME, AbilityType.DEFENSIVE, DESCRIPTION, false, COST, MagicType.NONE, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        if (focusPoints > 11 * this.getAbilityLevel() + 2) {
            return focusPoints - 10 * this.getAbilityLevel();
        }
        return 0;
    }
}
