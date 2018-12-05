/**
 * Class to generate a map (world) for the agent to attempt a solution at
 */

import java.util.Random;

public class Map {

    /**
     * Constructor
     */
    public Map(){

    }

    /**
     * Method to build map of specified size
     * @param size
     * @return
     */
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

    /**
     * Method to mark the Pits' adjacent positions with a breeze
     * @param world
     */
    public void setBreeze(Node[][] world)
    {
        //loop through the rows
        for (int row = 0; row < world.length; row++)
        {
            //loop through the columns
            for (int col = 0; col < world[row].length; col++)
            {
                //the position is a Pit
                if (world[row][col].isPit())
                {
                    /* mark the (viable) adjacent Nodes with a Breeze */
                    //North
                    if (row > 0)
                    {
                        world[row-1][col].setBreeze();
                    }

                    //East
                    if (col < world.length - 1)
                    {
                        world[row][col+1].setBreeze();
                    }

                    //South
                    if (row < world.length - 1)
                    {
                        world[row+1][col].setBreeze();
                    }

                    //West
                    if (col > 0)
                    {
                        world[row][col-1].setBreeze();
                    }
                }
            }
        }
    }

    /**
     * Method to mark the Wumpus' position and adjacent positions with a stench
     * @param world
     */
    public void setStench(Node[][] world)
    {
        //loop through the rows
        for (int row = 0; row < world.length; row++)
        {
            //loop through the columns
            for (int col = 0; col < world[row].length; col++)
            {
                //the position has the Wumpus
                if (world[row][col].isWumpus())
                {
                    world[row][col].setStench();

                    /* mark the (viable) adjacent Nodes with a Stench */
                    //North
                    if (row > 0)
                    {
                        world[row-1][col].setStench();
                    }

                    //East
                    if (col < world.length - 1)
                    {
                        world[row][col+1].setStench();
                    }

                    //South
                    if (row < world.length - 1)
                    {
                        world[row+1][col].setStench();
                    }

                    //West
                    if (col > 0)
                    {
                        world[row][col-1].setStench();
                    }
                }
            }
        }
    }

    /**
     * Method to mark the Gold's position with glitter
     * @param world
     */
    public void setGlitter(Node[][] world)
    {
        //loop through the rows
        for (int row = 0; row < world.length; row++)
        {
            //loop through the columns
            for (int col = 0; col < world[row].length; col++)
            {
                //the position has the Gold
                if (world[row][col].isGold())
                {
                    world[row][col].setGlitter(true);
                }
            }
        }
    }

    /**
     * Method to randomly place Wumpus
     * @param map
     * @param size
     * @return
     */
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

    /**
     * Method to randomly place gold
     * @param map
     * @param size
     * @return
     */
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
