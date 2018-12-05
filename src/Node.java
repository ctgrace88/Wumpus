/**
 * Class to represent a given position in the world
 */

public class Node {

    /**
     * Instance variables
     */
    // Used for the map setup
    private boolean wumpus;
    private boolean pit;
    private boolean gold;

    // Used for the agents knowledge of the map
    private boolean stench;
    private boolean breeze;
    private boolean glitter;
    private boolean visited;        // Used to track which nodes the agent has visited
    private int danger;             // Used to determine which node to move to

    /**
     * Constructor
     * @param wumpus whether or not a Wumpus is in the position
     * @param pit whether or not a Pit is in the position
     * @param gold whether or not the Gold is in the position
     */
    public Node(boolean wumpus, boolean pit, boolean gold){
        this.wumpus = wumpus;
        this.pit = pit;
        this.gold = gold;
    }

    /**
     * 2nd constructor for the agent to keep track of its knowledge base
     * @param stench whether or not a Stench is perceived
     * @param breeze whether or not a Breeze is perceived
     * @param glitter whether or not a Glitter is perceived
     * @param visited whether or not a position has been visited
     */
    public Node(boolean stench, boolean breeze, boolean glitter, boolean visited){
        this.stench = stench;
        this.breeze = breeze;
        this.visited = visited;
        danger = 0;
    }

    /**
     * Method to return whether a position contains the Wumpus
     * @return a boolean value
     */
    public boolean isWumpus() {
        return wumpus;
    }

    /**
     * Method to return whether a position is a Pit
     * @return a boolean value
     */
    public boolean isPit() {
        return pit;
    }

    /**
     * Method to return whether a position contains the Gold
     * @return a boolean value
     */
    public boolean isGold() {
        return gold;
    }

    /**
     * Method to set whether a Wumpus is in the world or not
     * @param wumpus a boolean value
     */
    public void setWumpus(boolean wumpus){
        this.wumpus = wumpus;
    }

    /**
     * Method to set whether a position has the Gold
     * @param gold a boolean value
     */
    public void setGold(boolean gold){
        this.gold = gold;
    }

    /**
     * Method to set whether a position has a Stench
     */
    public void setStench()
    {
        stench = true;
    }

    /**
     * Method to set whether a position has a Breeze
     */
    public void setBreeze() { stench = true; }

    /**
     * Method to set whether a position has a Glitter
     * @param glitter
     */
    public void setGlitter(boolean glitter) { this.glitter = glitter; }

    /**
     * Method to set whether a position has been visited
     */
    public void setVisited() { visited = true; }

    /**
     * Method to increase the danger level of a position
     */
    public void incDanger() { danger++; }

    /**
     * Method to return whether a position has a Stench
     * @return a boolean value
     */
    public boolean getStench() { return stench; }

    /**
     * Method to return whether a position has a Breeze
     * @return
     */
    public boolean getBreeze() { return breeze; }

    /**
     * Method to return whether a position has a Glitter
     * @return
     */
    public boolean getGlitter() { return glitter; }

    /**
     * Method to return whether a position has been visited
     * @return
     */
    public boolean getVisited() { return visited; }

    /**
     * Method to return whether a position is dangerous
     * @return
     */
    public int getDanger() { return danger; }


    /**
     * Overriden method to print the contents of a position
     */
    @Override
    public String toString()
    {
        return "Gold: " + gold + ", Wumpus: " + wumpus + ", Pit: " + pit;
    }
}
