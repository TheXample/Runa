package edu.kit.informatik.states;

import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.abilities.PhysicalAbility;
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

    public RunasAdventure(RunaType runaClass) {
        currentFloor = 1;
        runa = new Runa(runaClass);
        Statemachine.next();
        initMonster(currentFloor);
        initAbilities(currentFloor);
        Statemachine.next();
    }

    private void initMonster(int floor) {
        ArrayList<Monster> monsterList = new ArrayList<>();
        if (floor == 1) {
             monsterList = new ArrayList<>(List.of(new Frog(), new Ghost(), new Goblin(), new Gorgon(), new Mushroomlin(),
                    new Skeleton(), new Rat(), new Spider()));
        }
        else {

        }
        Collections.shuffle(monsterList);
        monsterStack.addAll(monsterList);
    }

    private void initAbilities(int floor) {
        ArrayList<Ability> abilitiesList = new ArrayList<>(List.of(new Slash(floor), new Swing(floor),
                new Thrust(floor), new Pierce(floor), new Parry(floor), new Reflect(floor), new Water(floor),
                new Ice(floor), new Fire(floor), new Lightning(floor)));
        for (Ability curr: runa.getAbilities()) {
            for (Ability listAbility: abilitiesList) {
                if (listAbility.equals(curr)) {
                    abilitiesList.remove(listAbility);
                    break;
                }
            }
        }
        Collections.shuffle(abilitiesList);
        abilities.addAll(abilitiesList);
    }

    public Monster enterRoom() {
        currentFight = monsterStack.poll();
        Statemachine.next();
        return currentFight;
    }

    public Monster runaPhysicalAttack(PhysicalAbility attack) {
        int newHealth = currentFight.getHealthPoints();
        switch (attack.getType()) {
            case OFFENSIVE -> {
                currentFight = new Monster(currentFight, newHealth - attack.calculate(runa.rollDice(1)));
            }
        }
        return currentFight;
    }
}
