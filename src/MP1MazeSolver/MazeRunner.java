package MP1MazeSolver;

import java.util.ArrayList;
import java.util.Collections;

public class MazeRunner {
    private Maze m;
    private Square start, end;
    private static OpenList openList;
    private static ClosedList closedList;
    private static int pathcost;


    private MazeRunner(String name){
        pathcost = 0;
        m = new Maze(name);
        start = m.getStartNode();
        end = m.getEndNode();
        openList = new OpenList();
        closedList = new ClosedList();
//        System.out.println(start.getX()+ " start " + start.getY());
//        System.out.println(end.getX()+ " end " + end.getY());
    }

    private void solve(){
        Square currentLocation = null;
        openList.add(start);
        ArrayList<Square> neighbours;
        while (openList.getSize() > 0 && !openList.contains(end)){
            Collections.sort(openList.getoList());
            currentLocation = openList.remove();
            closedList.add(currentLocation);
            neighbours = m.surrounding(currentLocation);
            for(Square square : neighbours){
                if(!closedList.contains(square) && square != null && square.getElement() != '%'){
                //orig soln nako ni
//                    square.setParent(currentLocation);
//                    square.setH(getHeuristic(2,square)); //1 if manhattan, else straight line
//                    square.setG(currentLocation.getG() + 1);
//                    square.setFn(square.getG() + square.getH());
//                    openList.add(square);

                    //simplified soln
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

        markPath(currentLocation);
    }

    private void markPath(Square square){
        while(square.hasParent()){
            square.setElement('.');
            pathcost++;
            square = square.getParent();
        }
        pathcost += 1; //since ang first gi check sa loop dili ang end node kundi ang tapad sa end node
                        // note, mu exit ang solve() if ma add na ang end node sa openlist :)
                        // add +1 para sa cost sa goal
    }

    private void print(){
        System.out.print(m.toString());
        //System.out.println("Path Cost: " + m.getPathCost());
        System.out.println("Path Cost: " + pathcost);
        System.out.println("Number of Nodes Expanded: " + closedList.getcList().size());
        System.out.println("Maximum Size of Frontier: " + openList.getFrontier());
    }


    private int getHeuristic(int i, Square square) {
        int dx = Math.abs(square.getX() - end.getX());
        int dy = Math.abs(square.getY() - end.getY());
        if(i == 1){//manhattan
            return dx + dy;
        }
        return Math.max(dx,dy);//straight line
    }

    public static ClosedList getClosedList() {
        return closedList;
    }

    public static OpenList getOpenList() {
        return openList;
    }

    public static void main(String[] args) {
        System.out.println("SMALL MAZE: ");
        MazeRunner small_Maze = new MazeRunner("exc maze/smallMaze.txt");
        small_Maze.solve();
        small_Maze.print();
        System.out.println();

        System.out.println("MEDIUM MAZE: ");
        MazeRunner medium_Maze = new MazeRunner("exc maze/mediumMaze.txt");
        medium_Maze.solve();
        medium_Maze.print();
        System.out.println();

        System.out.println("BIG MAZE: ");
        MazeRunner big_Maze = new MazeRunner("exc maze/bigMaze.txt");
        big_Maze.solve();
        big_Maze.print();
        System.out.println();

        System.out.println("OPEN MAZE: ");
        MazeRunner open_Maze = new MazeRunner("exc maze/openMaze.txt");
        open_Maze.solve();
        open_Maze.print();
        System.out.println();
    }

}
