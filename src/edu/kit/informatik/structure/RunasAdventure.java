package edu.kit.informatik.structure;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.MagicType;
import edu.kit.informatik.structure.card.abilities.PhysicalAbility;
import edu.kit.informatik.structure.card.abilities.MagicAbility;
import edu.kit.informatik.structure.card.abilities.magical.Focus;
import edu.kit.informatik.structure.card.abilities.magical.defensive.Reflect;
import edu.kit.informatik.structure.card.characters.Character;
import edu.kit.informatik.structure.card.characters.Monster;
import edu.kit.informatik.structure.card.characters.Runa;
import edu.kit.informatik.structure.card.characters.RunaType;
import edu.kit.informatik.structure.card.characters.monsters.one.SpiderKing;
import edu.kit.informatik.structure.card.characters.monsters.two.MegaSaurus;
import edu.kit.informatik.util.states.GameState;
import edu.kit.informatik.util.generator.ListGenerator;
import edu.kit.informatik.util.states.Statemachine;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The type Runas adventure.
 *
 * @author Hannes
 * @version 0.1
 */
public class RunasAdventure {

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final int TWO = 2;

    private static final int THREE = 3;

    private static final int FOUR = 4;

    private static final int TEN = 10;

    private static final int FLOORS = 2;

    private final Runa runa;

    private Queue<Monster> monsterStack;

    private Queue<Ability> abilities;

    private int currentFloor;

    private int currentRoom;

    private List<Monster> currentFight;

    private final int monsterSeed;

    /**
     * Instantiates a new Runas adventure.
     *
     * @param runaClass the runa class
     */
    public RunasAdventure(RunaType runaClass, List<Integer> seeds) {
        currentFloor = ONE;
        currentRoom = ZERO;
        this.monsterSeed = seeds.get(ONE);
        runa = new Runa(runaClass, seeds.get(ZERO));
        currentFight = new ArrayList<>();
        this.monsterStack = new LinkedList<>();
        this.abilities = new LinkedList<>();
        Statemachine.next();
    }

    //-----------------------------------------init functions-----------------------------------------------------------

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
        List<Monster> monsterList = ListGenerator.generateFloor(currentFloor, this.monsterSeed); //generates the current monster list
        Collections.shuffle(monsterList, new Random(seed)); //shuffles the current monster list
        monsterStack = new LinkedList<>(monsterList); //inits the monsterStack with the shuffled cards
    }

    private void initAbilities(long seed) {
        List<Ability> abilitiesList = ListGenerator.generateAbilities(currentFloor); //generates abilities
        for (Ability ability: new ArrayList<>(abilitiesList)) { //removes the class abilities from the generated list
            for (Ability classAb: runa.getClassAbilities(currentFloor)) {
                if (classAb.equalsAbility(ability)) {
                    abilitiesList.remove(ability);
                }
            }
        }
        Collections.shuffle(abilitiesList, new Random(seed)); //shuffles the ability list
        abilities = new LinkedList<>(abilitiesList); //inits the abilities stack with the shuffled list
    }

    /**
     * Enter room.
     */
    public void enterRoom() {
        if (currentRoom == THREE) { //if current room is 3 adds the Boss to the current fight
            currentFight = new ArrayList<>();
            if (currentFloor == ONE) {
                currentFight.add(new SpiderKing(monsterSeed));
            }
            if (currentFloor == TWO) {
                currentFight.add(new MegaSaurus(monsterSeed));
            }
            Statemachine.bossFight(); //sets the state to bossfight
            currentRoom++;
            return;
        }
        if (currentRoom == ZERO) {
            currentRoom++;
        }
        else if (currentRoom < THREE) { //if the current room is smaller than 3 it adds a monster to the current fight
            currentRoom++;
            currentFight.add(monsterStack.poll());
        }
        else if (currentRoom == FOUR) { //if the current room is 4 sets the room to one again and increments the floor
            currentRoom = ONE;
            currentFloor++;
        }
        currentFight.add(monsterStack.poll()); //adds a monster to the current fight
        Statemachine.next();
    }

    //--------------------------------------------fight functions-------------------------------------------------------

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
        int damage = ZERO;
        boolean runasTurn = Statemachine.getCurrentState().equals(GameState.RUNATURN)
                || Statemachine.getCurrentState().equals(GameState.RUNABOSSFIGHT);
        switch (attack.getType()) { //switches between attack types (Offensive/Defensive)
            case OFFENSIVE: {
                if (runasTurn) { //if its runas turn sets the damage to the
                    damage = setPhysicalDamage(currentFight.get(getOpponent(target)), attack, dice); //monster
                    break;
                }
                else { //if its the monsters turn sets the damage to the monster
                    damage = setPhysicalDamage(runa, attack, dice);
                }
                attacker.setLastMove(null); //sets the last move to null to prevent errors
            }
            case DEFENSIVE: {
                attacker.setLastMove(attack); //sets the last move
                break;
            }
            default: {
                break;
            }
        }
        if (runasTurn) { //if its runas turn goes to the next state
            Statemachine.next();
        }
        checkFocus(target, attack); //checks if the attack breaks focus
        return damage;
    }

    private int setPhysicalDamage(Character target, PhysicalAbility attack, int dice) {
        int damage; //if the last move of the target is a physical defensive ability calculates with medigation
        if (target.getLastMove() != null && target.getLastMove().getType().equals(AbilityType.DEFENSIVE)
                && target.getLastMove().getUsageType().equals(AbilityType.PHYSICAL)) {
            damage = target.getLastMove().calculate(attack.calculate(dice)); //calculates the damage
            target.setHealthPoints(target.getHealthPoints() - damage); //sets the health points to the new HP
            target.setLastMove(null); //sets the last move of the target to null
        }
        else {
            damage = attack.calculate(dice); //calculates the damage without the defensive medigation
            target.setHealthPoints(target.getHealthPoints() - damage); //sets the health
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
            case OFFENSIVE -> {
                if (!canCast(attacker, attack)) { //if the attacker cant cast the spell breaks
                    attacker.setLastMove(null);
                    break;
                }
                if (Statemachine.getCurrentState().equals(GameState.RUNATURN)
                        || Statemachine.getCurrentState().equals(GameState.RUNABOSSFIGHT)) {
                    dmg = setMagicalDamage(runa, currentFight.get(getOpponent(target)), attack);
                } else {
                    dmg = setMagicalDamage(attacker, runa, attack);
                }
                attacker.setFocusPoints(attacker.getFocusPoints() - attack.getCost()); //reduces the focus points
                attacker.setLastMove(null); //sets the last move of the attacker to null
            }
            case DEFENSIVE, FOCUS -> attacker.setLastMove(attack);
        }
        if (Statemachine.getCurrentState().equals(GameState.RUNATURN)
                || Statemachine.getCurrentState().equals(GameState.RUNABOSSFIGHT)) {
            Statemachine.next();
        }
        if (dmg.size() < ONE) {
            dmg.add(ZERO);
        }
        return dmg;
    }

    private List<Integer> setMagicalDamage(Character attacker, Character defender, MagicAbility attack) {
        List<Integer> dmg = new ArrayList<>();
        if (defender.getLastMove() != null && defender.getLastMove().getType().equals(AbilityType.DEFENSIVE)
                && defender.getLastMove().getUsageType().equals(AbilityType.MAGIC)) { //if lastmove is def.
            if (defender.getLastMove().getName().equals("Reflect")) { //if lastmove is reflect
                int attackDmg = attack.calculate(attacker.getFocusPoints(), MagicType.NONE); //attack damage
                dmg.add(attackDmg - ((Reflect) defender.getLastMove()).calculate(
                        attackDmg, defender.getPrimaryType())); //calculates the damage to the target
                dmg.add(((Reflect) defender.getLastMove()).calculate(
                        attackDmg, defender.getPrimaryType())); //calculates the damage to the attacker
                attacker.setHealthPoints(attacker.getHealthPoints() - dmg.get(dmg.size() - ONE));
            }
            else { //calculates the damage normal as damage with a defensive ability
                dmg.add(defender.getLastMove().calculate(
                        attack.calculate(attacker.getFocusPoints(), defender.getPrimaryType())));
            }
            defender.setHealthPoints(defender.getHealthPoints() - dmg.get(ZERO)); //sets health of the target
            defender.setLastMove(null); //sets the last move of the target to null
        }
        else { //calculates the damage without medigation
            dmg.add(attack.calculate(attacker.getFocusPoints(),
                    defender.getPrimaryType()));
            defender.setHealthPoints(defender.getHealthPoints() - dmg.get(ZERO));
        }
        return dmg;
    }

    /**
     * Check change focus int.
     *
     * @param user the user
     * @return the int
     */
    public int checkChangeFocus(Character user) {
        boolean clear = false;
        int reti = ZERO;
        if (Statemachine.getCurrentState().equals(GameState.RUNATURN)
                || Statemachine.getCurrentState().equals(GameState.RUNABOSSFIGHT)) {
            for (Monster monster:currentFight) { //checks if a move is breaking focus and sets clear to false if it does
                if (monster.getLastMove() != null && monster.getLastMove().isBreaksFocus()
                        && runa.getLastMove() != null && runa.getLastMove().getType().equals(AbilityType.FOCUS)) {
                    clear = false;
                    break;
                }
                clear = true;
            }
            if (clear && runa.getLastMove() != null && runa.getLastMove().getType().equals(AbilityType.FOCUS)) {
                reti = ((Focus) runa.getLastMove()).calculate(user.getFocusPoints(), MagicType.NONE);
                runa.setFocusPoints(runa.getFocusPoints() + reti); //if none break focus sets runas FP
            }
        }
        if (Statemachine.getCurrentState().equals(GameState.MONSTERTURN)
                || Statemachine.getCurrentState().equals(GameState.MONSTERBOSSFIGHT)) {
            if ((runa.getLastMove() == null || !runa.getLastMove().isBreaksFocus()) && user.getLastMove() != null
                    && user.getLastMove().getType().equals(AbilityType.FOCUS)) {
                reti = ((Focus) user.getLastMove()).calculate(user.getFocusPoints(), MagicType.NONE);
                user.setFocusPoints(user.getFocusPoints() + reti);
            } //if runas last move was not breaking focus sets the focus of the user
        }
        return reti;
    }

    /**
     * Monster turn over.
     */
    public void monsterTurnOver() {
        if (Statemachine.getCurrentState().equals(GameState.MONSTERTURN)
             || Statemachine.getCurrentState().equals(GameState.MONSTERBOSSFIGHT)) {
            Statemachine.next();
        }
    }

    //-------------------------------------------after fight functions--------------------------------------------------

    /**
     * Check dead.
     *
     * @return the character
     */
    public Character checkDead() {
        Character died = null;
        for (Character chara: new ArrayList<Character>(currentFight)) {
            if (chara.isDead()) { //checks if any monster is dead, removes and returns that monster
                died = chara;
                currentFight.remove(chara);
            }
        }
        if (runa.isDead()) { //if runa is dead sets state to lost and returns runa
            Statemachine.lost();
            return runa;
        }
        if (currentFight.size() == ZERO) { //if no monsters are in the fight any more sets the state to fight won
            Statemachine.fightWon();
        }
        return died;
    }

    /**
     * Fight reward.
     *
     * @param choice      the choice
     * @param chosenCards the chosen cards
     */
    public void fightReward(int choice, List<Ability> chosenCards) {
        if (Statemachine.getCurrentState().equals(GameState.FIGHTWON)) {
            if (choice == ONE) { //if the reward was new abilities adds the abilities
                for (Ability newAbility: chosenCards) {
                    runa.addAbility(newAbility);
                }
            }
            if (choice == TWO) { //if the reward was upgraded dice upgrades runas dice
                runa.upgradeDice();
            }
        }
        if (Statemachine.getCurrentState().equals(GameState.BOSSWIN)) { //if it was the boss win
            if (currentFloor == FLOORS) { //if it was the second boss sets the state to win and returns
                Statemachine.win();
                return;
            }
            runa.upgradeAbilities(); //upgrades runas class abilities
            currentFloor++;
            currentRoom = ZERO;
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
        runa.setHealthPoints(runa.getHealthPoints() + TEN * discard.size());
    }

    //-----------------------------------------private helper functions-------------------------------------------------

    private void checkFocus(Character target, Ability attack) {
        if (attack.isBreaksFocus() && target.getLastMove() != null
                && target.getLastMove().getType().equals(AbilityType.FOCUS)) {
            target.setLastMove(null);
        }
    }

    private int getOpponent(Character target) {
        for (int i = 0; i < currentFight.size(); i++) {
            if (target.equalsCharacter(currentFight.get(i))) {
                return i;
            }
        }
        return -ONE;
    }

    private boolean canCast(Character caster, MagicAbility attack) {
        return caster.getFocusPoints() >= attack.getCost();
    }

    //----------------------------------------------getters-------------------------------------------------------------

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
    public static GameState getState() {
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
