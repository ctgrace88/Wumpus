/**
 * Main class to handle the progress through the assignment
 *
 * Team Members: Connor Grace & Ryley Rodriguez
 * CSCI 446 - Artificial Intelligence
 * Wumpus World - First Order Logic
 * Fall 2018
 */

public class Driver {
    public static void main (String[] args){
        Agent agent4;
        Map newMap = new Map();
        Node[][] map4;

        //generate a random 4x4 world
        map4 = newMap.buildMap(4);
        agent4 = new Agent(0, map4.length-1, map4);

        //set breezes, stenches, glitter
        newMap.setBreeze(map4);
        newMap.setStench(map4);
        newMap.setGlitter(map4);

        //print the world as seen from above
        System.out.println();
        newMap.printWorld(map4);

        //attempt to solve the generated world
        //agent4.solve();


        //map = newMap.buildMap(5);


        //map = newMap.buildMap(8);


        //map = newMap.buildMap(10);
    }
}
