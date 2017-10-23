package MP1MazeSolver;

import java.util.ArrayList;
import java.util.Collections;

public class MazeRunner {
    private Maze m;
    private Square start, end;
    private static OpenList openList;
    private static ClosedList closedList;
    private static ArrayList<Square> path;

    public static void main(String[] args) {
        /*System.out.println("SMALL MAZE: ");
        MazeRunner small_Maze = new MazeRunner("exc maze/tinyMaze.lay.txt");
        small_Maze.solve();
        small_Maze.print();
        System.out.println();

        System.out.println("MEDIUM MAZE: ");
        MazeRunner medium_Maze = new MazeRunner("exc maze/mediumMaze.lay.txt");
        medium_Maze.solve();
        medium_Maze.print();
        System.out.println();

        System.out.println("BIG MAZE: ");
        MazeRunner big_Maze = new MazeRunner("exc maze/bigMaze.lay.txt");
        big_Maze.solve();
        big_Maze.print();
        System.out.println();
*/
        System.out.println("OPEN MAZE: ");
        MazeRunner open_Maze = new MazeRunner("exc maze/openMaze.lay.txt");
        open_Maze.solve();
        open_Maze.print();
        System.out.println();
    }

    private MazeRunner(String name){
        m = new Maze(name);
        start = m.getStartNode();
        end = m.getEndNode();
        openList = new OpenList();
        closedList = new ClosedList();
        path = new ArrayList<Square>();
    }

    public static ClosedList getClosedList() {
        return closedList;
    }

    public static OpenList getOpenList() {
        return openList;
    }

    void solve(){
        Square currentLocation = null;
        openList.add(start);
        ArrayList<Square> neighbours;

        while (openList.getSize() > 0 && !openList.contains(end)){
            Collections.sort(openList.getoList());
            currentLocation = openList.remove();
            closedList.add(currentLocation);
            neighbours = m.surrounding(currentLocation);
            for(Square square : neighbours){
                if(!closedList.contains(square) && square != null && !square.getElement().equals("%")){
                    if(!openList.contains(square) || square.getG() > currentLocation.getG() + 1){
                        square.setParent(currentLocation);
                        square.setH(getHeuristic(2,square)); //1 if manhattan, else straight line
                        square.setG(currentLocation.getG() + 1);
                        square.setFn(square.getG() + square.getH());
                    }
                    if(!openList.contains(square)) {
                        openList.add(square);
                    }

                }
            }
        }
        if(openList.contains(end)) {//only calculate pathcost if end is found
            markPath(currentLocation);
        }
    }

    private void markPath(Square square){
        while(square.hasParent()){
            if(!square.getElement().equals("P")){
                m.incPathCost();
                square.setElement(".");
            }
            path.add(square);
            square = square.getParent();
        }
        m.incPathCost(); // assumes goal is found
    }



    void print(){
        System.out.println(m.toString());
        System.out.println("Number of Nodes Expanded: " + closedList.getcList().size());
        System.out.println("Maximum Size of Frontier: " + openList.getFrontier());
        System.out.println("Path: " + m.getPathCost());
        System.out.println("Heuristic: "+getHeuristic(1));//Assume
        System.out.println("["+start.getX()+","+start.getY()+"] - ["+end.getX()+","+end.getY()+"] ");
    }


    int getHeuristic(int i) {
        return getHeuristic(i,start);
    }

    int getHeuristic(int i, Square square) {
        int dx = Math.abs(square.getX() - end.getX());
        int dy = Math.abs(square.getY() - end.getY());
        if(i == 1){//manhattan
            return dx + dy;
        }
        return Math.max(dx,dy);//straight line
    }

    MazeRunner(Maze m){
        this.m = m;
        start = m.getStartNode();
        end = m.getEndNode();
        openList = new OpenList();
        closedList = new ClosedList();
        path = new ArrayList<Square>();
    }

    int getPathCost(){
        return m.getPathCost();
    }

    Maze getMaze(){
        return m;
    }

    ArrayList<Square> getPath(){
        return path;
    }
    void reset(){//erase traversed path for printing
        for(Square square: path){
            square.setElement(" ");
        }
        end.setElement(" ");
        m.resetPathCost();
    }

    int getNodesExpanded(){
        return closedList.getcList().size();
    }

    int getFrontier(){
        return openList.getFrontier();
    }

}
