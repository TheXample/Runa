package edu.kit.informatik.util.states;

/**
 * The type Statemachine.
 *
 * @author Hanne
 * @version 0.1
 */
public final class Statemachine {

    private static GameState currentState = GameState.INIT;

    private Statemachine() {

    }

    /**
     * Next.
     */
    public static void next() {
        switch (currentState) {
            case INIT: {
                currentState = GameState.SHUFFLE;
                break;
            }
            case HEALING: {
                currentState = GameState.RUNATURN;
                break;
            }
            case RUNATURN: {
                currentState = GameState.MONSTERTURN;
                break;
            }
            case MONSTERTURN: {
                currentState = GameState.RUNATURN;
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

    /**
     * Fight won.
     */
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

    /**
     * Boss fight.
     */
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
     * Lost.
     */
    public static void lost() {
        currentState = GameState.LOST;
    }

    /**
     * Win.
     */
    public static void win() {
        currentState = GameState.WIN;
    }
}
