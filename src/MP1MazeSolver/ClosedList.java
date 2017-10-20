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

    boolean contains(Square square){
        return cList.contains(square);
    }
}
