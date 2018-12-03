public class Node {

    private boolean wumpus;
    private boolean pit;
    private boolean gold;
    private boolean stench;
    private boolean breeze;
    private boolean visited;        // Used to track which nodes the agent has visited

    public Node(boolean wumpus, boolean pit, boolean gold){
        this.wumpus = wumpus;
        this.pit = pit;
        this.gold = gold;
    }

    // "fake" variable in constructor to be able to have 2nd constructor with same input types
    public Node(boolean stench, boolean breeze,  boolean visited, boolean fake){
        this.stench = stench;
        this.breeze = breeze;
        this.visited = visited;
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

    public void setWumpus(){
        wumpus = true;
    }

    public void setGold(){
        gold = true;
    }

    public void setStench() { stench = true; }

    public void setBreeze() { stench = true; }

    public void setVisited() { visited = true; }
}
