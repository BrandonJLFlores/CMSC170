package MP1MazeSolver;

import java.util.ArrayList;

public class OpenList {
    private ArrayList<Square> oList;
    private int frontier;

    //constructor
    OpenList() {
        oList = new ArrayList<>();
        frontier = 0;
    }

    void add(Square sq){
        oList.add(sq);
        frontier += 1;
    }

    int getSize(){
        return oList.size();
    }

    public ArrayList<Square> getoList() {
        return oList;
    }

    Square remove(){
        return oList.remove(0);
    }

    boolean contains(Square square){
        return oList.contains(square);
    }

    int getFrontier(){
        return frontier;
    }
}
