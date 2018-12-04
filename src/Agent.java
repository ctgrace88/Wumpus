public class Agent {
    private char direction;     // {N, E, S, W}
    private int xCoord;
    private int yCoord;
    private boolean arrow;      // True if Agent still has the arrow
    private boolean gold;       // True if Agent is carrying the gold
    private Node[][] known;     // Agents knowledge of the map
    private Node[][] map;       // Map agent does not have access to
    private int score;
    private boolean dead;       // False = alive, True = dead

    public Agent(int x, int y, Node[][] map){
        xCoord = x;
        yCoord = y;
        this.map = map;
        direction = 'E';
        arrow = true;
        gold = false;
        dead = false;
    }

    public void solve(){
        // While agent is alive
        while (!dead) {
            checkEnvironment();
            decision();
        }

        // Print analysis/ending results of game
        analysis();
    }

    // Agent checks and updates it's map of the environment
    private void checkEnvironment(){
        // Sets agents current node to visited.
        known[yCoord][xCoord] = new Node(false, false, false, true);

        // Sets agents node to glitter if gold is in it.
        if (map[yCoord][xCoord].isGold()){
            known[yCoord][xCoord].setGlitter(true);
        }

        // Checks all adjacent nodes. Sets agents node to breeze if a pit is adjacent. Sets agents node to stench if wumpus is adjacent.
        // Check North
        if(yCoord-1 >= 0){
            if (map[yCoord-1][xCoord].isPit()){
                known[yCoord][xCoord].setBreeze();
            }
            if (map[yCoord-1][xCoord].isWumpus()){
                known[yCoord][xCoord].setStench();
            }
        }
        // Check East
        if(xCoord+1 < map.length){
            if (map[yCoord][xCoord+1].isPit()){
                known[yCoord][xCoord].setBreeze();
            }
            if (map[yCoord][xCoord+1].isWumpus()){
                known[yCoord][xCoord].setStench();
            }
        }
        // Check South
        if(yCoord+1 < map.length){
            if (map[yCoord+1][xCoord].isPit()){
                known[yCoord][xCoord].setBreeze();
            }
            if (map[yCoord+1][xCoord].isWumpus()){
                known[yCoord][xCoord].setStench();
            }
        }
        // Check West
        if(xCoord-1 > 0){
            if (map[yCoord][xCoord-1].isPit()){
                known[yCoord][xCoord].setBreeze();
            }
            if (map[yCoord][xCoord-1].isWumpus()){
                known[yCoord][xCoord].setStench();
            }
        }
    }

    private void decision(){
        int min = 999999999;    // lowest danger level
        char dir;               // direction of lowest danger level

        // Perform danger analysis (if current node has breeze or stench, add 1 danger level per each
        // to adjacent nodes
        if(known[yCoord][xCoord].getBreeze()){
            // North
            if(yCoord-1 >= 0) {
                known[yCoord-1][xCoord].incDanger();
            }
            // East
            if(xCoord+1 < known.length){
                known[yCoord][xCoord+1].incDanger();
            }
            // South
            if(yCoord+1 < known.length){
                known[yCoord+1][xCoord].incDanger();
            }
            // West
            if(xCoord-1 > 0){
                known[yCoord][xCoord-1].incDanger();
            }
        }
        if(known[yCoord][xCoord].getStench()){
            // North
            if(yCoord-1 >= 0) {
                known[yCoord-1][xCoord].incDanger();
            }
            // East
            if(xCoord+1 < known.length){
                known[yCoord][xCoord+1].incDanger();
            }
            // South
            if(yCoord+1 < known.length){
                known[yCoord+1][xCoord].incDanger();
            }
            // West
            if(xCoord-1 > 0){
                known[yCoord][xCoord-1].incDanger();
            }
        }

        // Find lowest danger level
        if(yCoord-1 >= 0 && known[yCoord-1][xCoord].getDanger() < min){
            min = known[yCoord-1][xCoord].getDanger();
            dir = 'N';
        }
        if(xCoord+1 < known.length && known[yCoord][xCoord+1].getDanger() < min){
            min = known[yCoord-1][xCoord].getDanger();
            dir = 'E';
        }
        if(yCoord+1 < known.length && known[yCoord+1][xCoord].getDanger() < min){
            min = known[yCoord-1][xCoord].getDanger();
            dir = 'S';
        }
        if(xCoord-1 > 0 && known[yCoord][xCoord-1].getDanger() < min){
            min = known[yCoord-1][xCoord].getDanger();
            dir = 'W';
        }

        // Move in direction of lowest danger level

        /////////*****************/////////////**************//////////********
        // This is where I left off
        ///////////***************************///////***************//////////**
    }

    // Print out ending messages
    private void analysis(){
        System.out.println("Game Finished:");
        System.out.print("Agent Status: ");
        // If alive
        if (!dead) {
            if (gold) {
                System.out.print("Escaped alive with the gold\n");
            }
            else
                System.out.print("Escaped alive without the gold\n");
        }
        // If dead
        else {
            if (map[yCoord][xCoord].isWumpus())
                System.out.print("Killed by the Wumpus\n");
            else // Fell in a pit
                System.out.print("Died in a pit\n");
        }
        System.out.println("Score: " + score);
        System.out.println("Cells Entered: " + getVisited());
    }

    // Return number of cells visited
    private int getVisited(){
        int numVisited = 0;
        for (int i = 0; i < known.length; i++){
            for (int j = 0; j < known.length; j++){
                if (known[i][j].getVisited())
                    numVisited++;
            }
        }
        return numVisited;
    }

    private void setX(int newX){
        xCoord = newX;
    }

    private void setY(int newY){
        yCoord = newY;
    }

    private void forward(){
        // Costs 1 point to move
        score++;

        if(direction == 'N'){
            yCoord -= 1;
        }
        else if (direction == 'E'){
            xCoord += 1;
        }
        else if (direction == 'S'){
            yCoord += 1;
        }
        else{   //direction == 'W'
            yCoord -= 1;
        }
    }

    private void turnLeft(){
        if(direction == 'N'){
            direction = 'W';
        }
        else if (direction == 'E'){
            direction = 'N';
        }
        else if (direction == 'S'){
            direction = 'E';
        }
        else{   //direction == 'W'
            direction = 'S';
        }
    }

    private void turnRight(){
        if(direction == 'N'){
            direction = 'E';
        }
        else if (direction == 'E'){
            direction = 'S';
        }
        else if (direction == 'S'){
            direction = 'W';
        }
        else{   //direction == 'W'
            direction = 'N';
        }
    }

    private void getGold(){
        // Pick up gold
        gold = true;

        // Remove gold and glitter from maps
        for (int i = 0; i < known.length; i++) {
            for (int j = 0; j < known.length; j++) {
                map[i][j].setGold(false);
                known[i][j].setGlitter(false);
            }
        }
    }

    // Return true if Wumpus killed, otherwise false
    private boolean shoot(){
        arrow = false;
        int x = xCoord;
        int y = yCoord;

        // North
        if (direction == 'N'){
            while(y >= 0){
                if (map[y][x].isWumpus()) {
                    removeWumpus();
                    return true;
                }
                else
                    y--;
            }
        }
        // South
        if (direction == 'E'){
            while(x < map.length){
                if (map[y][x].isWumpus()) {
                    removeWumpus();
                    return true;
                }
                else
                    x++;
            }
        }
        // East
        if (direction == 'S'){
            while(y < map.length){
                if (map[y][x].isWumpus()) {
                    removeWumpus();
                    return true;
                }
                else
                    y++;
            }
        }
        // West
        else {
            while(x >= 0){
                if (map[y][x].isWumpus()) {
                    removeWumpus();
                    return true;
                }
                else
                    x--;
            }
        }
        return false;
    }

    private void removeWumpus(){
        for (int i = 0; i < known.length; i++) {
            for (int j = 0; j < known.length; j++) {
                map[i][j].setWumpus(false);
            }
        }
    }

    private void changeScore(int add){
        score += add;
    }

    private int getScore(){
        return score;
    }

    private void initKnown(){

    }
}
