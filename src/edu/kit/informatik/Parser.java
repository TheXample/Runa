package edu.kit.informatik;

import edu.kit.informatik.characters.RunaType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Parser.
 *
 * @author Hanne
 * @version 0.1
 */
public class Parser {

    private static final String REGEXSEED = "(([2][0,1][0-4][0-7][0-4][0-8][0-3][0-6][0-4][0-7])|"
            + "(([1][0-9])|([1-9]))([0-9]{0,8}))";

    /**
     * Gets runa class.
     *
     * @param input the input
     * @return the runa class
     * @throws IOException the io exception
     */
    public static RunaType getRunaClass(String input) throws IOException {
        if (input.matches("[1-3]")) {
            if (input.equals("1")) {
                return RunaType.WARRIOR;
            }
            if (input.equals("2")) {
                return RunaType.MAGE;
            }
            if (input.equals("3")) {
                return RunaType.PALADIN;
            }
        }
        checkQuit(input);
        return null;
    }

    /**
     * Get seeds int [ ].
     *
     * @param input the input
     * @return the int [ ]
     * @throws IOException the io exception
     */
    public static int[] getSeeds(String input) throws IOException {
        if (input.matches(REGEXSEED + "[,]" + REGEXSEED)) {
            String[] split = input.split("[,]");
            int[] seeds = new int[2];
            for (int i = 0; i < split.length; i++) {
                seeds[i] = Integer.parseInt(split[i]);
            }
            return seeds;
        }
        checkQuit(input);
        return null;
    }

    /**
     * Gets selected.
     *
     * @param input the input
     * @param max   the max
     * @return the selected
     * @throws IOException the io exception
     */
    public static int getSelected(String input, int max) throws IOException {
        if (input.matches("[1-9][0-9]*")) {
            if (Integer.parseInt(input) > max) {
                return -1;
            }
            return Integer.parseInt(input);
        }
        checkQuit(input);
        return -1;
    }

    private static void checkQuit(String input) throws IOException {
        if (input.equals("quit")) {
            throw new IOException();
        }
    }

    /**
     * Parse multi list.
     *
     * @param input the input
     * @param max   the max
     * @return the list
     * @throws IOException the io exception
     */
    public static List<Integer> parseMulti(String input, int max) throws IOException {
        if (input.equals("")) {
            return List.of(-1);
        }
        if (input.matches("([1-9][0-9]*[,])*[1-9][0-9]*")) {
            List<Integer> reti = new ArrayList<>();
            String[] split = input.split("[,]");
            for (int i = 0; i < split.length; i++) {
                reti.add(Integer.parseInt(split[i]) - 1);
                if (reti.get(i) > max) {
                    return null;
                }
            }
            return reti;
        }
        checkQuit(input);
        return null;
    }
}
