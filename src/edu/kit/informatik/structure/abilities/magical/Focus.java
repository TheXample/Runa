package edu.kit.informatik.structure.abilities.magical;

import edu.kit.informatik.structure.abilities.AbilityType;
import edu.kit.informatik.structure.abilities.MagicType;
import edu.kit.informatik.structure.abilities.MagicAbility;

/**
 * The type Focus.
 *
 * @author Hannes
 * @version 0.1
 */
public class Focus extends MagicAbility {

    private static final String DESCRIPTION = "Erhöhe Runas Fokus-Punkte vor ihrem nächsten Zug um"
            + ", außer durch nächsten Zug der Monster gebrochen";

    private static final String NAME = "Focus";

    private static final int COST = 0;

    /**
     * Instantiates a new Focus.
     *
     * @param abilityLevel the ability level
     */
    public Focus(int abilityLevel) {
        super(NAME, AbilityType.FOCUS, DESCRIPTION, false, COST, MagicType.NONE, abilityLevel);
    }

    @Override
    public int calculate(int focusPoints, MagicType opposingType) {
        return this.getAbilityLevel();
    }
}
