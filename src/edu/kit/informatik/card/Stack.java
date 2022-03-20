package edu.kit.informatik.card;

import edu.kit.informatik.characters.Monster;

import java.util.ArrayList;
import java.util.List;

public class Stack {

    private final List<Card> cards ;

    private static final int TOP = 0;

    public Stack(List<Card> cards) {
        this.cards = cards;
    }

    public Stack() {
        this.cards = new ArrayList<>();
    }

    public Card removeTop() {
        Card top = cards.get(TOP);
        cards.remove(TOP);
        return top;
    }

    public void pushBottom(Card input) {
        cards.add(input);
    }

    public Card cycle() {
        Card curr = removeTop();
        pushBottom(curr);
        return curr;
    }
}
