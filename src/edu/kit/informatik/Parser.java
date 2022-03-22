package edu.kit.informatik;

import edu.kit.informatik.characters.RunaType;

public class Parser {

    private static final String REGEXSEED = "(([1,2][0,1][0-4][0-7][0-4][0-8][0-3][0-6][0-4][0-7])|([1-9][0-9]{0,8}))" +
            "[,](([1,2][0,1][0-4][0-7][0-4][0-8][0-3][0-6][0-4][0-7])|([1-9][0-9]{0,8}))";

    public static RunaType getRunaClass(String input){
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
        return null;
    }

    public static int[] getSeeds(String input) {
        if (input.matches(REGEXSEED)) {
            String[] split = input.split("[,]");
            int[] seeds = new int[2];
            for (int i = 0; i < split.length; i++) {
                seeds[i] = Integer.parseInt(split[i]);
            }
            return seeds;
        }
        return null;
    }
}
