package MP1MazeSolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Maze {
    private ArrayList<String> mazeLine = new ArrayList<>();
    private Square[][] maze;
    private int rows;
    private int cols;
    private Square startNode, endNode;
    private int pathCost;

    Maze(String file){
        loadFile(file);
        rows = mazeLine.size();
        cols = mazeLine.get(0).length();
        maze = new Square[rows][cols];
        addToMaze();
        pathCost = 0;
    }


    private void addToMaze() {
        int i = 0;
        for(String s : mazeLine){
            setRow(s,i);
            i++;
        }
    }

    private void setRow(String s, int i) { //row nang j
        for(int j = 0; j < s.length(); j++){
            String c = String.valueOf(s.charAt(j));
            Square sq = new Square(c, i,j);
            if(c.equals("P")) startNode = sq;
            if(c.equals(".")){
                sq.setElement("G");
                endNode = sq;
            }
            maze[i][j] = sq;
        }
    }

    private void loadFile(String FILENAME) {
        mazeLine = new ArrayList<>();
        BufferedReader br = null;
        FileReader fr = null;

        try {
            try {
                br = new BufferedReader(new FileReader(FILENAME));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String sCurrentLine;
            try {
                for (int i = 0; (sCurrentLine = br.readLine()) != null; i++) {
                    mazeLine.add(i, sCurrentLine);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void incPathCost(){
        pathCost++;
    }

    public Square[][] getMaze() {
        return maze;
    }

    public Square getStartNode() {
        return startNode;
    }

    public Square getEndNode() {
        return endNode;
    }

    public ArrayList<Square> surrounding(Square square) {
        int x = square.getX(), y = square.getY();
        ArrayList<Square> neighbours = new ArrayList<>();
        neighbours.add(maze[x-1][y]); //up
        neighbours.add(maze[x+1][y]); //down
        neighbours.add(maze[x][y-1]); //left
        neighbours.add(maze[x][y+1]); //right

        return neighbours;
    }

    public int getPathCost() {
        return pathCost;
    }

    //no E and F version
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                stringBuilder.append(maze[i][j].getElement());
                if (maze[i][j].getElement().matches("-?\\d+(\\.\\d+)?")){
                    if (Integer.valueOf(maze[i][j].getElement()) < 10){
                        stringBuilder.append("   ");
                    }
                    else if (Integer.valueOf(maze[i][j].getElement()) >= 10 && Integer.valueOf(maze[i][j].getElement()) < 100){
                        stringBuilder.append("  ");
                    }
                    else{
                        stringBuilder.append(" ");
                    }
                }
                else {
                    stringBuilder.append("   ");
                }
            }
            stringBuilder.append('\n');
        }
        return  stringBuilder.toString();
    }




    //Multiple Goals Functions

    public ArrayList<Goal> getEndGoals() { //stores end goals
        int i = 0;
        ArrayList<Goal> end = new ArrayList<Goal>();
        for(String s : mazeLine){
            for(int j = 0; j < s.length(); j++){
                String c = String.valueOf(s.charAt(j));
                Goal sq = new Goal(c, i,j);
                if(c.equals(".")){
                    end.add(sq);
                }
            }
            i++;
        }
        return end;
    }

    public void eraseGoals(ArrayList<Goal> goalsArr){
        for(Goal goal: goalsArr){
            eraseGoals(goal);
        }
    }

    public void eraseGoals(Goal goal){
        maze[goal.getX()][goal.getY()].setElement(String.valueOf(new String("  ").charAt(0)));
    }

    public void setEndNode(String c, Square goal){
        Square sq = new Square(c,goal.getX(),goal.getY());
        maze[goal.getX()][goal.getY()] = sq;
        endNode = sq;
    }

    public void setNode(String c, Square goal){
        Square sq = new Square(c,goal.getX(),goal.getY());
        maze[goal.getX()][goal.getY()] = sq;
    }

    public void setEndNode(Square goal){
        setEndNode("G",goal);
    }

    public void setStart(Square start){
        Square sq = new Square(" ",startNode.getX(),startNode.getY());
        maze[startNode.getX()][startNode.getY()] = sq;

        sq = new Square("P",start.getX(),start.getY());
        maze[start.getX()][start.getY()] = sq;
        startNode= sq;
    }
    void resetPathCost(){
        pathCost = 0;
    }
}
