package edu.kit.informatik.states;

/**
 * The type Statemachine.
 * @author Hanne
 * @version 0.1
 */
public class Statemachine {

    private static GameState currentState = GameState.SHUFFLE;

    /**
     * Next.
     */
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
            default: {
                break;
            }
        }
    }

    /**
     * Fight won.
     */
    public static void fightWon() {
        currentState = GameState.FIGHTWON;
    }

    /**
     * Gets current state.
     *
     * @return the current state
     */
    public static GameState getCurrentState() {
        return currentState;
    }

    /**
     * Reset.
     */
    public static void reset() {
        currentState = GameState.INIT;
    }

    /**
     * Sets state.
     *
     * @param newState the new state
     */
    public static void setState(GameState newState) {
        currentState = newState;
    }

    /**
     * Lost.
     */
    public static void lost() {
        currentState = GameState.LOST;
    }
}
