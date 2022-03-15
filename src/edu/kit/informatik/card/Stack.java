package edu.kit.informatik.card;

import java.util.List;

public class Stack {

    private static final int FIRST = 0;
    private List<Card> cards ;

    public Stack(List<Card> cards) {
        this.cards = cards;
    }

    public Card removeTop() {
        Card top = cards.get(FIRST);
        cards.remove(FIRST);
        return top;
    }

    public void pushBottom(Card input) {
        cards.add(input);
    }

    public 
}
