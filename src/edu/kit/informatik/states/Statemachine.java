package edu.kit.informatik.states;

public class Statemachine {

    private static GameState currentState = GameState.SHUFFLE;;

    public static void next() {
        switch (currentState) {
            case HEALING: {
                currentState = GameState.SHUFFLE;
                break;
            }
            case RUNATURN: {
                currentState = GameState.MONSTERTURNONE;
                break;
            }
            case MONSTERTURNTWO: {
                currentState = GameState.RUNATURN;
                break;
            }
            case MONSTERTURNONE: {
                currentState = GameState.MONSTERTURNTWO;
                break;
            }
            case FIGHTWON: {
                currentState = GameState.HEALING;
                break;
            }
            case SHUFFLE: {
                currentState = GameState.RUNATURN;
                break;
            }
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
