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

//    boolean checkoList(int x, int y){
//        for(Square sq : oList){
//            if(sq.getX() == x && sq.getY() == y) return true;
//        }
//        return false;
//    }


//    public Square findSmallestfn() {
//        int min = oList.get(0).getFn();
//        int minIndex = 0;
//        int ctr = 0;
//        for(Square sq : oList){
//            if(sq.getFn() < min){
//                minIndex = ctr;
//                min = sq.getFn();
//            }
//            ctr++;
//        }
//        Square sq = oList.get(minIndex);
//        oList.remove(minIndex);
//        return sq;
//    }

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
