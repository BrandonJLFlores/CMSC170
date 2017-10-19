package MP1MazeSolver;

import java.util.ArrayList;

public class ClosedList {
    private ArrayList<Square> cList;

    //constructor
    ClosedList() {
        cList = new ArrayList<>();
    }

    void add(Square sq){
        cList.add(sq);
    }

//    boolean checkcList(int x, int y){
//        for(Square sq : cList){
//            if(sq.getX() == x && sq.getY() == y) return true;
//        }
//        return false;
//    }

    boolean contains(Square square){
        return cList.contains(square);
    }
}
