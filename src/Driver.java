public class Driver {
    public static void main (String[] args){
        Agent agent4;
        Map newMap = new Map();
        Node[][] map4;

        map4 = newMap.buildMap(4);
        agent4 = new Agent(0, map4.length-1);
        agent4.solve(map4);


        //map = newMap.buildMap(5);


        //map = newMap.buildMap(8);


        //map = newMap.buildMap(10);
    }
}
