public class Agent {
    private char direction;     // {N, E, S, W}
    private int xCoord;
    private int yCoord;
    private boolean arrow;      // True if Agent still has the arrow
    private boolean gold;       // True if Agent is carrying the gold
    private Node[][] known;     // Agents knowledge of the map
    private int score;

    public Agent(int x, int y){
        xCoord = x;
        yCoord = y;
        direction = 'E';
        arrow = true;
        gold = false;
    }

    public void solve(Node[][] map){
        checkEnvironment(map);
    }

    // Agent checks and updates it's map of the environment
    private void checkEnvironment(Node[][] map){
        // Sets agents current node to visited.
        known[yCoord][xCoord] = new Node(false, false, false, true);

        // Sets agents node to glitter if gold is in it.
        if (map[yCoord][xCoord].isGold()){
            known[yCoord][xCoord].setGlitter();
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
                known[yCoord][xCoord].setBreeze();            }
            if (map[yCoord+1][xCoord].isWumpus()){
                known[yCoord][xCoord].setStench();
            }
        }
        // Check West
        if(xCoord-1 > 0){
            if (map[yCoord][xCoord-1].isPit()){
                known[yCoord][xCoord].setBreeze();            }
            if (map[yCoord][xCoord-1].isWumpus()){
                known[yCoord][xCoord].setStench();
            }
        }
    }

    private void decision(Node[][] map){

    }

    private void setX(int newX){
        xCoord = newX;
    }

    private void setY(int newY){
        yCoord = newY;
    }

    private void forward(){
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
        gold = true;
    }

    private void shoot(){
        arrow = false;
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
