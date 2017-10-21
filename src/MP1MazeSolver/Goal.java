package MP1MazeSolver;

import java.util.ArrayList;

public class Goal extends Square {
    private int pathCost;
    private int heuristic;

    public Goal(char c, int x, int y) {
        super(c, x, y);
        pathCost = 0;
        heuristic = 0;
    }

    public int getPathCost() {
        return pathCost;
    }

    public int getHeuristic() {
        return heuristic;
    }
    public void print(){
        System.out.println("pc: "+pathCost);
        System.out.println("heu:"+heuristic);
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }
}
