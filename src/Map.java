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

            // Place wumpus if node is valid, else try again (wumpus cannot be in a pit)
            if (!map[row][col].isPit() && row != size-1 && col != 0){
                map[row][col].setWumpus(true);
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

            // Place gold if node is valid, else try again (gold can be in a pit)
            if (row != size-1 && col != 0){
                map[row][col].setGold(true);
                goldPlaced = true;
            }
        }
        return map;
    }

    /**
     * Method to print the world as seen from above
     * @param world
     */
    public void printWorld(Node[][] world)
    {
        //initial data
        int worldSize = world.length;
        int k = 0;
        String sense = "";
        //int numCols = world[0].length;

        //print the necessary number of grid spaces
        for (int n = 0; n < worldSize; n++)
        {
            //print the top border
            for (int i = 0; i < worldSize; i++)
            {
                System.out.print(" -------------------------- ");
            }
            System.out.println();

            //print the inside border 5 times to make ample space for text
            for (int i = 0; i < 5; i++)
            {
                //print the inside borders (+1 for the right-most border)
                for (int j = 0; j < worldSize + 1; j++)
                {
                    //k++;

                    System.out.print(" |");

                    //only print data for viable Nodes
                    if (k < 4)
                    {
                        //String sense;
                        switch (i)
                        {
                            case 0:
                                sense = "\tStench: ";
                                break;
                            case 1:
                                sense = "\tBreeze: ";
                                break;
                            case 2:
                                //handling different boolean value lengths
                                if (world[n][k].isPit())
                                {
                                    sense = "\tPit: " + world[n][k].isPit() + "             ";
                                }
                                else
                                {
                                    sense = "\tPit: " + world[n][k].isPit() + "            ";
                                }
                                //sense = "\tPit: " + world[n][k].isPit() + "            ";
                                break;
                            case 3:
                                if (world[n][k].isWumpus())
                                {
                                    sense = "\tWumpus: " + world[n][k].isWumpus() + "          ";
                                }
                                else
                                {
                                    sense = "\tWumpus: " + world[n][k].isWumpus() + "         ";
                                }
                                //sense = "\tWumpus: " + world[n][k].isWumpus() + "          ";
                                break;
                            case 4:
                                if (world[n][k].isGold())
                                {
                                    sense = "\tGold: " + world[n][k].isGold() + "            ";
                                }
                                else
                                {
                                    sense = "\tGold: " + world[n][k].isGold() + "           ";
                                }
                                //sense = "\tGold: " + world[n][k].isGold() + "           ";
                                break;
                            default:
                                sense = "";
                                break;
                        }
                    }

                    //only print the senses numCols number of times
                    if (j < worldSize)
                    {
                        System.out.print(sense);
                    }

                    //handling different word lengths
                    if (sense.equals("\tPit: "))
                    {
                        System.out.print("                 ");
                    }

                    //handling different word lengths
                    if (sense.equals("\tGold: "))
                    {
                        System.out.print("                ");
                    }

                    //print same space for all other words
                    if (sense.equals("\tStench: ") || sense.equals("\tBreeze: ") || sense.equals("\tWumpus: "))
                    {
                        System.out.print("              ");
                    }
                    k++;
                }
                k = 0;
                System.out.println();
            }
        }
        //print the bottom border
        for (int i = 0; i < worldSize; i++)
        {
            System.out.print(" -------------------------- ");
        }
        System.out.println();
    }
}
