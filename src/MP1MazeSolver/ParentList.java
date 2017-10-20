package MP1MazeSolver;

import java.util.ArrayList;
import java.util.LinkedList;

public class ParentList {
    ArrayList<Square> pList;

    //constructor
    ParentList() {
        pList = new ArrayList<>();
    }

    void add(Square sq){
        pList.add(sq);
    }
}
