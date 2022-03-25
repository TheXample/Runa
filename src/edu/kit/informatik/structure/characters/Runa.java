package edu.kit.informatik.structure.characters;

import edu.kit.informatik.structure.abilities.Ability;
import edu.kit.informatik.structure.abilities.magical.Focus;
import edu.kit.informatik.structure.abilities.magical.defensive.Reflect;
import edu.kit.informatik.structure.abilities.magical.offensive.Water;
import edu.kit.informatik.structure.abilities.physical.defensive.Parry;
import edu.kit.informatik.structure.abilities.physical.offensive.Slash;
import edu.kit.informatik.structure.abilities.physical.offensive.Thrust;
import edu.kit.informatik.structure.dice.DiceType;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Runa.
 *
 * @author Hanne
 * @version 0.1
 */
public class Runa extends Character {

    private static final int MAXHEALTH = 50;

    private static final int MINFOCUS = 1;

    private static final String NAME = "Runa";

    private int maxFocus;

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
        this.dice = DiceType.D_FOUR;
        this.maxFocus = dice.getValue();
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
     * Gets class abilities.
     *
     * @param level the level
     * @return the class abilities
     */
    public List<Ability> getClassAbilities(int level) {
        switch (runaClass) {
            case MAGE: {
                return new ArrayList<>(List.of(new Focus(level), new Water(level)));
            }
            case WARRIOR: {
                return new ArrayList<>(List.of(new Thrust(level), new Parry(level)));
            }
            case PALADIN: {
                return new ArrayList<>(List.of(new Slash(level), new Reflect(level)));
            }
            default: {
                break;
            }
        }
        return new ArrayList<>();
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
     * Upgrade dice boolean.
     */
    public void upgradeDice() {
        switch (dice) {
            case D_FOUR: {
                dice = DiceType.D_SIX;
                maxFocus = dice.getValue();
                return;
            }
            case D_SIX: {
                dice = DiceType.D_EIGHT;
                maxFocus = dice.getValue();
                return;
            }
            case D_EIGHT: {
                dice = DiceType.D_TEN;
                maxFocus = dice.getValue();
                return;
            }
            case D_TEN: {
                dice = DiceType.D_Twelve;
                maxFocus = dice.getValue();
                return;
            }
            default: {
                break;
            }
        }
    }

    /**
     * Upgrade abilities.
     */
    public void upgradeAbilities() {
        level++;
        abilities.addAll(getClassAbilities(level));
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

    /**
     * Remove card.
     *
     * @param card the card
     */
    public void removeCard(Ability card) {
        for (int i = 0; i < abilities.size(); i++) {
            if (abilities.get(i).equalsAbility(card)) {
                abilities.remove(abilities.get(i));
            }
        }
    }

    @Override
    public void setFocusPoints(int focusPoints) {
        if (focusPoints < 1 || focusPoints > maxFocus) {
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

    /**
     * Gets maxhealth.
     *
     * @return the maxhealth
     */
    public static int getMaxhealth() {
        return MAXHEALTH;
    }
}
