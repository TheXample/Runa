package edu.kit.informatik.abilities.magical.defensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.MagicAbility;

/**
 * The type Deflect.
 *
 * @author Hannes
 * @version 0.1
 */
public class Deflect extends MagicAbility {

    private static final String DESCRIPTION = "Reduziere nächsten Angriff um 11n+2 magischen Schaden";

    private static final String NAME = "Deflect";

    private static final int COST = 0;

    /**
     * Instantiates a new Deflect.
     *
     * @param abilityLevel the ability level
     */
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
