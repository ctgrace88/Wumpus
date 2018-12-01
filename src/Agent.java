public class Agent {
    private char direction;     // {N, E, S, W}
    private int xCoord;
    private int yCoord;
    private boolean arrow;      // True if Agent still has the arrow
    private boolean gold;       // True if Agent is carrying the gold

    public Agent(){
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
}
