package MP1MazeSolver;

import java.util.ArrayList;
import java.util.Collections;

public class MazeRunner {
    private Maze m;
    private Square[][] maze;
    private Square start, end;
    private OpenList openList;
    private ClosedList closedList;

    private MazeRunner(String name){
        m = new Maze(name);
        maze = m.getMaze();
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
                if(!openList.contains(currentLocation) && !closedList.contains(square) && square != null && square.getElement() != '%'){
                    square.setParent(currentLocation);
                    square.setH(getHeuristic(1,square));
                    square.setG(currentLocation.getG() + 1);
                    square.setFn(square.getG() + square.getH());
                    openList.add(square);
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
    }


    private int getHeuristic(int i, Square square) {
        int dx = square.getX() - end.getX();
        int dy = square.getY() - end.getY();
        if(i == 1){//manhattan
            return Math.abs(dx) + Math.abs(dy);
        }
        return Math.max(dx,dy);//straight line
    }

    public static void main(String[] args) {
        final String FILENAME = "openMaze.txt";//
        MazeRunner mazeRunner = new MazeRunner(FILENAME);
        mazeRunner.solve();
        mazeRunner.print();
    }

}
