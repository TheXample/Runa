package edu.kit.informatik.characters;

import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.abilities.magical.Focus;
import edu.kit.informatik.abilities.magical.defensive.Reflect;
import edu.kit.informatik.abilities.magical.offensive.Water;
import edu.kit.informatik.abilities.physical.defensive.Parry;
import edu.kit.informatik.abilities.physical.offensive.Slash;
import edu.kit.informatik.abilities.physical.offensive.Thrust;
import edu.kit.informatik.dice.DiceType;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Runa.
 * @author Hanne
 * @version 0.1
 */
public class Runa extends Character {

    private static final int MAXHEALTH = 50;

    private static final int MINFOCUS = 1;

    private static final int MAXFOCUS = 6;

    private static final String NAME = "Runa";

    private DiceType dice;

    private List<Ability> abilities;

    private final RunaType runaClass;

    private int level;

    /**
     * Instantiates a new Runa.
     *
     * @param runaClass the runa class
     */
    public Runa(RunaType runaClass) {
        super(NAME, MAXHEALTH , MINFOCUS);
        this.dice = DiceType.D_SIX;
        this.runaClass = runaClass;
        this.level = 1;
        abilities = new ArrayList<>();
        setClass(runaClass, level);
    }

    private void setClass(RunaType runaClass, int level) {
        switch (runaClass) {
            case MAGE: {
                abilities.addAll(List.of(new Focus(level), new Water(level)));
                break;
            }
            case WARRIOR: {
                abilities.addAll(List.of(new Thrust(level), new Parry(level)));
                break;
            }
            case PALADIN: {
                abilities.addAll(List.of(new Slash(level), new Reflect(level)));
                break;
            }
            default: {
                break;
            }
        }
    }

    /**
     * Gets abilities.
     *
     * @return the abilities
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     * Gets runa class.
     *
     * @return the runa class
     */
    public RunaType getRunaClass() {
        return runaClass;
    }

    /**
     * Upgrade dice boolean.
     *
     * @return the boolean
     */
    public boolean upgradeDice() {
        switch (dice) {
            case D_SIX: {
                dice = DiceType.D_EIGHT;
                return true;
            }
            case D_EIGHT: {
                dice = DiceType.D_TEN;
                return true;
            }
            case D_TEN: {
                dice = DiceType.D_Twelve;
                return true;
            }
            default: {
                break;
            }
        }
        return false;
    }

    /**
     * Upgrade abilities.
     */
    public void upgradeAbilities() {
        for (int i = 0; i < abilities.size(); i++) {
            if (i < 2) {
                abilities.get(i).upgrade();
            }
        }
    }

    /**
     * Level up.
     */
    public void levelUP() {
        this.level = this.level + 1;
    }

    /**
     * Add ability.
     *
     * @param ability the ability
     */
    public void addAbility(Ability ability) {
        abilities.add(ability);
    }

    /**
     * Gets dice.
     *
     * @return the dice
     */
    public DiceType getDice() {
        return dice;
    }

    public void removeCard(Ability card) {
        for (int i = 0; i < abilities.size(); i++) {
            if (abilities.get(i).equalsAbility(card)) {
                abilities.remove(abilities.get(i));
            }
        }
    }

    @Override
    public void setFocusPoints(int focusPoints) {
        if (focusPoints < 1 || focusPoints > MAXFOCUS) {
            return;
        }
        this.focusPoints = focusPoints;
    }

    @Override
    public void setHealthPoints(int healthPoints) {
        if (healthPoints > MAXHEALTH) {
            this.healthPoints = MAXHEALTH;
            return;
        }
        if (healthPoints <= 0) {
            this.healthPoints = 0;
            return;
        }
        this.healthPoints = healthPoints;
    }
}
