package edu.kit.informatik.card;

import java.util.List;

public class Stack {

    private List<Card> cards ;

    public Stack(List<Card> cards) {
        this.cards = cards;
    }

    public Card removeTop() {
        Card top = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
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
