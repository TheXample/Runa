package edu.kit.informatik.states;

import edu.kit.informatik.abilities.*;
import edu.kit.informatik.abilities.magical.defensive.Reflect;
import edu.kit.informatik.abilities.magical.offensive.Fire;
import edu.kit.informatik.abilities.magical.offensive.Ice;
import edu.kit.informatik.abilities.magical.offensive.Lightning;
import edu.kit.informatik.abilities.magical.offensive.Water;
import edu.kit.informatik.abilities.physical.defensive.Parry;
import edu.kit.informatik.abilities.physical.offensive.Pierce;
import edu.kit.informatik.abilities.physical.offensive.Slash;
import edu.kit.informatik.abilities.physical.offensive.Swing;
import edu.kit.informatik.abilities.physical.offensive.Thrust;
import edu.kit.informatik.characters.Character;
import edu.kit.informatik.characters.Monster;
import edu.kit.informatik.characters.Runa;
import edu.kit.informatik.characters.RunaType;
import edu.kit.informatik.characters.monsters.one.*;

import java.util.*;

public class RunasAdventure {

    private final Runa runa;

    private Queue<Monster> monsterStack;

    private Queue<Ability> abilities;

    private int currentFloor;

    private List<Monster> currentFight;

    private Ability lastMove;

    public RunasAdventure(RunaType runaClass) {
        currentFloor = 1;
        runa = new Runa(runaClass);
        currentFight = new ArrayList<>();
        this.monsterStack = new LinkedList<>();
        this.abilities = new LinkedList<>();
    }

    public void shuffleCards(long seedMonster, long seedAbilties) {
        initMonster(seedMonster);
        initAbilities(seedAbilties);
    }

    private void initMonster(long seed) {
        ArrayList<Monster> monsterList = new ArrayList<>();
        monsterList = new ArrayList<>(List.of(new Frog(), new Ghost(), new Goblin(), new Gorgon(), new Mushroomlin(),
                new Skeleton(), new Rat(), new Spider()));
        Collections.shuffle(monsterList, new Random(seed));
        monsterStack.addAll(monsterList);
    }

    private void initAbilities(long seed) {
        ArrayList<Ability> abilitiesList = new ArrayList<>(List.of(new Slash(1), new Swing(1),
                new Thrust(1), new Pierce(1), new Parry(1), new Reflect(1), new Water(1),
                new Ice(1), new Fire(1), new Lightning(1)));
        for (Ability curr: runa.getAbilities()) {
            for (Ability listAbility: abilitiesList) {
                if (listAbility.equals(curr)) {
                    abilitiesList.remove(listAbility);
                    break;
                }
            }
        }
        Collections.shuffle(abilitiesList, new Random(seed));
        abilities.addAll(abilitiesList);
    }

    public void enterRoom() {
        currentFight.add(monsterStack.poll());
        currentFight.add(monsterStack.poll());
        Statemachine.next();
    }

    public int usePhysicalAbility(Character attacker, Character target, PhysicalAbility attack, int dice) {
        int damage = 0;
        switch (attack.getType()) {
            case OFFENSIVE -> {
                checkFocus(attacker, attack);
                if (Statemachine.getCurrentState().equals(GameState.RUNATURN)) {
                    damage = setPhysicalDamage(currentFight.get(getOpponent(target)), attack, dice);
                    getOpponent(target);
                }
                else {
                    damage = setPhysicalDamage(runa, attack, dice);
                }
            }
            case DEFENSIVE -> {
                lastMove = attack;
            }
        }
        setStateMonster();
        checkDead();
        return damage;
    }

    private int getOpponent(Character target) {
        for (int i = 0; i < currentFight.size(); i++) {
            if (target.equals(currentFight.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private int setPhysicalDamage(Character target, PhysicalAbility attack, int dice) {
        int damage = 0;
        if (lastMove != null && !lastMove.getType().equals(AbilityType.FOCUS)) {
            damage = lastMove.calculate(attack.calculate(dice));
            target.setHealthPoints(target.getHealthPoints() - damage);
            lastMove = null;
        }
        else {
            damage = attack.calculate(dice);
            target.setHealthPoints(target.getHealthPoints() - damage);
        }
        return damage;
    }

    public int useMagicalAbility(Character attacker, Character target, MagicAbility attack) {
        int dmg = 0;
        switch (attack.getType()) {
            case OFFENSIVE -> {
                if (Statemachine.getCurrentState().equals(GameState.RUNATURN)) {
                    Monster targetMonster = currentFight.get(getOpponent(target));
                    checkFocus(targetMonster, attack);
                    if (lastMove != null && !lastMove.getType().equals(AbilityType.FOCUS)) {
                        dmg = lastMove.calculate(attack.calculate(runa.getFocusPoints(), targetMonster.getPrimaryType()));
                        targetMonster.setHealthPoints(targetMonster.getHealthPoints() - dmg);
                        lastMove = null;
                    }
                    else {
                        dmg = attack.calculate(runa.getFocusPoints(), targetMonster.getPrimaryType());
                        targetMonster.setHealthPoints(targetMonster.getHealthPoints()
                                - dmg);
                    }
                    currentFight.set(getOpponent(target), targetMonster);
                }
                else {
                    checkFocus(runa, attack);
                    if (lastMove != null && !lastMove.getType().equals(AbilityType.FOCUS)) {
                        dmg = lastMove.calculate(attack.calculate(currentFight.get(getOpponent(attacker)).getFocusPoints(), MagicType.NONE));
                        runa.setHealthPoints(runa.getHealthPoints() - dmg);
                        lastMove = null;
                    }
                    else {
                        dmg = attack.calculate(currentFight.get(getOpponent(attacker)).getFocusPoints(), MagicType.NONE);
                        runa.setHealthPoints(runa.getHealthPoints()
                                - dmg);
                    }
                }
            }
            case DEFENSIVE -> {
                lastMove = attack;
            }
        }
        setStateMonster();
        checkDead();
        return dmg;
    }

    private void setStateMonster() {
        if (Statemachine.getCurrentState().equals(GameState.RUNATURN)) {
            Statemachine.next();
            return;
        }
        if (currentFight.size() == 1) {
            Statemachine.setState(GameState.RUNATURN);
            return;
        }
        else if (currentFight.size() == 0) {
            Statemachine.setState(GameState.FIGHTWON);
            return;
        }
        Statemachine.next();
    }

    private void checkDead() {
        currentFight.removeIf(Character::isDead);
        if (runa.isDead()) {
            Statemachine.lost();
        }
    }

    private void checkFocus(Character caster, Ability attack) {
        if (!attack.isBreaksFocus() && lastMove != null && lastMove.getType().equals(AbilityType.FOCUS)) {
            caster.setFocusPoints(lastMove.calculate(caster.getFocusPoints()));
        }
    }

    public Runa getRuna() {
        return runa;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public List<Monster> getCurrentFight() {
        return currentFight;
    }

    public GameState getState() {
        return Statemachine.getCurrentState();
    }
}
