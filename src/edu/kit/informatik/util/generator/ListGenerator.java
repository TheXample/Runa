package edu.kit.informatik.util.generator;

import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.magical.Focus;
import edu.kit.informatik.structure.card.abilities.magical.defensive.Reflect;
import edu.kit.informatik.structure.card.abilities.magical.offensive.Fire;
import edu.kit.informatik.structure.card.abilities.magical.offensive.Ice;
import edu.kit.informatik.structure.card.abilities.magical.offensive.Lightning;
import edu.kit.informatik.structure.card.abilities.magical.offensive.Water;
import edu.kit.informatik.structure.card.abilities.physical.defensive.Parry;
import edu.kit.informatik.structure.card.abilities.physical.offensive.Pierce;
import edu.kit.informatik.structure.card.abilities.physical.offensive.Slash;
import edu.kit.informatik.structure.card.abilities.physical.offensive.Swing;
import edu.kit.informatik.structure.card.abilities.physical.offensive.Thrust;
import edu.kit.informatik.structure.card.characters.Monster;
import edu.kit.informatik.structure.card.characters.monsters.one.Frog;
import edu.kit.informatik.structure.card.characters.monsters.one.Ghost;
import edu.kit.informatik.structure.card.characters.monsters.one.Gorgon;
import edu.kit.informatik.structure.card.characters.monsters.one.Skeleton;
import edu.kit.informatik.structure.card.characters.monsters.one.Spider;
import edu.kit.informatik.structure.card.characters.monsters.one.Goblin;
import edu.kit.informatik.structure.card.characters.monsters.one.Rat;
import edu.kit.informatik.structure.card.characters.monsters.one.Mushroomlin;
import edu.kit.informatik.structure.card.characters.monsters.two.Snake;
import edu.kit.informatik.structure.card.characters.monsters.two.DarkElf;
import edu.kit.informatik.structure.card.characters.monsters.two.ShadowBlade;
import edu.kit.informatik.structure.card.characters.monsters.two.Hornet;
import edu.kit.informatik.structure.card.characters.monsters.two.Tarantula;
import edu.kit.informatik.structure.card.characters.monsters.two.Bear;
import edu.kit.informatik.structure.card.characters.monsters.two.Mushroomlon;
import edu.kit.informatik.structure.card.characters.monsters.two.WildBoar;

import java.util.ArrayList;
import java.util.List;

/**
 * The type List generator.
 *
 * @author Hanne
 * @version 0.1
 */
public final class ListGenerator {

    private ListGenerator() {

    }

    /**
     * Generate floor list.
     *
     * @param level the level
     * @return the list
     */
    public static List<Monster> generateFloor(int level, int seed) {
        ArrayList<Monster> monsterList = new ArrayList<>();
        if (level == 1) {
            monsterList = new ArrayList<>(List.of(new Frog(seed), new Ghost(seed), new Gorgon(seed), new Skeleton(seed),
                    new Spider(seed), new Goblin(seed), new Rat(seed), new Mushroomlin(seed)));
        }
        if (level == 2) {
            monsterList = new ArrayList<>(List.of(new Snake(seed), new DarkElf(seed), new ShadowBlade(seed),
                    new Hornet(seed), new Tarantula(seed), new Bear(seed), new Mushroomlon(seed), new WildBoar(seed)));
        }
        return monsterList;
    }

    /**
     * Generate abilities list.
     *
     * @param currentFloor the current floor
     * @return the list
     */
    public static List<Ability> generateAbilities(int currentFloor) {
        return new ArrayList<>(List.of(new Slash(currentFloor), new Swing(currentFloor),
                new Thrust(currentFloor), new Pierce(currentFloor), new Parry(currentFloor), new Focus(currentFloor),
                new Reflect(currentFloor), new Water(currentFloor), new Ice(currentFloor), new Fire(currentFloor),
                new Lightning(currentFloor)));
    }
}
