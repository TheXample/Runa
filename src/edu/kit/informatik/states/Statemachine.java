package edu.kit.informatik.states;

public class Statemachine {

    private static GameState currentState = GameState.INIT;;

    public static GameState next() {
        switch (currentState) {
            case INIT, HEALING -> currentState = GameState.SHUFFLE;
            case RUNATURN -> currentState = GameState.FOCUSPOINTSMONSTER;
            case FOCUSPOINTSMONSTER -> currentState = GameState.MONSTERTURN;
            case MONSTERTURN -> currentState = GameState.FOCUSPOINTSRUNA;
            case FOCUSPOINTSRUNA -> currentState = GameState.RUNATURN;
            case FIGHTWON -> currentState = GameState.HEALING;
        }
        return currentState;
    }
    public static void fightWon() {
        currentState = GameState.FIGHTWON;
    }

    public static GameState getCurrentState() {
        return currentState;
    }

    public static void reset() {
        currentState = GameState.INIT;
    }
}
