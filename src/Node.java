public class Node {

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

    public Node(boolean wumpus, boolean pit, boolean gold){
        this.wumpus = wumpus;
        this.pit = pit;
        this.gold = gold;
    }

    // 2nd constructor for the agent to keep track of what it knows
    public Node(boolean stench, boolean breeze, boolean glitter, boolean visited){
        this.stench = stench;
        this.breeze = breeze;
        this.visited = visited;
        danger = 0;
    }

    public boolean isWumpus() {
        return wumpus;
    }

    public boolean isPit() {
        return pit;
    }

    public boolean isGold() {
        return gold;
    }

    public void setWumpus(boolean wumpus){
        this.wumpus = wumpus;
    }

    public void setGold(boolean gold){
        this.gold = gold;
    }

    public void setStench() { stench = true; }

    public void setBreeze() { stench = true; }

    public void setGlitter(boolean glitter) { this.glitter = glitter; }

    public void setVisited() { visited = true; }

    public void incDanger() { danger++; }

    public boolean getStench() { return stench; }

    public boolean getBreeze() { return breeze; }

    public boolean getGlitter() { return glitter; }

    public boolean getVisited() { return visited; }

    public int getDanger() { return danger; }
}
