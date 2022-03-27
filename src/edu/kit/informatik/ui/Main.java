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
import java.util.ArrayList;
import java.util.List;

/**
 * The type Main.
 *
 * @author Hanne
 * @version 0.1
 */
public class Main {

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
        if (args.length != ZERO) { //if the args aren't empty it throws a exception
            throw new IllegalArgumentException("args have to be empty");
        }
        Main main = new Main();
        try {
            while (main.notEnd()) {
                switch (RunasAdventure.getState()) {
                    case INIT: { //initializes the game
                        main.init();
                        break;
                    }
                    case SHUFFLE: { //shuffles the cards and enters the first room of the dungeon
                        main.shuffle();
                        main.enterRoom();
                        break;
                    }
                    case RUNATURN: { //executes runas turn
                        main.nextStage();
                        main.runaAttack();
                        break;
                    }
                    case MONSTERTURN: { //executes the monsters turn
                        main.monsterAttack();
                        break;
                    }
                    case FIGHTWON: { //executes the reward stage
                        main.reward();
                        break;
                    }
                    case HEALING: { //executes the healing stage
                        main.heal();
                        main.enterRoom();
                        break;
                    }
                    case RUNABOSSFIGHT: { //runs runas turn in a boss fight
                        main.nextStage();
                        main.runaAttack();
                        break;
                    }
                    case MONSTERBOSSFIGHT: { //runs the boss monsters turn
                        main.monsterAttack();
                        break;
                    }
                    case BOSSWIN: { //executes the rewards after the boss win
                        main.printUpgrade();
                        main.heal();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        } catch (EndGameException ignored) { //ends the program if "quit" was the input and the exception got thrown
            return;
        }
        if (Statemachine.getCurrentState().equals(GameState.WIN)) { //prints the win message if the game is won
            Terminal.print("Runa wins");
        }
    }

    //-----------------------------------private primary functions------------------------------------------------------

    private void init() throws EndGameException {
        Terminal.printHello(); //prints the hello message
        switch (Terminal.selectTarget("number", THREE, true)) { //selects the class according to the
            case ZERO: { //input of the user
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

    private void shuffle() throws EndGameException { //shuffles the cards with the seeds given from the player
        Terminal.print("To shuffle ability cards and monsters, enter two seeds");
        List<Integer> selected = Terminal.selectMultiTarget(MAXSEED, TWO, true, "seeds");
        game.shuffleCards(selected.get(ONE), selected.get(ZERO));
    }

    private void enterRoom() { //enters the next room and prints out the stage
        game.enterRoom();
        Terminal.printStage(game.getCurrentRoom(), game.getCurrentFloor());
    }

    private void nextStage() { //checks the focus of runa and prints out the level again
        Terminal.printFocus(game.getRuna().getName(), game.checkChangeFocus(game.getRuna()));
        Terminal.printLevel(game.getRuna(), game.getCurrentFight());
    }

    private void runaAttack() throws EndGameException {
        Terminal.print("Select card to play");
        Terminal.printAbilities(game.getRuna()); //makes the player select a ability card to play
        Ability use = game.getRuna().getAbilities().get(
                Terminal.selectTarget("number", game.getRuna().getAbilities().size(), true));
        int target = ZERO; //inits the target of the attack
        if (game.getCurrentFight().size() > ONE && use.getType().equals(AbilityType.OFFENSIVE)) {
            Terminal.print("Select Runa's target."); //if the fight is against more than one opponent it makes the
            Terminal.printTargets(game.getCurrentFight()); //player choose a target to attack
            target = Terminal.selectTarget("number", game.getCurrentFight().size(), true);
        }
        Terminal.printUse(game.getRuna(), use); //prints the use Ability message
        switch (use.getUsageType()) { //switches between the type of the attack (Physical/Magic)
            case PHYSICAL: {
                int dice = ZERO;
                if (use.getType().equals(AbilityType.OFFENSIVE)) { //if needed gets a dice roll of the player
                    dice = Terminal.selectTarget("dice roll", game.getRuna().getDice().getValue(), true) + ONE;
                }
                Terminal.printDamage(game.getCurrentFight().get(target), game.usePhysicalAbility(game.getRuna(), game.getCurrentFight().get(target),
                        (PhysicalAbility) use, dice), use); //calculates and prints the damage of the attack
                break;
            }
            case MAGIC: { //calculates and prints the damage of the magic attack
                Terminal.printDamage(game.getCurrentFight().get(target), game.useMagicalAbility(game.getRuna(),
                        game.getCurrentFight().get(target), (MagicAbility) use).get(ZERO), use);
                break;
            }
            default: {
                break;
            }
        }
        Terminal.printDeath(game.checkDead()); //prints a death message if one occurred
    }

    private void monsterAttack() throws EndGameException {
        for (Monster monster: game.getCurrentFight()) { //calculates and prints the focus points for the monsters
            Terminal.printFocus(monster.getName(), game.checkChangeFocus(monster));
        }
        List<Monster> iterate = new ArrayList<>(game.getCurrentFight());
        for (Monster monster: iterate) { //iterates over the monster list
            Terminal.printUse(monster, monster.getNextMove());
            switch (monster.getNextMove().getUsageType()) { //switches between the type of the attack (Physical/Magical)
                case PHYSICAL: { //calculates and prints the physical damage of runa
                    int dmg = game.usePhysicalAbility(monster, game.getRuna(),
                            (PhysicalAbility) monster.getNextMove(), ZERO);
                    Terminal.printDamage(game.getRuna(), dmg, monster.getNextMove());
                    break;
                }
                case MAGIC: { //calculates and prints the magical damage
                    List<Integer> dmg = game.useMagicalAbility(monster, game.getRuna(),
                            (MagicAbility) monster.getNextMove()); //gets the damage list of the magical damage
                    Terminal.printDamage(game.getRuna(), dmg.get(ZERO), monster.getNextMove()); //prints the damage
                    if (dmg.size() > 1) { //if the damage list has a second entry the attacker took reflect damage
                        Terminal.printDamage(monster, dmg.get(ONE), new Reflect(1));
                    }
                    break;
                }
                default: {
                    break;
                }
            }
            Terminal.printDeath(game.checkDead()); //checks and prints the dead monsters
            if (Statemachine.getCurrentState().equals(GameState.LOST)) { //if the state is lost the function returns
                return;
            }
            monster.rmTop(); //cycles the monsters attacks
        }
        game.monsterTurnOver(); //ends the monster turn
    }

    private void printUpgrade() {
        game.fightReward(ZERO, null); //executes the fight reward zero in the game
        if (!Statemachine.getCurrentState().equals(GameState.WIN)) {
            for (Ability newAb: game.getRuna().getAbilities()) {
                for (Ability classAb: game.getRuna().getClassAbilities(game.getCurrentFloor())) {
                    if (newAb.equalsAbility(classAb)) {
                        Terminal.print("Runa gets " + Terminal.printAbility(newAb));
                    }
                }
            }
        }
    }

    private void reward() throws EndGameException {
        Terminal.print("Choose Runa's reward");
        Terminal.print("1) new ability cards");
        Terminal.print("2) next player dice");
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
                Terminal.print("Runa gets " + Terminal.printAbility(rewardPrint));
            }
        }
        else if (selected == TWO) {
            game.fightReward(selected, null);
            Terminal.print("Runa upgrades her die to a d" + game.getRuna().getDice().getValue());
        }
    }

    private List<Ability> selectReward(List<Ability> rewards) throws EndGameException {
        List<Ability> selected = new ArrayList<>();
        int sizeRewards = rewards.size();
        if (sizeRewards % TWO != ZERO) {
            sizeRewards++;
        }
        Terminal.print("Pick " + sizeRewards / TWO + " card(s) as loot");
        for (int i = 0; i < rewards.size(); i++) {
            Terminal.print((i + ONE) + ") " + Terminal.printAbility(rewards.get(i)));
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
            Terminal.print(Terminal.printRuna(game.getRuna(), false)
                    + " can discard ability cards for healing (or none)");
            Terminal.printAbilities(game.getRuna());
            List<Integer> selected = new ArrayList<>();
            if (game.getRuna().getAbilities().size() > TWO) {
                selected = Terminal.selectMultiTarget(
                        game.getRuna().getAbilities().size(), amount, false, "numbers");
            } else {
                int picked = Terminal.selectTarget("number",
                        game.getRuna().getAbilities().size(), false);
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
                    Terminal.print("Runa gains " + (int) damage + " health");
                }
                else {
                    Terminal.print("Runa gains " + found.size() * TEN + " health");
                }
            }
        }
    }

    private boolean notEnd() {
        return !Statemachine.getCurrentState().equals(GameState.LOST)
                && !Statemachine.getCurrentState().equals(GameState.WIN);
    }
}
