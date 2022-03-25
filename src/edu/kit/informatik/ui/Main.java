package edu.kit.informatik.ui;

import edu.kit.informatik.parser.Parser;
import edu.kit.informatik.structure.abilities.*;
import edu.kit.informatik.structure.abilities.magical.defensive.Reflect;
import edu.kit.informatik.structure.characters.Character;
import edu.kit.informatik.structure.characters.Monster;
import edu.kit.informatik.structure.characters.Runa;
import edu.kit.informatik.structure.characters.RunaType;
import edu.kit.informatik.structure.states.GameState;
import edu.kit.informatik.structure.RunasAdventure;
import edu.kit.informatik.structure.states.Statemachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Main.
 *
 * @author Hanne
 * @version 0.1
 */
public class Main {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private static RunasAdventure game;

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final int TWO = 2;

    private static final int TEN = 10;

    private static final int FOUR = 4;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IllegalArgumentException the illegal argument exception
     */
    public static void main(String[] args) throws IllegalArgumentException {
        if (args.length != ZERO) {
            throw new IllegalArgumentException("args have to be empty");
        }
        Main main = new Main();
        main.printHello();
        try {
            main.init();
            while (!Statemachine.getCurrentState().equals(GameState.LOST)
                    && !Statemachine.getCurrentState().equals(GameState.WIN)) {
                switch (game.getState()) {
                    case SHUFFLE: {
                        main.shuffle();
                        game.enterRoom();
                        main.printStage(ONE, game.getCurrentFloor());
                        break;
                    }
                    case RUNATURN: {
                        main.printLevel();
                        main.runaAttack();
                        break;
                    }
                    case MONSTERTURNONE: {
                        main.monsterAttack();
                        break;
                    }
                    case FIGHTWON: {
                        main.reward();
                        break;
                    }
                    case HEALING: {
                        main.heal();
                        main.printStage(game.getCurrentRoom(), game.getCurrentFloor());
                        break;
                    }
                    case RUNABOSSFIGHT: {
                        main.printLevel();
                        main.runaAttack();
                        break;
                    }
                    case MONSTERBOSSFIGHT: {
                        main.monsterAttack();
                        break;
                    }
                    case BOSSWIN: {
                        main.printUpgrade();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        } catch (IOException ignored) {

        }
        if (Statemachine.getCurrentState().equals(GameState.LOST)) {
            System.out.println("Runa dies");
        }
        if (Statemachine.getCurrentState().equals(GameState.WIN)) {
            System.out.println("Runa wins");
        }
    }

    private void printFocus() {
        int focusChange = game.checkChangeFocus();
        if (focusChange > ZERO) {
            System.out.println("Runa gains " + focusChange + " focus");
        }
    }

    private void printUpgrade() {
        game.fightReward(ZERO, null);
        if (!Statemachine.getCurrentState().equals(GameState.WIN)) {
            for (Ability newAb: game.getRuna().getAbilities()) {
                for (Ability classAb: game.getRuna().getClassAbilities(game.getCurrentFloor())) {
                    if (newAb.equalsAbility(classAb)) {
                        System.out.println("Runa gets " + printAbility(newAb));
                    }
                }
            }
        }
    }

    private void heal() throws IOException {
        double damage = Runa.getMaxhealth() - game.getRuna().getHealthPoints();
        int amount = (int) Math.ceil(damage / TEN);
        if (amount > ZERO) {
            System.out.println(printRuna(game.getRuna(), false) + " can discard ability cards for healing (or none)");
            getRunasAbilities();
            List<Integer> selected = selectMultiTarget(
                    game.getRuna().getAbilities().size(), amount, false, "numbers");
            List<Ability> found = new ArrayList<>();
            for (int i = ONE; i <= game.getRuna().getAbilities().size(); i++) {
                if (selected.contains(i)) {
                    found.add(game.getRuna().getAbilities().get(i));
                }
            }
            game.heal(found);
            if (damage < found.size() * TEN) {
                System.out.println("Runa gains " + (int) damage + " health");
            }
            else {
                System.out.println("Runa gains " + found.size() * 10 + " health");
            }
        }
        game.enterRoom();
    }

    private void reward() throws IOException {
        System.out.println("Choose Runa's reward");
        System.out.println("1) new ability cards");
        System.out.println("2) next player dice");
        int selected = selectTarget(TWO) + ONE;
        if (selected == 1) {
            List<Ability> drawnCards = new ArrayList<>();
            if (game.getCurrentRoom() == ONE) {
                for (int i = 0; i < TWO; i++) {
                    drawnCards.add(game.getTopAbility());
                }
            }
            else if (game.getCurrentRoom() > 1) {
                for (int i = 0; i < FOUR; i++) {
                    drawnCards.add(game.getTopAbility());
                }
            }
            List<Ability> chosen = selectReward(drawnCards);
            game.fightReward(selected, chosen);
            for (Ability rewardPrint: chosen) {
                System.out.println("Runa get " + printAbility(rewardPrint));
            }
        }
        else if (selected == TWO) {
            game.fightReward(selected, null);
            System.out.println("Runa upgrades her die to a d" + game.getRuna().getDice().getValue());
        }
    }

    private List<Ability> selectReward(List<Ability> rewards) throws IOException {
        List<Ability> selected = new ArrayList<>();
        System.out.println("Pick " + rewards.size() / 2 + " card(s) as loot");
        for (int i = 0; i < rewards.size(); i++) {
            System.out.println((i + 1) + ") " + printAbility(rewards.get(i)));
        }
        List<Integer> picked = new ArrayList<>();
        if (rewards.size() / 2 > 1) {
            picked = selectMultiTarget(rewards.size(), rewards.size() / 2, true, "numbers");
        }
        else {
            picked.add(selectTarget(rewards.size()));
        }
        for (Integer pick: picked) {
            selected.add(rewards.get(pick));
        }
        return selected;
    }

    private List<Integer> selectMultiTarget(int max, int amount, boolean exact, String name) throws IOException {
        System.out.println("Enter " + name + " [1--" + max + "] separated by comma:");
        List<Integer> parsed = Parser.parseMulti(READER.readLine(), max);
        if (parsed != null && parsed.size() == amount) {
            return parsed;
        }
        else if (parsed != null && parsed.size() < amount && !exact) {
            return parsed;
        }
        else {
            return selectMultiTarget(max, amount, exact, name);
        }
    }

    private void runaAttack() throws IOException {
        System.out.println("Select card to play");
        getRunasAbilities();
        int pos = selectTarget(game.getRuna().getAbilities().size());
        Ability use = game.getRuna().getAbilities().get(pos);
        int target = 0;
        if (game.getCurrentFight().size() > 1 && use.getType().equals(AbilityType.OFFENSIVE)) {
            System.out.println("Select Runa’s target.");
            getTargets();
            target = selectTarget(game.getCurrentFight().size());
        }
        printUse(game.getRuna(), use);
        switch (use.getUsageType()) {
            case PHYSICAL: {
                int dice = 0;
                if (use.getType().equals(AbilityType.OFFENSIVE)) {
                    dice = enterDice();
                }
                Monster current = game.getCurrentFight().get(target);
                int dmg = game.usePhysicalAbility(game.getRuna(), game.getCurrentFight().get(target),
                        (PhysicalAbility) use, dice);
                if (use.getType().equals(AbilityType.OFFENSIVE)) {
                    printDamage(current, dmg, use);
                }
                break;
            }
            case MAGIC: {
                Monster current = game.getCurrentFight().get(target);
                List<Integer> dmg = game.useMagicalAbility(game.getRuna(), game.getCurrentFight().get(target),
                        (MagicAbility) use);
                printFocus();
                if (use.getType().equals(AbilityType.OFFENSIVE)) {
                    printDamage(current, dmg.get(0), use);
                }
                break;
            }
            default: {
                break;
            }
        }
        printDeath(game.checkDead());
    }

    private void printDeath(Character input) {
        if (input != null) {
            System.out.println(input.getName() + " dies");
        }
    }

    private void printUse(Character user, Ability ability) {
        System.out.println(user.getName() + " uses " + printAbility(ability));
    }

    private void monsterAttack() throws IOException {
        for (Monster monster: game.getCurrentFight()) {
            printUse(monster, monster.getNextMove());
            switch (monster.getNextMove().getUsageType()) {
                case PHYSICAL: {
                    int dmg = game.usePhysicalAbility(monster, game.getRuna(),
                            (PhysicalAbility) monster.getNextMove(), 0);
                    printDamage(game.getRuna(), dmg, monster.getNextMove());
                    break;
                }
                case MAGIC: {
                    List<Integer> dmg = game.useMagicalAbility(monster, game.getRuna(),
                            (MagicAbility) monster.getNextMove());
                    printDamage(game.getRuna(), dmg.get(0), monster.getNextMove());
                    if (dmg.size() > 1) {
                        printDamage(monster, dmg.get(1), new Reflect(1));
                    }
                    break;
                }
                default: {
                    break;
                }
            }
            monster.rmTop();
        }
        printDeath(game.checkDead());
    }

    private void printDamage(Character target, int damage, Ability ability) {
        if (damage == ZERO) {
            return;
        }
        if (ability.getType().equals(AbilityType.OFFENSIVE) || ability.getClass().equals(Reflect.class)) {
            System.out.println(target.getName() + " takes " + damage + " "
                    + ability.getUsageType().getValue() + ". damage");

        }
    }

    private int enterDice() throws IOException {
        printSelect("dice roll", game.getRuna().getDice().getValue());
        int parsed = Parser.getSelected(READER.readLine(), game.getRuna().getDice().getValue());
        if (parsed != -1) {
            return parsed;
        }
        enterDice();
        return -1;
    }

    private int selectTarget(int max) throws IOException {
        printSelect("number", max);
        int parsed = Parser.getSelected(READER.readLine(), max);
        if (parsed != -1) {
            return parsed - 1;
        }
        return selectTarget(max);
    }

    private void printSelect(String type, int max) {
        System.out.println("Enter " + type + " [1--" + max + "]:");
    }

    private void getTargets() {
        List<Monster> monsters = game.getCurrentFight();
        for (int i = 0; i < monsters.size(); i++) {
            System.out.println((i + 1) + ") " + monsters.get(i).getName());
        }
    }

    private void getRunasAbilities() {
        List<Ability> runasAbilities = game.getRuna().getAbilities();
        for (int i = 0; i < runasAbilities.size(); i++) {
            System.out.println((i + 1) + ") " + printAbility(runasAbilities.get(i)));

        }
    }

    private String printAbility(Ability input) {
        return  input.getName() + "(" + input.getAbilityLevel() + ")";
    }

    private void printHello() {
        System.out.println("Welcome to Runa's Strive");
        System.out.println("Select Runa's character class");
        System.out.println("1) Warrior");
        System.out.println("2) Mage");
        System.out.println("3) Paladin");
    }

    private void init() throws IOException {

        int choice = selectTarget(3);
        if (choice == 0) {
            game = new RunasAdventure(RunaType.WARRIOR);
            return;
        }
        if (choice == 1) {
            game = new RunasAdventure(RunaType.MAGE);
            return;
        }
        if (choice == 2) {
            game = new RunasAdventure(RunaType.PALADIN);
        }
    }

    private void shuffle() throws IOException {
        System.out.println("To shuffle ability cards and monsters, enter two seeds");
        List<Integer> selected = selectMultiTarget(2147483647, 2, true, "seeds");
        game.shuffleCards(selected.get(1), selected.get(0));
    }

    private void printLine() {
        System.out.println("----------------------------------------");
    }

    private String printRuna(Runa runa, boolean full) {
        if (full) {
            return ("Runa (" + runa.getHealthPoints() + "/50 HP, " + runa.getFocusPoints()
                    + "/" + runa.getDice().getValue() + " FP)");
        }
        return ("Runa (" + runa.getHealthPoints() + "/50 HP)");
    }

    private void printStage(int stage, int level) {
        System.out.println("Runa enters Stage " + stage + " of Level " + level);
    }

    private void printMonster(Monster monster) {
        System.out.println(monster.getName() + " (" + monster.getHealthPoints() + " HP, " + monster.getFocusPoints()
                + " FP): attempts " + printAbility(monster.getNextMove()) + " next");
    }

    private void printLevel() {
        printLine();
        System.out.println(printRuna(game.getRuna(), true));
        System.out.println("vs.");
        for (Monster monster: game.getCurrentFight()) {
            printMonster(monster);
        }
        printLine();
    }
}
