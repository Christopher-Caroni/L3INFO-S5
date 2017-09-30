package fil.coo.spawnables.beings;

import fil.coo.actions.*;
import fil.coo.other.Direction;
import fil.coo.spawnables.interfaces.Item;
import fil.coo.structures.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePlayer extends GameCharacter {

    private List<Action> actions;
    private String name;

    private int uniqueRoomCount;

    public GamePlayer() {
        super();

        name = "no_name";
        uniqueRoomCount = 1; // starting room
        initActions();
    }

    @Override
    void setSpecificAttributes(Random random) {
        setRandomStrength(random);
    }

    @Override
    protected void setRandomStrength(Random random) {
        strength = random.nextInt(30) + 30;
    }

    private void initActions() {
        actions = new ArrayList<>();
        actions.add(new Attack());
        actions.add(new Look());
        actions.add(new Move());
        actions.add(new Rest());
        actions.add(new PickupItem());
        actions.add(new DisplayStats());
    }

    /**
     * Moves the player to the room in the <b>direction</b>
     *
     * @param direction the direction from the player's current room to the neighbour
     */
    public void moveToDirection(Direction direction) {
        this.currentRoom = this.currentRoom.getNeighbour(direction);
        revealCurrentRoom();
        incrementUniqueRoomCount();
    }


    /**
     * @return if the player reached the exit: the room is revealed, contains no monsters and room is exit.
     */
    public boolean reachedExit() {
        return currentRoom.isExit() && !currentRoom.hasMonsters() && isCurrentRoomRevealed();
    }

    /**
     * Goes through the player's actions, and returns only those that are possible in his current situation.
     *
     * @return a List of the only possible actions
     */
    public List<Action> getPossibleActions() {
        List<Action> possibleActions = new ArrayList<>(actions.size());
        for (Action checkAction : actions) {
            if (checkAction.isPossible(this)) {
                possibleActions.add(checkAction);
            }
        }
        return possibleActions;
    }


    @Override
    public String getMenuDescription() {
        return name + " - " + getHP() + " HP, " + getStrength() + " strength";
    }

    /**
     * The <b>item</b> used has finished its {@link Item#use(GamePlayer)} method and should now be removed from the game.
     *
     * @param item the item used
     */
    public void confirmItemUseFromRoom(Item item) {
        currentRoom.removeItem(item);
    }

    /**
     * @param cost the cost of whatever needs to be evaluated. Can be either negative or positive
     * @return whether the player has enough gold for the absolute value of <b>cost</b>
     */
    public boolean hasEnoughGold(int cost) {
        return gold >= Math.abs(cost);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean hasItemsInCurrentRoom() {
        return currentRoom.hasItems();
    }

    public List<Item> getItemsFromRoom() {
        return currentRoom.getItems();

    }

    public void revealCurrentRoom() {
        currentRoom.setRevealed(true);
    }

    public void hideCurrentRoom() {
        currentRoom.setRevealed(false);
    }

    public boolean isCurrentRoomRevealed() {
        return currentRoom.isRevealed();
    }

    public int getUniqueRoomCount() {
        return uniqueRoomCount;
    }

    private void incrementUniqueRoomCount() {
        uniqueRoomCount++;
    }

    /**
     * @return a list of directions where the player can move, according to his current room's {@link Room#getNeighboursDirections()}
     */
    public List<Direction> getPossibleMoveDirections() {
        return currentRoom.getNeighboursDirections();
    }

    public boolean currentRoomHasNeighbour() {
        return currentRoom.getNumberOfNeighbours() > 0;
    }

    public boolean hasEnoughStrength(int cost) {
        return strength >= Math.abs(cost);
    }
}
