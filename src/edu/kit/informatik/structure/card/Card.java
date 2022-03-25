package edu.kit.informatik.structure.card;

/**
 * The type Card.
 *
 * @author Hannes
 * @version 0.1
 */
public class Card {

    private String name;

    /**
     * Instantiates a new Card.
     *
     * @param name the name
     */
    public Card(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
