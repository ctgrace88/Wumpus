public class Driver {
    public static void main (String[] args){
        Agent agent4;
        Map newMap = new Map();
        Node[][] map;

        map = newMap.buildMap(4);
        agent4 = new Agent(0, map.length-1);


        //map = newMap.buildMap(5);


        //map = newMap.buildMap(8);


        //map = newMap.buildMap(10);
    }
}
