package MP1MazeSolver;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;

public class MultipleGoalsRunner {

    private Maze m;
    private Square start;//store for printing purposes
    private ArrayList<Goal> end;
    private ArrayList<Goal> path;
    private static String[] filenames = new String[]{"trickySearch","tinyMaze","smallSearch" +
            "smallMaze", "openMaze", "mediumSearch", "bigSearch", "bigMaze"};
    public static void main(String[] args) {
        System.out.println("TRICKY SEARCH:");
        //change filename here
        final String FILENAME = "exc maze/trickySearch.lay.txt";
        MultipleGoalsRunner mazeRunner = new MultipleGoalsRunner(FILENAME);
        int heuristic = 2;// 1 for Manhattan and 2 for Straight-line
        mazeRunner.solve(heuristic);
        //mazeRunner.solve(heuristic+1);
    }

    private MultipleGoalsRunner(String name){
        m = new Maze(name);
        start = m.getStartNode();
        end = m.getEndGoals();
        path = new ArrayList<Goal>();
    }

    private void solve(int h){
        /*Solution: A*
            1. Calculate Path cost and Heuristic all possible goals
                A. Use previous mazeRunner to solve for every goal
                    a. clear map
                    b. put only goal desired to be calculated
                    c. store each
            2. Use A* to determine which next goal to start
            3. If goals not finished, go to 1
         */

        m.eraseGoals(end);//erases Goals
        while(end.size() > 0){
            getGoalsInfo(h); //get Heuristic and Path Cost for each goal.
            int maxIndex = findMin();
            Goal tempGoal = end.get(maxIndex);
            path.add(tempGoal);
            m.setStart(tempGoal);
            end.remove(maxIndex);
        }
        setMazePrint();
    }
    private void getGoalsInfo(int h){//gets Heuristic, Path Cost for each
        for(Goal goal: end){
            m.setEndNode(goal);
            MazeRunner mRunner = new MazeRunner(m);
            mRunner.solve();
            //mRunner.print();//prints process
            goal.setPathCost(mRunner.getPathCost());
            goal.setHeuristic(mRunner.getHeuristic(h));
            goal.setNodesExpanded(mRunner.getNodesExpanded());
            goal.setFrontier(mRunner.getFrontier());
            goal.setPath(mRunner.getPath(),goal);
            mRunner.reset();//erases for printing purposes
        }
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private void setMazePrint(){
        for(Goal goal: path){
            Integer index = path.indexOf(goal)+1;
            goal.setElement(index.toString());
            m.setEndNode(index.toString(),goal);
        }
        m.setNode("P",start);
        System.out.println(m.toString(path));
        printFrontier();
        printNodesExpanded();
        printPathCost();
        printPath();
    }
    private void printPathCost(){
        int pathCost = 0;
        for(Goal goal: path){
            pathCost += goal.getPathCost();
        }
        System.out.println("Path Cost: "+pathCost);
    }

    private void printNodesExpanded(){
        int nodesExpanded = 0;
        for(Goal goal: path){
            nodesExpanded += goal.getNodesExpanded();
        }
        System.out.println("Nodes Expanded: "+nodesExpanded);
    }
    private void printFrontier(){
        int frontier = 0;
        for(Goal goal: path){
            frontier += goal.getFrontier();
        }
        System.out.println("Frontiers: "+frontier);
    }

    private void printPath(){
        System.out.println("Path: ");
        for(Goal goal: path){
            for(Square sq: goal.getPath()){
                System.out.print(" ["+sq.getX()+","+sq.getY()+"] ");
            }
            System.out.println();
        }
    }

    private int findMin(){//linear search finding max goal
        int current = 0, minheu = end.get(0).getHeuristic(),minpc = end.get(0).getPathCost()
                , heu = 0, pc = 0;
        for(int i = 1 ; i < end.size() ; i++){
            heu = end.get(i).getHeuristic();
            pc = end.get(i).getPathCost();
            if((heu + pc < minpc + minheu)){
                current = i;
                minheu = heu;
                minpc = pc;
            }else if(minpc + minheu == heu + pc){
                if(pc < minpc){
                    current = i;
                    minheu = heu;
                    minpc = pc;
                }
            }
        }
        return current;
    }
}

