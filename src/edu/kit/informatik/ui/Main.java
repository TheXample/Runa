package edu.kit.informatik.ui;

import edu.kit.informatik.Parser;
import edu.kit.informatik.abilities.Ability;
import edu.kit.informatik.abilities.AbilityType;
import edu.kit.informatik.abilities.MagicAbility;
import edu.kit.informatik.abilities.PhysicalAbility;
import edu.kit.informatik.characters.Character;
import edu.kit.informatik.characters.Monster;
import edu.kit.informatik.characters.Runa;
import edu.kit.informatik.states.RunasAdventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static RunasAdventure game;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        String line = "";
        main.printHello();
        try {
            main.init();
            while (!line.equals("quit")) {
                switch (game.getState()) {
                    case SHUFFLE -> {
                        main.shuffle();
                        main.printStage(1, game.getCurrentFloor());
                        game.enterRoom();
                    }
                    case RUNATURN -> {
                        main.printLevel();
                        main.runaAttack();
                    }
                    case MONSTERTURNONE -> {
                        main.monsterAttack();
                    }
                    case FIGHTWON -> {
                        System.out.println("WOOOOHOOOOO");
                    }
                }
            }
        } catch (IOException ignored) {

        }

    }

    private void runaAttack() throws IOException {
        System.out.println("Select card to play \n" + getRunasAbilities());
        int pos = selectTarget(game.getRuna().getAbilities().size());
        Ability use = game.getRuna().getAbilities().get(pos);
        printUse(game.getRuna(), use);
        int target = 0;
        if (game.getCurrentFight().size() > 1 && use.getType().equals(AbilityType.OFFENSIVE)) {
            System.out.println("Select Runa’s target.\n" + getTargets());
            target = selectTarget(game.getCurrentFight().size());
        }
        int dice = enterDice();
        switch (use.getUsageType()) {
            case PHYSICAL -> {
                Monster current = game.getCurrentFight().get(target);
                int dmg = game.usePhysicalAbility(game.getRuna(), game.getCurrentFight().get(target),
                        (PhysicalAbility) use, dice);
                printDamage(current, dmg, use);
            }
            case MAGIC -> game.useMagicalAbility(game.getRuna(), game.getCurrentFight().get(target),
                    (MagicAbility) use);
        }
    }

    private void printUse(Character user, Ability ability) {
        System.out.println( user.getName() + " uses " + printAbility(ability));
    }

    private void monsterAttack() throws IOException {
        for (Monster monster: game.getCurrentFight()) {
            printUse(monster, monster.getNextMove());
            switch (monster.getNextMove().getUsageType()) {
                case PHYSICAL -> {
                    int dmg = game.usePhysicalAbility(monster, game.getRuna(), (PhysicalAbility) monster.getNextMove(), 0);
                    printDamage(game.getRuna(), dmg, monster.getNextMove());
                }
                case MAGIC -> {
                    int dmg = game.useMagicalAbility(monster, game.getRuna(), (MagicAbility) monster.getNextMove());
                    printDamage(game.getRuna(), dmg, monster.getNextMove());
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
        int parsed = Parser.getSelected(reader.readLine(), game.getRuna().getDice().getValue());
        if (parsed != -1) {
            return parsed;
        }
        enterDice();
        return -1;
    }

    private int selectTarget(int max) throws IOException {
        printSelect("number", max);
        int parsed = Parser.getSelected(reader.readLine(), max);
        if (parsed != -1) {
            return parsed - 1;
        }
        return selectTarget(max);
    }

    private void printSelect(String type, int max) {
        System.out.println("Enter " + type + " [1--" + max +"]:");
    }

    private String getTargets() {
        List<Monster> monsters = game.getCurrentFight();
        StringBuilder newBuilder = new StringBuilder();
        for (int i = 0; i < monsters.size(); i++) {
            String toAppend = (i + 1) + ") " + monsters.get(i).getName() + "\n";
            newBuilder.append(toAppend);
        }
        return newBuilder.toString();
    }

    private String getRunasAbilities() {
        List<Ability> runasAbilities = game.getRuna().getAbilities();
        StringBuilder newBuilder = new StringBuilder();
        for (int i = 0; i < runasAbilities.size(); i++) {
            String toAppend = (i + 1) + ") " + printAbility(runasAbilities.get(i)) + "\n";
            newBuilder.append(toAppend);
        }
        return newBuilder.toString();
    }

    private String printAbility(Ability input) {
        return  input.getName() + "(" + input.getAbilityLevel() + ")";
    }

    private void printHello() {
        System.out.println("""
                Welcome to Runa's Strive\s
                Select Runa's character class\s
                1) Warrior\s
                2) Mage\s
                3) Paladin\s""");
    }

    private void init() throws IOException {
        System.out.println("Enter number [1--3]:");
        String line = reader.readLine();
        if (Parser.getRunaClass(line) != null) {
            game = new RunasAdventure(Parser.getRunaClass(line));
            return;
        }
        if (!line.equals("quit")) {
            init();
        } else {
            throw new IOException();
        }
    }

    private void shuffle() throws IOException {
        System.out.println("To shuffle ability cards and monsters, enter two seeds \nEnter seeds [1--2147483647] separated by comma:");
        String line = reader.readLine();
        if (Parser.getSeeds(line) != null) {
            game.shuffleCards(Parser.getSeeds(line)[1], Parser.getSeeds(line)[0]);
            return;
        }
        if (!line.equals("quit")) {
            shuffle();
        } else {
            throw new IOException();
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
