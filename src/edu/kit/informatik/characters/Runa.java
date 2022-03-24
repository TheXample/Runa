package edu.kit.informatik.characters;

import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.abilities.magical.Focus;
import edu.kit.informatik.abilities.magical.defensive.Reflect;
import edu.kit.informatik.abilities.magical.offensive.Water;
import edu.kit.informatik.abilities.physical.defensive.Parry;
import edu.kit.informatik.abilities.physical.offensive.Slash;
import edu.kit.informatik.abilities.physical.offensive.Thrust;
import edu.kit.informatik.dice.DiceType;

import java.util.List;

public class Runa extends Character{

    private static final int maxHealth = 50;

    private static final int minFocus = 1;

    private static final String NAME = "Runa";

    private DiceType dice;

    private List<Ability> abilities;

    private final RunaType runaClass;

    private int level;

    public Runa(RunaType runaClass) {
        super(NAME, maxHealth , minFocus);
        this.dice = DiceType.D_SIX;
        this.runaClass = runaClass;
        this.level = 1;
        setClass(runaClass, level);
    }

    private void setClass(RunaType runaClass, int level) {
        switch (runaClass) {
            case MAGE: {
                abilities = List.of(new Focus(level), new Water(level));
                break;
            }
            case WARRIOR: {
                abilities = List.of(new Thrust(level), new Parry(level));
                break;
            }
            case PALADIN: {
                abilities = List.of(new Slash(level), new Reflect(level));
                break;
            }
        }
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public RunaType getRunaClass() {
        return runaClass;
    }

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
        }
        return false;
    }

    public void upgradeAbilities() {
        for (Ability curr: abilities) {
            curr.upgrade();
        }
    }

    public void levelUP() {
        this.level = this.level + 1;
    }

    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }

    public DiceType getDice() {
        return dice;
    }
}
