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
            case INIT, BOSSWIN -> currentState = GameState.SHUFFLE;
            case HEALING, MONSTERTURN, SHUFFLE -> currentState = GameState.RUNATURN;
            case RUNATURN -> currentState = GameState.MONSTERTURN;
            case FIGHTWON -> currentState = GameState.HEALING;
            case RUNABOSSFIGHT -> currentState = GameState.MONSTERBOSSFIGHT;
            case MONSTERBOSSFIGHT -> currentState = GameState.RUNABOSSFIGHT;
        }
    }

    /**
     * Fight won.
     */
    public static void fightWon() {
        switch (currentState) {
            case MONSTERBOSSFIGHT, RUNABOSSFIGHT -> currentState = GameState.BOSSWIN;
            default -> currentState = GameState.FIGHTWON;
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
