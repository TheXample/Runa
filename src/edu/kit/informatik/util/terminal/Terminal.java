package edu.kit.informatik.util.terminal;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.magical.defensive.Reflect;
import edu.kit.informatik.structure.card.characters.Character;
import edu.kit.informatik.structure.card.characters.Monster;
import edu.kit.informatik.structure.card.characters.Runa;
import edu.kit.informatik.util.parser.EndGameException;
import edu.kit.informatik.util.parser.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Terminal.
 *
 * @author Hannes Schniz
 * @version 0.1
 */
public final class Terminal {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final String ANSI_RESET = "\u001B[0m";

    private static final String ANSI_GREEN = "\u001B[32m";

    private static final String ANSI_RED = "\u001B[31m";

    private static final String ANSI_YELLOW = "\u001B[33m";

    private static final String ANSI_PURPLE = "\u001B[35m";

    private static final String ANSI_CYAN = "\u001B[36m";

    private static final String ANSI_BLACK = "\u001B[30m";

    private Terminal() {

    }

    /**
     * Select target int.
     *
     * @param name        the name
     * @param max         the max
     * @param hasToSelect the has to select
     * @return the int
     * @throws EndGameException the end game exception
     */
    public static int selectTarget(String name, int max, boolean hasToSelect) throws EndGameException {
        printSelect(name, max); //prints the select message
        int parsed;
        try {
            parsed = Parser.getSelected(READER.readLine(), max); //parses the input
        } catch (IOException ex) {
            return selectTarget("number", max, hasToSelect);
        }
        if (parsed != -ONE) { //if the input was correct returns the input -1
            return parsed - ONE;
        }
        else if (!hasToSelect) { //if the input wasn't correct but the player doesn't need to select returns -1
            return -1;
        }
        return selectTarget(name, max, hasToSelect); //recurrs to make sure the player makes an input
    }

    /**
     * Select multi target list.
     *
     * @param max    the max
     * @param amount the amount
     * @param exact  the exact
     * @param name   the name
     * @return the list
     * @throws EndGameException the end game exception
     */
    public static List<Integer> selectMultiTarget(int max, int amount, boolean exact, String name)
            throws EndGameException {
        System.out.println("Enter " + name + " [1--" + max + "] separated by comma:");
        List<Integer> parsed = new ArrayList<>();
        try {
            parsed = Parser.parseMulti(READER.readLine(), max); //parses the input
        } catch (IOException ex) {
            selectMultiTarget(max, amount, exact, name);
        }
        if (parsed != null && parsed.size() == amount) { //if the player made a correct input returns the parsed input
            return parsed;
        }
        else if (parsed != null && parsed.size() < amount && !exact) { //if the input doesn't need to be exact returns
            return parsed;
        }
        else {
            return selectMultiTarget(max, amount, exact, name); //recurrs to make sure that the player makes an input
        }
    }

    /**
     * Print hello.
     */
    public static void printHello() {
        System.out.println("Welcome to Runa's Strive");
        System.out.println("Select Runa's character class");
        System.out.println(ANSI_BLACK + "1) Warrior");
        System.out.println(ANSI_PURPLE + "2) Mage");
        System.out.println(ANSI_CYAN + "3) Paladin" + ANSI_RESET);
    }

    /**
     * Print ability string.
     *
     * @param input       the input
     * @param description if the description should be displayed
     * @return the string
     */
    public static String printAbility(Ability input, boolean description) {
        if (description) {
            return  ANSI_CYAN + input.getName() + "(" + input.getAbilityLevel() + ")" + ANSI_RESET + ": " + input.getDescription();
        }
        return  ANSI_CYAN + input.getName() + "(" + input.getAbilityLevel() + ")" + ANSI_RESET;
    }

    /**
     * Print line.
     */
    public static void printLine() {
        for (int i = 0; i < 120; i++) {
            System.out.print('-');
        }
        System.out.print("\n");
    }

    /**
     * Print runa string.
     *
     * @param character the character
     * @param shortOut  the short out
     * @return the string
     */
    public static String printCharacter(Character character, boolean shortOut) {
        String color = ANSI_GREEN;
        if (character.getHealthPoints() <= character.getMaxHealth() / 2) {
            color = ANSI_YELLOW;
        }
        if (character.getHealthPoints() <= character.getMaxHealth() / 4) {
            color = ANSI_RED;
        }
        if (shortOut) {

            return character.getName() + " (" + color + character.getHealthPoints() + "/"
                    + character.getMaxHealth() + ANSI_RESET  + ")";
        }
        return (character.getName() + " (" + color + character.getHealthPoints() + "/" + character.getMaxHealth()
                + " HP"  + ANSI_RESET + ", " + ANSI_PURPLE + character.getFocusPoints() + "/"
                + character.getDice().getType().getValue() + " FP" + ANSI_RESET + ")");
    }

    /**
     * Print stage.
     *
     * @param currentRoom  the current room
     * @param currentFloor the current floor
     */
    public static void printStage(int currentRoom, int currentFloor) {
        System.out.println("Runa enters Stage " + currentRoom + " of Level " + currentFloor);
    }

    /**
     * Print monster.
     *
     * @param monster the monster
     */
    public static void printMonster(Monster monster) {
        System.out.println(printCharacter(monster, false) + ": attempts "
                + printAbility(monster.getNextMove(), true));
    }

    /**
     * Print level.
     *
     * @param runa        the runa
     * @param monsterList the monster list
     */
    public static void printLevel(Runa runa, List<Monster> monsterList) {
        printLine();
        System.out.println(printCharacter(runa, false));
        System.out.println("vs.");
        for (Monster monster: monsterList) {
            printMonster(monster);
        }
        printLine();
    }

    /**
     * Print use.
     *
     * @param user    the user
     * @param ability the ability
     */
    public static void printUse(Character user, Ability ability) {
        System.out.println(user.getName() + " uses " + printAbility(ability, false));
    }

    /**
     * Print focus.
     *
     * @param name        the name
     * @param focusChange the focus change
     */
    public static void printFocus(String name, int focusChange) {
        if (focusChange > ZERO) {
            System.out.println(name + " gains " + focusChange + " focus");
        }
    }

    private static void printSelect(String type, int max) {
        System.out.println("Enter " + type + " [1--" + max + "]:");
    }

    /**
     * Print damage.
     *
     * @param target  the target
     * @param damage  the damage
     * @param ability the ability
     */
    public static void printDamage(Character target, int damage, Ability ability) {
        if (damage == ZERO) {
            return;
        }
        if (ability.getType().equals(AbilityType.OFFENSIVE) || ability.getClass().equals(Reflect.class)) {
            System.out.println(ANSI_RED + target.getName() + " takes " + damage + " "
                    + ability.getUsageType().getValue() + ". damage" + ANSI_RESET);

        }
    }

    /**
     * Print death.
     *
     * @param input the input
     */
    public static void printDeath(Character input) {
        if (input != null) {
            System.out.println(ANSI_RED + input.getName() + " dies" + ANSI_RESET);
        }
    }

    /**
     * Print targets.
     *
     * @param monsterList the monster list
     */
    public static void printTargets(List<Monster> monsterList) {
        for (int i = 0; i < monsterList.size(); i++) {
            System.out.println((i + ONE) + ") " + monsterList.get(i).getName());
        }
    }

    /**
     * Print abilities.
     *
     * @param input the input
     */
    public static void printAbilities(Runa input) {
        List<Ability> runasAbilities = input.getAbilities();
        for (int i = 0; i < runasAbilities.size(); i++) {
            System.out.println((i + ONE) + ") " + printAbility(runasAbilities.get(i), true));
        }
    }

    /**
     * Print.
     *
     * @param toPrint the to print
     */
    public static void print(String toPrint) {
        if (toPrint == null || toPrint.equals("")) {
            return;
        }
        System.out.println(toPrint);
    }

}
