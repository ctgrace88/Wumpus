public class Agent {
    private char direction;     // {N, E, S, W}
    private int xCoord;
    private int yCoord;
    private boolean arrow;      // True if Agent still has the arrow
    private boolean gold;       // True if Agent is carrying the gold
    private Node[][] known;     // Agents knowledge of the map

    public Agent(int x, int y){
        xCoord = x;
        yCoord = y;
        direction = 'E';
        arrow = true;
        gold = false;
    }

    public void setX(int newX){
        xCoord = newX;
    }

    public void setY(int newY){
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
        else if (direction == 'E'){
            direction = 'W';
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
}
