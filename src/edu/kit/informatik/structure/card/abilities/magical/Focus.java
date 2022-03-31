package edu.kit.informatik.structure.card.abilities.magical;

import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.MagicAbility;

/**
 * The type Focus.
 *
 * @author Hannes
 * @version 0.1
 */
public class Focus extends MagicAbility {

    private static final String DESCRIPTION = "Increases the Focus of the user by n at the next turn.";

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
