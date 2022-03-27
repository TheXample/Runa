package edu.kit.informatik.util.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Parser.
 *
 * @author Hanne
 * @version 0.1
 */
public final class Parser {

    private static final String REGEXSEED = "(([2][0,1][0-4][0-7][0-4][0-8][0-3][0-6][0-4][0-7])|"
            + "(([1][0-9])|([1-9]))([0-9]{0,8}))";

    private static final int ISSEED = 2147483647;

    private Parser() {

    }

    /**
     * Gets selected.
     *
     * @param input the input
     * @param max   the max
     * @return the selected
     * @throws EndGameException the io exception
     */
    public static int getSelected(String input, int max) throws EndGameException {
        if (input.matches(REGEXSEED)) { //makes sure that the input is an Integer value
            if (Integer.parseInt(input) > max) { //if the input is bigger than the max returns -1
                return -1;
            }
            return Integer.parseInt(input);
        }
        checkQuit(input); //checks if the input is quit and throws the EndGameException
        return -1;
    }

    /**
     * Parse multi list.
     *
     * @param input the input
     * @param max   the max
     * @return the list
     * @throws EndGameException the io exception
     */
    public static List<Integer> parseMulti(String input, int max) throws EndGameException {
        if (input.matches("([1-9][0-9]*[,])*[1-9][0-9]*")) {
            List<Integer> reti = new ArrayList<>(); //the return list
            String[] split = input.split("[,]");
            for (int i = 0; i < split.length; i++) {
                if (!split[i].matches(REGEXSEED)) { //if any input doesnt match the Integer value returns null
                    return null;
                }
                reti.add(Integer.parseInt(split[i]) - 1); //adds the parsed input to the return list
                if (max == ISSEED) { //if the max is the seed adds one to the value again
                    reti.set(i, Integer.parseInt(split[i]));
                }

            }
            return reti;
        }
        checkQuit(input); //checks if the input was quit
        return null;
    }

    private static void checkQuit(String input) throws EndGameException {
        if (input.equals("quit")) {
            throw new EndGameException();
        }
    }
}
