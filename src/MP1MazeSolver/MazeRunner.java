package MP1MazeSolver;

import java.util.ArrayList;
import java.util.Collections;

public class MazeRunner {
    private Maze m;
    //    private Square[][] maze;
    private Square start, end;
    private static OpenList openList;
    private static ClosedList closedList;

    private MazeRunner(String name){
        m = new Maze(name);
        //maze = m.getMaze();
        start = m.getStartNode();
        end = m.getEndNode();
        openList = new OpenList();
        closedList = new ClosedList();
        System.out.println(start.getX()+ " start " + start.getY());
        System.out.println(end.getX()+ " end " + end.getY());
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

                    //simplified soln nila ni
                    if(!openList.contains(square) || square.getG() > currentLocation.getG() + 1){
                        square.setParent(currentLocation);
                        square.setH(getHeuristic(2,square)); //1 if manhattan, else straight line
                        square.setG(currentLocation.getG() + 1);
                        square.setFn(square.getG() + square.getH());
                    }
                    if(!openList.contains(square)) {
                        openList.add(square);
                    }

                    //ila soln ni below
//                    if(!openList.contains(square)){
//                        square.setParent(currentLocation);
//                        square.setH(getHeuristic(1,square)); //1 if manhattan, else straight line
//                        square.setG(currentLocation.getG() + 1);
//                        square.setFn(square.getG() + square.getH());
//                        openList.add(square);
//                    }
//                    else{
//                        if(square.getG() > currentLocation.getG() + 1){
//                            square.setParent(currentLocation);
//                            square.setH(getHeuristic(1,square));
//                            square.setG(currentLocation.getG() + 1);
//                            square.setFn(square.getG() + square.getH());
//                        }
//                    }
                }
            }
        }

        markPath(currentLocation);
    }

    private void markPath(Square square){
        while(square.hasParent()){
            if(square.getElement() != 'P'){
                square.setElement('.');
            }
            square = square.getParent();
        }
    }

    private void print(){
        System.out.println(m.toString());

        System.out.println("frontier lmao: " + openList.getFrontier());
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
        final String FILENAME = "openMaze.txt";//
        MazeRunner mazeRunner = new MazeRunner(FILENAME);
        mazeRunner.solve();
        mazeRunner.print();
    }

}
