package edu.kit.informatik.card;

import edu.kit.informatik.characters.Monster;

import java.util.ArrayList;
import java.util.List;

public class MonsterStack extends Stack{

    private final List<Monster> cards ;

    private static final int TOP = 0;

    public MonsterStack(List<Monster> cards) {
        this.cards = cards;
    }

    public void pushBottom(Monster input) {
        cards.add(input);
    }

}
