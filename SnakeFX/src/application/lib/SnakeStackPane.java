package application.lib;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import application.GameTile;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;

public class SnakeStackPane {
    private static StackPane snakePane;
    private static Stack<Point2D> snake;
    
    
    public static boolean alive = true;
    
    private static final String snakeBody = "S";

    
    private static int headX;
    private static int headY;
    
    private static int tailX;
    private static int tailY;
    
    private static int fruitX;
    private static int fruitY;
    
    
    
	public static void GenerateSnake(int length, int start_x, int start_y) {
        snakePane = new StackPane();
        snake = new Stack<>();
        
		tailX = start_x;
		tailY = start_y;
		
		
		snake.push(new Point2D(tailX, tailY));
		
		GameTile.changeTile('S', start_x, start_y);
		
		for (int i = 1; i < length; i++) {
			
			GameTile.changeTile('S', start_x, start_y + i);
			snake.push(new Point2D(start_x, start_y + i));
			
			
		}
		GameTile.changeTile('S', start_x, start_y + length);
		
		headX = start_x;
		headY = start_y + length;
		
		snake.push(new Point2D(headX, headY));
		
		
		GameTile.iterateAndCheckLabels();
	}
	
	
	private static void addSnakebitCond(int direction)
	{
        switch(direction) {
    	case(0): 
    		addSnakeBit(headX - 1, headY);
    		break;
    	case(1):
    		addSnakeBit(headX + 1, headY);
    		break;
    	case(2):
    		addSnakeBit(headX, headY + 1);
    		break;
    	case(3):
    		addSnakeBit(headX, headY - 1);
    		break;
        }
	}
	
	
	private static void addSnakeBit(int start_x, int start_y)
	{
		System.out.println("start values: " + start_x + "," + start_y);
		System.out.println("start label: " + GameTile.getTileLabel(start_x, start_y) );
		
    	if (GameTile.getTileLabel(start_x, start_y).equals(snakeBody)) {
    		System.out.println("GAMEOVER");
    		alive = false;
    	}
    	
		GameTile.changeTile('S', start_x, start_y);
		headX = start_x;
		headY = start_y;
		
		snake.push(new Point2D(headX, headY));
	}
	
	
	public static void updateDisplay() {
		for (int x = 0; x < GameTile.ROWCOL;x++) {
			for (int y = 0; y < GameTile.ROWCOL; y++) {
				GameTile.updateTileMap(GameTile.getRectangles(), x, y);
			}
		}
	}
	
	public static void addFruit() {
		Random rand = new Random(); 
		int upperbound = GameTile.ROWCOL;
		fruitX = rand.nextInt(upperbound); 
		fruitY = rand.nextInt(upperbound); 
		
		GameTile.changeTile('F', fruitX, fruitY);
	}
	
    public static void moveSnake(Group group, int direction) {
        try {
    	if (alive)
    	{
        	updateDisplay();
        	
        	Point2D head = snake.peek();
        	Iterator<Point2D> value = snake.iterator(); 
        	
        	System.out.println(headX + "," + headY);

        	
            // Remove the first element (tail)
        	
        	Point2D tail = snake.remove(0);
        	
            GameTile.changeTile('G', (int)tail.getX(), (int)tail.getY());

            
        	// Add on fruit
        	if ( (int) head.getX() == fruitX && (int) head.getY() == fruitY) {
        		
        		System.out.println("workd");
        		addSnakebitCond(direction);
        		addFruit();
        	}
            
            // Add a new element at the end (head)
        	addSnakebitCond(direction);
    	}
    	else
    	{
    		GameTile.gameOverScreen(group);
    	}
        }
	    catch (ArrayIndexOutOfBoundsException | EmptyStackException e )
	    {
	    	GameTile.gameOverScreen(group);
	    }
	        


    }
}
