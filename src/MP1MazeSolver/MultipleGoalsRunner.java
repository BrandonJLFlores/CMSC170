package MP1MazeSolver;

import java.util.ArrayList;

public class MultipleGoalsRunner {

    private Maze m;
    private MazeRunner mRunner;
    private Square start;//store for printing purposes
    private ArrayList<Goal> end;
    private ArrayList<Goal> path;

    public static void main(String[] args) {
        final String FILENAME = "exc maze/tiny_search.txt";//
        MultipleGoalsRunner mazeRunner = new MultipleGoalsRunner(FILENAME);
        mazeRunner.solve();


/*
        System.out.println("SMALL MAZE: ");
        MultipleGoalsRunner small_Maze = new MultipleGoalsRunner("exc maze/smallMaze.txt");
        small_Maze.solve();
        System.out.println();

        System.out.println("MEDIUM MAZE: ");
        MazeRunner medium_Maze = new MazeRunner("exc maze/mediumMaze.txt");
        medium_Maze.solve();
        medium_Maze.print();
        System.out.println();

        System.out.println("BIG MAZE: ");
        MazeRunner big_Maze = new MazeRunner("exc maze/bigMaze.txt");
        big_Maze.solve();
        big_Maze.print();
        System.out.println();

        System.out.println("OPEN MAZE: ");
        MazeRunner open_Maze = new MazeRunner("exc maze/openMaze.txt");
        open_Maze.solve();
        open_Maze.print();
        System.out.println();*/
    }

    private MultipleGoalsRunner(String name){
        m = new Maze(name);
        start = m.getStartNode();
        end = m.getEndGoals();
        path = new ArrayList<Goal>();
    }

    private void solve(){
        /*Solution: A*
            1. Calculate Path cost and Heuristic all possible goals
                A. Use previous mazeRunner to solve for every goal
                    a. clear map
                    b. put only goal desired to be calculated
                    c. store each
            3. Use A* to determine which next goal to start
            4. If goals not finished, go to 1
         */
        m.eraseGoals(end);//erases Goals
        while(end.size() > 0){
            getGoalsInfo(); //get Heuristic and Path Cost for each goal.
            int maxIndex = findMin();
            path.add(end.get(maxIndex));
            m.setStart(end.get(maxIndex));
            end.remove(maxIndex);
        }
        setMazePrint();
    }
    private void getGoalsInfo(){//gets Heuristic, Path Cost for each
        for(Goal goal: end){
            m.setEndNode(goal);
            MazeRunner mRunner = new MazeRunner(m);
            mRunner.solve();
            mRunner.print();
            goal.setPathCost(mRunner.getPathCost());
            goal.setHeuristic(mRunner.getHeuristic(1));
            mRunner.reset();//erases for printing purposes
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private void setMazePrint(){
        for(Goal goal: path){
            Integer index = path.indexOf(goal)+1;
            goal.setElement(String.valueOf(index.toString().charAt(0)));
            m.setEndNode(String.valueOf(index.toString().charAt(0)),goal);
        }
        m.setNode("P",start);
        System.out.println(m.toString());
    }

    private int findMin(){//linear search finding max goal
        int current = 0, min = end.get(0).getHeuristic() + end.get(0).getPathCost()
                , total = 0;
        for(int i = 1 ; i < end.size() ; i++){
            total = end.get(i).getHeuristic() + end.get(i).getPathCost();
            if(total < min){
                current = i;
                min = total;
            }
        }
        return current;
    }
}

