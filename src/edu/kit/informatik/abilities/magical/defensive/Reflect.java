package edu.kit.informatik.abilities.magical.defensive;

import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicType;
import edu.kit.informatik.abilities.MagicAbility;

/**
 * The type Reflect.
 *
 * @author Hannes
 * @version 0.1
 */
public class Reflect extends MagicAbility {

    private static final String DESCRIPTION = "Reflektiere bis zu 10n magischen Schaden. Der restliche "
            + "Schaden trifft Runa.";

    private static final String NAME = "Reflect";

    private static final int COST = 0;

    /**
     * Instantiates a new Reflect.
     *
     * @param abilityLevel the ability level
     */
    public Reflect(int abilityLevel) {
        super(NAME, AbilityType.DEFENSIVE, DESCRIPTION, false, COST, MagicType.NONE, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        if (focusPoints > 10 * this.getAbilityLevel()) {
            return focusPoints - 10 * this.getAbilityLevel();
        }
        return 0;
    }
}
