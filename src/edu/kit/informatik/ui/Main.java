package edu.kit.informatik.ui;

import edu.kit.informatik.Parser;
import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicAbility;
import edu.kit.informatik.abilities.PhysicalAbility;
import edu.kit.informatik.characters.Character;
import edu.kit.informatik.characters.Monster;
import edu.kit.informatik.characters.Runa;
import edu.kit.informatik.states.GameState;
import edu.kit.informatik.states.RunasAdventure;
import edu.kit.informatik.states.Statemachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The type Main.
 * @author Hanne
 * @version 0.1
 */
public class Main {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private static RunasAdventure game;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.printHello();
        try {
            main.init();
            while (!Statemachine.getCurrentState().equals(GameState.LOST)) {
                switch (game.getState()) {
                    case SHUFFLE: {
                        main.shuffle();
                        main.printStage(1, game.getCurrentFloor());
                        game.enterRoom();
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
                        System.out.println("WOOOOHOOOOO");
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        } catch (IOException ignored) {

        }

    }

    private void runaAttack() throws IOException {
        System.out.println("Select card to play");
        System.out.println(getRunasAbilities());
        int pos = selectTarget(game.getRuna().getAbilities().size());
        Ability use = game.getRuna().getAbilities().get(pos);
        int target = 0;
        if (game.getCurrentFight().size() > 1 && use.getType().equals(AbilityType.OFFENSIVE)) {
            System.out.println("Select Runa’s target.");
            getTargets();
            target = selectTarget(game.getCurrentFight().size());
        }
        printUse(game.getRuna(), use);
        int dice = enterDice();
        switch (use.getUsageType()) {
            case PHYSICAL: {
                Monster current = game.getCurrentFight().get(target);
                int dmg = game.usePhysicalAbility(game.getRuna(), game.getCurrentFight().get(target),
                        (PhysicalAbility) use, dice);
                printDamage(current, dmg, use);
                break;
            }
            case MAGIC: {
                Monster current = game.getCurrentFight().get(target);
                int dmg = game.useMagicalAbility(game.getRuna(), game.getCurrentFight().get(target),
                        (MagicAbility) use);
                printDamage(current, dmg, use);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void printUse(Character user, Ability ability) {
        System.out.println( user.getName() + " uses " + printAbility(ability));
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
                    int dmg = game.useMagicalAbility(monster, game.getRuna(), (MagicAbility) monster.getNextMove());
                    printDamage(game.getRuna(), dmg, monster.getNextMove());
                    break;
                }
                default: {
                    break;
                }
            }
            monster.rmTop();
        }
    }

    private void printDamage(Character target, int damage, Ability ability) {
        if (ability.getType().equals(AbilityType.OFFENSIVE)) {
            System.out.println(target.getName() + " takes " + damage + ability.getUsageType().getValue() + ". damage");
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

    private String getRunasAbilities() {
        List<Ability> runasAbilities = game.getRuna().getAbilities();
        StringBuilder newBuilder = new StringBuilder();
        for (int i = 0; i < runasAbilities.size(); i++) {
            String toAppend = (i + 1) + ") " + printAbility(runasAbilities.get(i));
            newBuilder.append(toAppend);
        }
        return newBuilder.toString();
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
        System.out.println("Enter number [1--3]:");
        String line = READER.readLine();
        if (Parser.getRunaClass(line) != null) {
            game = new RunasAdventure(Parser.getRunaClass(line));
        }
    }

    private void shuffle() throws IOException {
        System.out.println("To shuffle ability cards and monsters, enter two seeds");
        System.out.println("Enter seeds [1--2147483647] separated by comma:");
        String line = READER.readLine();
        if (Parser.getSeeds(line) != null) {
            game.shuffleCards(Parser.getSeeds(line)[1], Parser.getSeeds(line)[0]);
        }
    }

    private void printLine() {
        System.out.println("----------------------------------------");
    }

    private void printRuna(Runa runa) {
        System.out.println("Runa (" + runa.getHealthPoints() + "/50 HP, " + runa.getFocusPoints() + "/6 FP)");
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
        printRuna(game.getRuna());
        System.out.println("vs.");
        for (Monster monster: game.getCurrentFight()) {
            printMonster(monster);
        }
        printLine();
    }
}
