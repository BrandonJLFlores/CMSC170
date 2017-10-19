package MP1MazeSolver;

import java.util.LinkedList;

public class ParentList {
    LinkedList<Square> pList;

    //constructor
    ParentList() {
        pList = new LinkedList<>();
    }

    void add(Square sq){
        pList.add(sq);
    }

    boolean checkpList(int x, int y){
        for(Square sq : pList){
            if(sq.getX() == x && sq.getY() == y) return true;
        }
        return false;
    }
}
