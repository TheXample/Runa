package edu.kit.informatik.ui;

import edu.kit.informatik.Parser;
import edu.kit.informatik.ParserException;
import edu.kit.informatik.characters.Monster;
import edu.kit.informatik.characters.Runa;
import edu.kit.informatik.states.RunasAdventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static RunasAdventure game;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        String line = "";
        main.printHello();
        if (!main.init() || !main.shuffle()) {
            return;
        }
        while (!line.equals("quit")) {
            main.printStage(1, game.getCurrentFloor());
            main.printLine();
            main.printLevel();
            line = reader.readLine();
        }

    }

    private void printHello() {
        System.out.println("""
                Welcome to Runa's Strive\s
                Select Runa's character class\s
                1) Warrior\s
                2) Mage\s
                3) Paladin\s""");
    }

    private boolean init() throws IOException {
        System.out.println("Enter number [1--3]:");
        String line = reader.readLine();
        if (Parser.getRunaClass(line) != null) {
            game = new RunasAdventure(Parser.getRunaClass(line));
            return true;
        }
        if (!line.equals("quit")) {
            init();
        }
        return false;
    }

    private boolean shuffle() throws IOException {
        System.out.println("To shuffle ability cards and monsters, enter two seeds \nEnter seeds [1--2147483647] separated by comma:");
        String line = reader.readLine();
        if (Parser.getSeeds(line) != null) {
            game.shuffleCards(Parser.getSeeds(line)[1], Parser.getSeeds(line)[0]);
            return true;
        }
        if (!line.equals("quit")) {
            shuffle();
        }
        return false;
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
                + " FP): attempts " + monster.getNextMove().getName() + "(" + monster.getNextMove().getAbilityLevel() + ") next");
    }

    private void printLevel() {
        game.enterRoom();
        printRuna(game.getRuna());
        System.out.println("vs.");
        printMonster(game.getCurrentFight());
        printMonster(game.getTop());
    }
}
