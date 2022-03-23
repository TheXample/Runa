package edu.kit.informatik.states;

public class Statemachine {

    private static GameState currentState = GameState.SHUFFLE;;

    public static void next() {
        switch (currentState) {
            case HEALING -> currentState = GameState.SHUFFLE;
            case RUNATURN -> currentState = GameState.MONSTERTURNONE;
            case SHUFFLE, MONSTERTURNTWO -> currentState = GameState.RUNATURN;
            case MONSTERTURNONE -> currentState = GameState.MONSTERTURNTWO;
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

    public static void setState(GameState newState) {
        currentState = newState;
    }

    public static void lost() {
        currentState = GameState.LOST;
    }
}
