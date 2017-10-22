package MP1MazeSolver;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Collections;

public class Goal extends Square {
    private int pathCost;
    private int heuristic;
    private ArrayList<Square> path;
    private int frontier; // openlist
    private int nodesExpanded; // closed

    public Goal(String c, int x, int y) {
        super(c, x, y);
        pathCost = 0;
        heuristic = 0;
        path = new ArrayList<Square>();
    }

    public int getPathCost() {
        return pathCost;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void print(){
        System.out.println("Path Cost: "+pathCost);
        System.out.println("Frontier: "+heuristic);
        System.out.println("Expanded: "+heuristic);
    }

    public ArrayList<Square> getPath() {
        return path;
    }

    public void setPath(ArrayList<Square> newPath, Square goal) {
        newPath.add(0,goal);
        Collections.reverse(newPath);
        path.addAll(newPath);
        path = newPath;
    }

    public int getFrontier() {
        return frontier;
    }

    public void setFrontier(int frontier) {
        this.frontier = frontier;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
    }

    public void setNodesExpanded(int expanded) {
        this.nodesExpanded = expanded;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }
}
