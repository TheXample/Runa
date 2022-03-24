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
                currentState = GameState.RUNATURN;
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
            case BOSSWIN: {
                currentState = GameState.SHUFFLE;
                break;
            }
            case RUNABOSSFIGHT: {
                currentState = GameState.MONSTERBOSSFIGHT;
                break;
            }
            case MONSTERBOSSFIGHT: {
                currentState = GameState.RUNABOSSFIGHT;
                break;
            }
            default: {
                break;
            }
        }
    }

    public static void fightWon() {
        switch (currentState) {
            case MONSTERBOSSFIGHT: {
                currentState = GameState.BOSSWIN;
                break;
            }
            case RUNABOSSFIGHT: {
                currentState = GameState.BOSSWIN;
                break;
            }
            default: {
                currentState = GameState.FIGHTWON;
            }
        }
    }

    public static void bossFight() {
        currentState = GameState.RUNABOSSFIGHT;
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

    public static void win() {
        currentState = GameState.WIN;
    }
}
