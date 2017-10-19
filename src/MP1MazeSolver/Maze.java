package MP1MazeSolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
    private ArrayList<String> mazeLine = new ArrayList<>();
    private Square[][] maze;
    private int rows;
    private int cols;
    private Square startNode, endNode;

    Maze(String file){
        loadFile(file);
        rows = mazeLine.size();
        cols = mazeLine.get(0).length();
        maze = new Square[rows][cols];
        addToMaze();
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
            char c = s.charAt(j);
            Square sq = new Square(c, i,j);
            if(c == 'P') startNode = sq;
            if(c == '.') endNode = sq;
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
        neighbours.add(maze[x][y-1]);
        neighbours.add(maze[x-1][y]);
        neighbours.add(maze[x][y+1]);
        neighbours.add(maze[x+1][y]);

        return neighbours;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                stringBuilder.append(maze[i][j].getElement());
            }
            stringBuilder.append('\n');
        }
        return  stringBuilder.toString();
    }
}