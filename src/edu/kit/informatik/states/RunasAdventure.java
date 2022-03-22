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

    private Runa runa;

    private Queue<Monster> monsterStack;

    private Queue<Ability> abilities;

    private int currentFloor;

    private Monster currentFight;

    private Ability lastMove;

    public RunasAdventure(RunaType runaClass) {
        currentFloor = 1;
        runa = new Runa(runaClass);
        this.monsterStack = new LinkedList<>();
        this.abilities = new LinkedList<>();
        Statemachine.next();
        Statemachine.next();
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

    public Monster enterRoom() {
        currentFight = monsterStack.poll();
        Statemachine.next();
        return currentFight;
    }

    public Character usePhysicalAbility(PhysicalAbility attack) {
        switch (attack.getType()) {
            case OFFENSIVE -> {
                if (Statemachine.getCurrentState().equals(GameState.RUNATURN)) {
                    setPhysicalDamage(currentFight, attack);
                    return currentFight;
                }
                else {
                    setPhysicalDamage(runa, attack);

                    return runa;
                }
            }
            case DEFENSIVE -> {
                lastMove = attack;
            }
        }
        return null;
    }

    private void setPhysicalDamage(Character target, PhysicalAbility attack) {
        if (lastMove != null && !lastMove.getType().equals(AbilityType.FOCUS)) {
            target.setHealthPoints(lastMove.calculate(target.getHealthPoints() - attack.calculate(0)));
            lastMove = null;
        }
        else {
            target.setHealthPoints(target.getHealthPoints() - attack.calculate(0));
        }
    }

    public Character useMagicalAbility(MagicAbility attack) {
        switch (attack.getType()) {
            case OFFENSIVE -> {
                if (Statemachine.getCurrentState().equals(GameState.RUNATURN)) {
                    if (lastMove != null && !lastMove.getType().equals(AbilityType.FOCUS)) {
                        currentFight.setHealthPoints(lastMove.calculate(currentFight.getHealthPoints()
                                - attack.calculate(runa.getFocusPoints(), currentFight.getPrimaryType())));
                        lastMove = null;
                    }
                    else {
                        currentFight.setHealthPoints(currentFight.getHealthPoints()
                                - attack.calculate(runa.getFocusPoints(), currentFight.getPrimaryType()));
                    }
                    return currentFight;
                }
                else {
                    if (lastMove != null && !lastMove.getType().equals(AbilityType.FOCUS)) {
                        runa.setHealthPoints(lastMove.calculate(runa.getHealthPoints()
                                - attack.calculate(currentFight.getFocusPoints(), MagicType.NONE)));
                        lastMove = null;
                    }
                    else {
                        runa.setHealthPoints(runa.getHealthPoints()
                                - attack.calculate(currentFight.getFocusPoints(), MagicType.NONE));
                    }
                    return runa;
                }
            }
            case DEFENSIVE -> {
                lastMove = attack;
            }
        }
        return null;
    }

    private void checkFocus(Character caster, Ability attack) {
        if (!attack.isBreaksFocus() && lastMove.getType().equals(AbilityType.FOCUS)) {
            caster.setFocusPoints(lastMove.calculate(caster.getFocusPoints()));
        }
    }

    public Runa getRuna() {
        return runa;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Monster getTop() {
        return monsterStack.peek();
    }

    public Monster getCurrentFight() {
        return currentFight;
    }

    public GameState getState() {
        return Statemachine.getCurrentState();
    }
}
