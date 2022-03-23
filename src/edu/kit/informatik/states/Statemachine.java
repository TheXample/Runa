package edu.kit.informatik.states;

public class Statemachine {

    private static GameState currentState = GameState.SHUFFLE;;

    public static void next() {
        switch (currentState) {
            case HEALING -> currentState = GameState.SHUFFLE;
            case RUNATURN -> currentState = GameState.MONSTERTURN;
            case SHUFFLE, MONSTERTURN -> currentState = GameState.RUNATURN;
            case FIGHTWON -> currentState = GameState.HEALING;
        }
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
