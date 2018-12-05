public class Driver {
    public static void main (String[] args){
        Agent agent4;
        Map newMap = new Map();
        Node[][] map4;

        map4 = newMap.buildMap(4);
        agent4 = new Agent(0, map4.length-1, map4);

        //print the world as seen from above
        System.out.println();
        newMap.printWorld(map4);

        //agent4.solve();


        //map = newMap.buildMap(5);


        //map = newMap.buildMap(8);


        //map = newMap.buildMap(10);
    }
}
