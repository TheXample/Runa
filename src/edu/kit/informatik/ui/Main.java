package edu.kit.informatik.ui;

import edu.kit.informatik.util.parser.EndGameException;
import edu.kit.informatik.structure.card.abilities.Ability;
import edu.kit.informatik.structure.card.abilities.AbilityType;
import edu.kit.informatik.structure.card.abilities.MagicAbility;
import edu.kit.informatik.structure.card.abilities.PhysicalAbility;
import edu.kit.informatik.structure.card.abilities.magical.defensive.Reflect;
import edu.kit.informatik.structure.card.characters.Monster;
import edu.kit.informatik.structure.card.characters.Runa;
import edu.kit.informatik.structure.card.characters.RunaType;
import edu.kit.informatik.util.states.GameState;
import edu.kit.informatik.structure.RunasAdventure;
import edu.kit.informatik.util.states.Statemachine;
import edu.kit.informatik.util.terminal.Terminal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Main.
 *
 * @author Hanne
 * @version 0.1
 */
public class Main {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private static RunasAdventure game;

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final int TWO = 2;

    private static final int THREE = 3;

    private static final int FOUR = 4;

    private static final int TEN = 10;

    private static final int MAXSEED = 2147483647;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IllegalArgumentException the illegal argument exception
     */
    public static void main(String[] args) throws IllegalArgumentException {
        if (args.length != ZERO) {
            throw new IllegalArgumentException("args have to be empty");
        }
        Main main = new Main();
        try {
            while (main.notEnd()) {
                switch (RunasAdventure.getState()) {
                    case INIT: {
                        main.init();
                        break;
                    }
                    case SHUFFLE: {
                        main.shuffle();
                        main.enterRoom();
                        break;
                    }
                    case RUNATURN: {
                        main.nextStage();
                        main.runaAttack();
                        break;
                    }
                    case MONSTERTURN: {
                        main.monsterAttack();
                        break;
                    }
                    case FIGHTWON: {
                        main.reward();
                        break;
                    }
                    case HEALING: {
                        main.heal();
                        main.enterRoom();
                        break;
                    }
                    case RUNABOSSFIGHT: {
                        main.nextStage();
                        main.runaAttack();
                        break;
                    }
                    case MONSTERBOSSFIGHT: {
                        main.monsterAttack();
                        break;
                    }
                    case BOSSWIN: {
                        main.printUpgrade();
                        main.heal();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        } catch (EndGameException ignored) {
            return;
        }
        if (Statemachine.getCurrentState().equals(GameState.WIN)) {
            System.out.println("Runa wins");
        }
    }

    //-----------------------------------private primary functions------------------------------------------------------

    private void init() throws EndGameException {
        Terminal.printHello();
        switch (Terminal.selectTarget("number", THREE, true)) {
            case ZERO: {
                game = new RunasAdventure(RunaType.WARRIOR);
                return;
            }
            case ONE: {
                game = new RunasAdventure(RunaType.MAGE);
                return;
            }
            case TWO: {
                game = new RunasAdventure(RunaType.PALADIN);
                return;
            }
            default: {
            }
        }
    }

    private void shuffle() throws EndGameException {
        System.out.println("To shuffle ability cards and monsters, enter two seeds");
        List<Integer> selected = Terminal.selectMultiTarget(MAXSEED, TWO, true, "seeds");
        game.shuffleCards(selected.get(ONE), selected.get(ZERO));
    }

    private void enterRoom() {
        game.enterRoom();
        Terminal.printStage(game.getCurrentRoom(), game.getCurrentFloor());
    }

    private void nextStage() {
        Terminal.printFocus(game.getRuna().getName(), game.checkChangeFocus(game.getRuna()));
        Terminal.printLevel(game.getRuna(), game.getCurrentFight());
    }

    private void runaAttack() throws EndGameException {
        System.out.println("Select card to play");
        Terminal.printAbilities(game.getRuna());
        Ability use = game.getRuna().getAbilities().get(
                Terminal.selectTarget("number", game.getRuna().getAbilities().size(), true));
        int target = ZERO;
        if (game.getCurrentFight().size() > ONE && use.getType().equals(AbilityType.OFFENSIVE)) {
            System.out.println("Select Runa's target.");
            Terminal.printTargets(game.getCurrentFight());
            target = Terminal.selectTarget("number", game.getCurrentFight().size(), true);
        }
        Terminal.printUse(game.getRuna(), use);
        switch (use.getUsageType()) {
            case PHYSICAL: {
                int dice = ZERO;
                if (use.getType().equals(AbilityType.OFFENSIVE)) {
                    dice = Terminal.selectTarget("dice roll", game.getRuna().getDice().getValue(), true) + ONE;
                }
                int dmg = game.usePhysicalAbility(game.getRuna(), game.getCurrentFight().get(target),
                        (PhysicalAbility) use, dice);
                if (use.getType().equals(AbilityType.OFFENSIVE)) {
                    Terminal.printDamage(game.getCurrentFight().get(target), dmg, use);
                }
                break;
            }
            case MAGIC: {
                Monster current = game.getCurrentFight().get(target);
                List<Integer> dmg = game.useMagicalAbility(game.getRuna(), game.getCurrentFight().get(target),
                        (MagicAbility) use);
                if (use.getType().equals(AbilityType.OFFENSIVE)) {
                    Terminal.printDamage(current, dmg.get(ZERO), use);
                }
                break;
            }
            default: {
                break;
            }
        }
        Terminal.printDeath(game.checkDead());
    }

    private void monsterAttack() throws EndGameException {
        for (Monster monster: game.getCurrentFight()) {
            Terminal.printFocus(monster.getName(), game.checkChangeFocus(monster));
        }
        List<Monster> iterate = new ArrayList<>(game.getCurrentFight());
        for (Monster monster: iterate) {
            Terminal.printUse(monster, monster.getNextMove());
            switch (monster.getNextMove().getUsageType()) {
                case PHYSICAL: {
                    int dmg = game.usePhysicalAbility(monster, game.getRuna(),
                            (PhysicalAbility) monster.getNextMove(), ZERO);
                    Terminal.printDamage(game.getRuna(), dmg, monster.getNextMove());
                    break;
                }
                case MAGIC: {
                    List<Integer> dmg = game.useMagicalAbility(monster, game.getRuna(),
                            (MagicAbility) monster.getNextMove());
                    Terminal.printDamage(game.getRuna(), dmg.get(ZERO), monster.getNextMove());
                    if (dmg.size() > 1) {
                        Terminal.printDamage(monster, dmg.get(ONE), new Reflect(1));
                    }
                    break;
                }
                default: {
                    break;
                }
            }
            Terminal.printDeath(game.checkDead());
            if (Statemachine.getCurrentState().equals(GameState.LOST)) {
                return;
            }
            monster.rmTop();
        }
        game.monsterTurnOver();
    }

    private void printUpgrade() {
        game.fightReward(ZERO, null);
        if (!Statemachine.getCurrentState().equals(GameState.WIN)) {
            for (Ability newAb: game.getRuna().getAbilities()) {
                for (Ability classAb: game.getRuna().getClassAbilities(game.getCurrentFloor())) {
                    if (newAb.equalsAbility(classAb)) {
                        System.out.println("Runa gets " + Terminal.printAbility(newAb));
                    }
                }
            }
        }
    }

    private void reward() throws EndGameException {
        System.out.println("Choose Runa's reward");
        System.out.println("1) new ability cards");
        System.out.println("2) next player dice");
        int selected = Terminal.selectTarget("number", TWO, true) + ONE;
        if (selected == 1) {
            List<Ability> drawnCards = new ArrayList<>();
            if (game.getCurrentRoom() == ONE) {
                for (int i = 0; i < TWO; i++) {
                    drawnCards.add(game.getTopAbility());
                }
            }
            else if (game.getCurrentRoom() > 1) {
                for (int i = 0; i < FOUR; i++) {
                    Ability toAdd = game.getTopAbility();
                    if (toAdd != null) {
                        drawnCards.add(toAdd);
                    }
                }
            }
            List<Ability> chosen = selectReward(drawnCards);
            game.fightReward(selected, chosen);
            for (Ability rewardPrint: chosen) {
                System.out.println("Runa gets " + Terminal.printAbility(rewardPrint));
            }
        }
        else if (selected == TWO) {
            game.fightReward(selected, null);
            System.out.println("Runa upgrades her die to a d" + game.getRuna().getDice().getValue());
        }
    }

    private List<Ability> selectReward(List<Ability> rewards) throws EndGameException {
        List<Ability> selected = new ArrayList<>();
        int sizeRewards = rewards.size();
        if (sizeRewards % TWO != ZERO) {
            sizeRewards++;
        }
        System.out.println("Pick " + sizeRewards / TWO + " card(s) as loot");
        for (int i = 0; i < rewards.size(); i++) {
            System.out.println((i + ONE) + ") " + Terminal.printAbility(rewards.get(i)));
        }
        List<Integer> picked = new ArrayList<>();
        if (sizeRewards / TWO > ONE) {
            picked = Terminal.selectMultiTarget(rewards.size(), sizeRewards / 2, true, "numbers");
        }
        else {
            picked.add(Terminal.selectTarget("number", rewards.size(), true));
        }
        for (Integer pick: picked) {
            selected.add(rewards.get(pick));
        }
        return selected;
    }

    private void heal() throws EndGameException {
        double damage = Runa.getMaxhealth() - game.getRuna().getHealthPoints();
        int amount = (int) Math.ceil(damage / TEN);
        if (amount == game.getRuna().getAbilities().size()) {
            amount--;
        }
        if (amount > ZERO && game.getRuna().getAbilities().size() > ONE) {
            System.out.println(Terminal.printRuna(game.getRuna(), false)
                    + " can discard ability cards for healing (or none)");
            Terminal.printAbilities(game.getRuna());
            List<Integer> selected = new ArrayList<>();
            if (game.getRuna().getAbilities().size() > TWO) {
                selected = Terminal.selectMultiTarget(
                        game.getRuna().getAbilities().size(), amount, false, "numbers");
            } else {
                int picked = Terminal.selectTarget("number", game.getRuna().getAbilities().size(), false);
                if (picked != -ONE) {
                    selected.add(picked);
                }
            }
            List<Ability> found = new ArrayList<>();
            for (int i = ZERO; i <= game.getRuna().getAbilities().size(); i++) {
                if (selected.contains(i)) {
                    found.add(game.getRuna().getAbilities().get(i));
                }
            }
            if (found.size() > ZERO) {
                game.heal(found);
                if (damage < found.size() * TEN) {
                    System.out.println("Runa gains " + (int) damage + " health");
                }
                else {
                    System.out.println("Runa gains " + found.size() * TEN + " health");
                }
            }
        }
    }

    private boolean notEnd() {
        return !Statemachine.getCurrentState().equals(GameState.LOST)
                && !Statemachine.getCurrentState().equals(GameState.WIN);
    }
}
