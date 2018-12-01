public class Node {

    private boolean wumpus;
    private boolean pit;
    private boolean gold;

    public Node(boolean wumpus, boolean pit, boolean gold){
        this.wumpus = wumpus;
        this.pit = pit;
        this.gold = gold;
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
}
