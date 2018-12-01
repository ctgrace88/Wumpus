import java.util.Random;

public class Map {

    public Map(){

    }

    // Build map of specified size
    public Node[][] buildMap(int size){
        Random rand = new Random();
        Node[][] map = new Node[size][size];
        Node node;

        // Place pits on map
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                // Don't put anything in the Agent's starting node
                if (i != size-1 && j != 0) {
                    // Place pit at 20% chance
                    if ((rand.nextInt(10) + 1) <= 2) {
                        node = new Node(false, true, false);
                    }
                    else
                        node = new Node(false, false, false);
                    map[i][j] = node;
                }
                // Don't put anything in Agent's starting node
                else map[i][j] = new Node(false, false, false);
            }
        }
        // Place wumpus
        map = placeWumpus(map, size);
        // Place gold
        map = placeGold(map, size);
        return map;
    }

    // Randomly place wumpus
    private Node[][] placeWumpus(Node[][] map, int size){
        boolean wumpusPlaced = false;
        Random rand = new Random();
        int row;
        int col;

        while (!wumpusPlaced){
            // Pick random row and column
            row = rand.nextInt(size-1);
            col = rand.nextInt(size-1);

            // Place wumpus if node is valid, else try again
            if (!map[row][col].isPit() && row != size-1 && col != 0){
                map[row][col].setWumpus();
                wumpusPlaced = true;
            }
        }
        return map;
    }

    // Randomly place gold
    private Node[][] placeGold(Node[][] map, int size){
        boolean goldPlaced = false;
        Random rand = new Random();
        int row;
        int col;

        while (!goldPlaced){
            // Pick random row and column
            row = rand.nextInt(size-1);
            col = rand.nextInt(size-1);

            // Place wumpus if node is valid, else try again
            if (!map[row][col].isPit() && row != size-1 && col != 0){
                map[row][col].setGold();
                goldPlaced = true;
            }
        }
        return map;
    }
}
