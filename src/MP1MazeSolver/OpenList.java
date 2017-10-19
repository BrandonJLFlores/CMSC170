package MP1MazeSolver;

import java.util.ArrayList;

public class OpenList {
    ArrayList<Square> oList;

    //constructor
    OpenList() {
        oList = new ArrayList<>();
    }

    void add(Square sq){
        oList.add(sq);
    }

    boolean checkoList(int x, int y){
        for(Square sq : oList){
            if(sq.getX() == x && sq.getY() == y) return true;
        }
        return false;
    }


    public Square findSmallestfn() {
        int min = oList.get(0).getFn();
        int minIndex = 0;
        int ctr = 0;
        for(Square sq : oList){
            if(sq.getFn() < min){
                minIndex = ctr;
                min = sq.getFn();
            }
            ctr++;
        }
        Square sq = oList.get(minIndex);
        oList.remove(minIndex);
        return sq;
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
}
