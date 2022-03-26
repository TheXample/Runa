package edu.kit.informatik.structure;

import edu.kit.informatik.structure.abilities.*;
import edu.kit.informatik.structure.abilities.magical.Focus;
import edu.kit.informatik.structure.abilities.magical.defensive.Reflect;
import edu.kit.informatik.structure.abilities.magical.offensive.Fire;
import edu.kit.informatik.structure.abilities.magical.offensive.Ice;
import edu.kit.informatik.structure.abilities.magical.offensive.Lightning;
import edu.kit.informatik.structure.abilities.magical.offensive.Water;
import edu.kit.informatik.structure.abilities.physical.defensive.Parry;
import edu.kit.informatik.structure.abilities.physical.offensive.Pierce;
import edu.kit.informatik.structure.abilities.physical.offensive.Slash;
import edu.kit.informatik.structure.abilities.physical.offensive.Swing;
import edu.kit.informatik.structure.abilities.physical.offensive.Thrust;
import edu.kit.informatik.structure.characters.Character;
import edu.kit.informatik.structure.characters.Monster;
import edu.kit.informatik.structure.characters.Runa;
import edu.kit.informatik.structure.characters.RunaType;
import edu.kit.informatik.structure.characters.monsters.one.*;
import edu.kit.informatik.structure.characters.monsters.two.*;
import edu.kit.informatik.structure.states.GameState;
import edu.kit.informatik.structure.states.Statemachine;

import java.util.*;

/**
 * The type Runas adventure.
 *
 * @author Hanne
 * @version 0.1
 */
public class RunasAdventure {

    private final Runa runa;

    private Queue<Monster> monsterStack;

    private Queue<Ability> abilities;

    private int currentFloor;

    private int currentRoom;

    private List<Monster> currentFight;

    /**
     * Instantiates a new Runas adventure.
     *
     * @param runaClass the runa class
     */
    public RunasAdventure(RunaType runaClass) {
        currentFloor = 1;
        currentRoom = 0;
        runa = new Runa(runaClass);
        currentFight = new ArrayList<>();
        this.monsterStack = new LinkedList<>();
        this.abilities = new LinkedList<>();
    }

    /**
     * Shuffle cards.
     *
     * @param seedMonster  the seed monster
     * @param seedAbilties the seed abilties
     */
    public void shuffleCards(long seedMonster, long seedAbilties) {
        initMonster(seedMonster);
        initAbilities(seedAbilties);
    }

    private void initMonster(long seed) {
        ArrayList<Monster> monsterList = new ArrayList<>();
        if (currentFloor == 1) {
            monsterList = new ArrayList<>(List.of(new Frog(), new Ghost(), new Gorgon(), new Skeleton(),
                    new Spider(), new Goblin(), new Rat(), new Mushroomlin()));
        }
        if (currentFloor == 2) {
            monsterList = new ArrayList<>(List.of(new Snake(), new DarkElf(), new ShadowBlade(), new Hornet(),
                    new Tarantula(), new Bear(), new Mushroomlon(), new WildBoar()));
        }
        Collections.shuffle(monsterList, new Random(seed));
        monsterStack = new LinkedList<>(monsterList);
    }

    private void initAbilities(long seed) {
        ArrayList<Ability> abilitiesList = new ArrayList<>(List.of(new Slash(currentFloor), new Swing(currentFloor),
                new Thrust(currentFloor), new Pierce(currentFloor), new Parry(currentFloor), new Focus(currentFloor),
                new Reflect(currentFloor), new Water(currentFloor), new Ice(currentFloor), new Fire(currentFloor),
                new Lightning(currentFloor)));

        for (Ability ability: new ArrayList<>(abilitiesList)) {
            for (Ability classAb: runa.getClassAbilities(currentFloor)) {
                if (classAb.equalsAbility(ability)) {
                    abilitiesList.remove(ability);
                }
            }
        }
        Collections.shuffle(abilitiesList, new Random(seed));
        abilities = new LinkedList<>(abilitiesList);
    }

    /**
     * Enter room.
     */
    public void enterRoom() {
        if (currentRoom == 3) {
            currentFight = new ArrayList<>();
            if (currentFloor == 1) {
                currentFight.add(new SpiderKing());
            }
            if (currentFloor == 2) {
                currentFight.add(new MegaSaurus());
            }
            Statemachine.bossFight();
            currentRoom++;
            return;
        }
        if (currentRoom == 0) {
            currentRoom++;
        }
        else if (currentRoom < 3) {
            currentRoom++;
            currentFight.add(monsterStack.poll());
        }
        else if (currentRoom == 4) {
            currentRoom = 1;
            currentFloor++;
        }
        currentFight.add(monsterStack.poll());
        Statemachine.next();
    }

    /**
     * Use physical ability int.
     *
     * @param attacker the attacker
     * @param target   the target
     * @param attack   the attack
     * @param dice     the dice
     * @return the int
     */
    public int usePhysicalAbility(Character attacker, Character target, PhysicalAbility attack, int dice) {
        int damage = 0;
        switch (attack.getType()) {
            case OFFENSIVE: {
                if (Statemachine.getCurrentState().equals(GameState.RUNATURN)
                        || Statemachine.getCurrentState().equals(GameState.RUNABOSSFIGHT)) {
                    damage = setPhysicalDamage(currentFight.get(getOpponent(target)), attack, dice);
                    getOpponent(target);
                    break;
                }
                else {
                    damage = setPhysicalDamage(runa, attack, dice);
                }
                attacker.setLastMove(null);
            }
            case DEFENSIVE: {
                attacker.setLastMove(attack);
                break;
            }
            default: {
                break;
            }
        }
        if (Statemachine.getCurrentState().equals(GameState.RUNATURN)
                || Statemachine.getCurrentState().equals(GameState.RUNABOSSFIGHT)) {
            Statemachine.next();
        }
        checkFocus(target, attack);
        return damage;
    }

    /**
     * Check change focus int.
     *
     * @param user the user
     * @return the int
     */
    public int checkChangeFocus(Character user) {
        boolean clear = false;
        int reti = 0;
        if (Statemachine.getCurrentState().equals(GameState.RUNATURN)
                || Statemachine.getCurrentState().equals(GameState.RUNABOSSFIGHT)) {
            for (Monster monster:currentFight) {
                if (monster.getLastMove() != null && monster.getLastMove().isBreaksFocus()
                        && runa.getLastMove() != null && runa.getLastMove().getType().equals(AbilityType.FOCUS)) {
                    clear = false;
                    break;
                }
                clear = true;
            }
            if (clear && runa.getLastMove() != null && runa.getLastMove().getType().equals(AbilityType.FOCUS)) {
                reti = ((Focus) runa.getLastMove()).calculate(user.getFocusPoints(), MagicType.NONE);
                runa.setFocusPoints(runa.getFocusPoints() + reti);
            }
        }
        if (Statemachine.getCurrentState().equals(GameState.MONSTERTURNONE)
                || Statemachine.getCurrentState().equals(GameState.MONSTERBOSSFIGHT)) {
            if ((runa.getLastMove() == null || !runa.getLastMove().isBreaksFocus()) && user.getLastMove() != null
                    && user.getLastMove().getType().equals(AbilityType.FOCUS)) {
                clear = true;
            }
            if (clear && user.getLastMove() != null && user.getLastMove().getType().equals(AbilityType.FOCUS)) {
                reti = ((Focus) user.getLastMove()).calculate(user.getFocusPoints(), MagicType.NONE);
                user.setFocusPoints(user.getFocusPoints() + reti);
            }
        }
        return reti;
    }

    private int getOpponent(Character target) {
        for (int i = 0; i < currentFight.size(); i++) {
            if (target.equalsCharacter(currentFight.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private int setPhysicalDamage(Character target, PhysicalAbility attack, int dice) {
        int damage = 0;
        if (target.getLastMove() != null && target.getLastMove().getType().equals(AbilityType.DEFENSIVE)
                && target.getLastMove().getUsageType().equals(AbilityType.PHYSICAL)) {
            damage = target.getLastMove().calculate(attack.calculate(dice));
            target.setHealthPoints(target.getHealthPoints() - damage);
            target.setLastMove(null);
        }
        else {
            damage = attack.calculate(dice);
            target.setHealthPoints(target.getHealthPoints() - damage);
        }
        return damage;
    }

    /**
     * Use magical ability int.
     *
     * @param attacker the attacker
     * @param target   the target
     * @param attack   the attack
     * @return the int
     */
    public List<Integer> useMagicalAbility(Character attacker, Character target, MagicAbility attack) {
        List<Integer> dmg = new ArrayList<>();
        switch (attack.getType()) {
            case OFFENSIVE: {
                if (!canCast(attacker, attack)) {
                    attacker.setLastMove(null);
                    break;
                }
                if (Statemachine.getCurrentState().equals(GameState.RUNATURN)
                        || Statemachine.getCurrentState().equals(GameState.RUNABOSSFIGHT)) {
                    Monster targetMonster = currentFight.get(getOpponent(target));
                    if (target.getLastMove() != null && target.getLastMove().getType().equals(AbilityType.DEFENSIVE)
                            && target.getLastMove().getUsageType().equals(AbilityType.MAGIC)) {
                        dmg.add(target.getLastMove().calculate(attack.calculate(runa.getFocusPoints(),
                                targetMonster.getPrimaryType())));
                        targetMonster.setHealthPoints(targetMonster.getHealthPoints() - dmg.get(0));
                    }
                    else {
                        dmg.add(attack.calculate(runa.getFocusPoints(), targetMonster.getPrimaryType()));
                        targetMonster.setHealthPoints(targetMonster.getHealthPoints() - dmg.get(0));
                    }
                    currentFight.set(getOpponent(target), targetMonster);
                    attacker.setLastMove(null);
                }
                else {
                    if (target.getLastMove() != null && target.getLastMove().getType().equals(AbilityType.DEFENSIVE)
                            && target.getLastMove().getUsageType().equals(AbilityType.MAGIC)) {
                        if (target.getLastMove().getName().equals("Reflect")) {
                            int attackDmg = attack.calculate(attacker.getFocusPoints(), MagicType.NONE);
                            dmg.add(attackDmg - ((Reflect) target.getLastMove()).calculate(attackDmg, MagicType.NONE));
                            dmg.add(((Reflect) target.getLastMove()).calculate(attackDmg, MagicType.NONE));
                            attacker.setHealthPoints(attacker.getHealthPoints() - dmg.get(dmg.size() - 1));
                        }
                        else {
                            dmg.add(target.getLastMove().calculate(attack.calculate(currentFight.get(
                                    getOpponent(attacker)).getFocusPoints(), MagicType.NONE)));
                        }
                        target.setHealthPoints(target.getHealthPoints() - dmg.get(0));
                        target.setLastMove(null);
                    }
                    else {
                        dmg.add(attack.calculate(currentFight.get(getOpponent(attacker)).getFocusPoints(),
                                MagicType.NONE));
                        runa.setHealthPoints(runa.getHealthPoints() - dmg.get(0));
                    }
                }
                attacker.setFocusPoints(attacker.getFocusPoints() - attack.getCost());
                checkFocus(target, attack);
                attacker.setLastMove(null);
                break;
            }
            case DEFENSIVE: {
                attacker.setLastMove(attack);
                break;
            }
            case FOCUS: {
                attacker.setLastMove(attack);
                break;
            }
            default: {
                break;
            }
        }
        if (Statemachine.getCurrentState().equals(GameState.RUNATURN)
                || Statemachine.getCurrentState().equals(GameState.RUNABOSSFIGHT)) {
            Statemachine.next();
        }
        if (dmg.size() < 1) {
            dmg.add(0);
        }
        return dmg;
    }

    /**
     * Monster turn over.
     */
    public void monsterTurnOver() {
        if (Statemachine.getCurrentState().equals(GameState.MONSTERTURNONE)
             || Statemachine.getCurrentState().equals(GameState.MONSTERBOSSFIGHT)) {
            Statemachine.next();
        }
    }

    /**
     * Check dead.
     *
     * @return the character
     */
    public Character checkDead() {
        Character died = null;
        for (Character chara: new ArrayList<Character>(currentFight)) {
            if (chara.isDead()) {
                died = chara;
                currentFight.remove(chara);
            }
        }
        currentFight.removeIf(Character::isDead);
        if (runa.isDead()) {
            Statemachine.lost();
            return runa;
        }
        if (currentFight.size() == 0) {
            Statemachine.fightWon();
        }
        return died;
    }

    private boolean canCast(Character caster, MagicAbility attack) {
        if (caster.getFocusPoints() >= attack.getCost()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Fight reward.
     *
     * @param choice      the choice
     * @param chosenCards the chosen cards
     */
    public void fightReward(int choice, List<Ability> chosenCards) {
        if (Statemachine.getCurrentState().equals(GameState.FIGHTWON)) {
            if (choice == 1) {
                for (Ability newAbility: chosenCards) {
                    runa.addAbility(newAbility);
                }
            }
            if (choice == 2) {
                runa.upgradeDice();
            }
        }
        if (Statemachine.getCurrentState().equals(GameState.BOSSWIN)) {
            runa.upgradeAbilities();
            if (currentFloor == 2) {
                Statemachine.win();
                return;
            }
            currentFloor = 2;
            currentRoom = 0;
        }
        Statemachine.next();
    }

    /**
     * Heal.
     *
     * @param discard the discard
     */
    public void heal(List<Ability> discard) {
        for (Ability toDiscard: discard) {
            runa.removeCard(toDiscard);
        }
        runa.setHealthPoints(runa.getHealthPoints() + 10 * discard.size());
    }


    private void checkFocus(Character target, Ability attack) {
        if (attack.isBreaksFocus() && target.getLastMove() != null
                && target.getLastMove().getType().equals(AbilityType.FOCUS)) {
            target.setLastMove(null);
        }
    }

    /**
     * Gets runa.
     *
     * @return the runa
     */
    public Runa getRuna() {
        return runa;
    }

    /**
     * Gets current floor.
     *
     * @return the current floor
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Gets current fight.
     *
     * @return the current fight
     */
    public List<Monster> getCurrentFight() {
        return currentFight;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public GameState getState() {
        return Statemachine.getCurrentState();
    }

    /**
     * Gets current room.
     *
     * @return the current room
     */
    public int getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Gets top ability.
     *
     * @return the top ability
     */
    public Ability getTopAbility() {
        return abilities.poll();
    }
}
