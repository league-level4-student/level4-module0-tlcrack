package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		int i = randGen.nextInt(w);
		int j = randGen.nextInt(h);

		maze.cells[0][randGen.nextInt(maze.getWidth())].setWestWall(false);
		maze.cells[w-1][randGen.nextInt(maze.getWidth())].setEastWall(false);
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cells[i][j]);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(unvisitedNeighbors.size()>0) {
			//C1. select one at random.
			int randomNeighbor = randGen.nextInt(unvisitedNeighbors.size());
			//C2. push it to the stack
			uncheckedCells.push(unvisitedNeighbors.get(randomNeighbor));
			//C3. remove the wall between the two cells
			removeWalls(currentCell, unvisitedNeighbors.get(randomNeighbor));
			//C4. make the new cell the current cell and mark it as visited
			unvisitedNeighbors.get(randomNeighbor).setBeenVisited(true);
			currentCell=unvisitedNeighbors.get(randomNeighbor);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		if(unvisitedNeighbors.size()==0) {
			//D1. if the stack is not empty
			if(!uncheckedCells.isEmpty()) {
				// D1a. pop a cell from the stack
		currentCell=uncheckedCells.pop();
				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX()-1==c2.getX()&&c1.getY()==c2.getY()) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
		else if(c1.getX()==c2.getX()-1&&c1.getY()==c2.getY()) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}
		else if(c1.getY()-1==c2.getY()&&c1.getX()==c2.getX()) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
		else if(c1.getY()==c2.getY()-1&&c1.getX()==c2.getX()) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> neighborsUnvisited = new ArrayList<Cell>();
		if(c.getX()>0) {
			if(maze.cells[c.getX()-1][c.getY()].hasBeenVisited()==false) {
				neighborsUnvisited.add(maze.cells[c.getX()-1][c.getY()]);
			}
		}
		if(c.getX()<maze.getWidth()-1) {
			if(maze.cells[c.getX()+1][c.getY()].hasBeenVisited()==false) {
				neighborsUnvisited.add(maze.cells[c.getX()+1][c.getY()]);
			}
		}
		if(c.getY()>0) {
			if(maze.cells[c.getX()][c.getY()-1].hasBeenVisited()==false) {
				neighborsUnvisited.add(maze.cells[c.getX()][c.getY()-1]);
			}
		}
		if(c.getY()<maze.getHeight()-1) {
			if(maze.cells[c.getX()][c.getY()+1].hasBeenVisited()==false) {
				neighborsUnvisited.add(maze.cells[c.getX()][c.getY()+1]);
			}
		}
		return neighborsUnvisited;
	}
}
