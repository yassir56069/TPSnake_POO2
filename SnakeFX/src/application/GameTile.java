package application;


import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;



public class GameTile {
	public static GridPane gridPane;
	private static Rectangle[][] rectangles;
	
	public static final int ROWCOL = 40; // 10 rows * 10 columns.
	
	/**
	 * Constructs game tile with pattern specified
	 * <p>
	 * | G - Ground 
	 * | S - Snake Body
	 * | H - Snake Head
	 * | F - Fruit
	 * <p>
	 *
	 * @param  group  		:group containing rect nodes for the scene
	 * @param  screenSize   :the size of the screen as an integer. we only need one value as the screen is always a square 
	 */
	public static float generateTileMap(Group group, int screenSize) {   
		float SQUARE = screenSize/ROWCOL;
		gridPane = new GridPane();
		rectangles = new Rectangle[ROWCOL][ROWCOL];
		
		for (int x = 0; x < ROWCOL; x++) {
			for (int y = 0; y < ROWCOL; y++) {
				
				createTileMap(gridPane, SQUARE, 'G', x, y); 
	              
				group.getChildren().addAll(rectangles[y][x]); //adding rectangle to the //group   
			}
		}

		return SQUARE;

	}
	
	private static void createTileMap(GridPane gridPane, float SQUARE, char v, int x, int y) {
		StackPane tile = createTile(v);
		gridPane.add(tile, y, x);
			
		Rectangle rect=new Rectangle(); //instantiating Rectangle   
		rectangles[y][x] = rect;
		rect.setX(SQUARE * x);
		rect.setY(SQUARE * y);
		
		rect.setWidth(SQUARE);
		rect.setHeight(SQUARE);
		
		
		switch(getTileLabel(x,y)) {
			
			case "S":
				rect.setFill(Color.RED);
				break;
			case "H":
				rect.setFill(Color.BLACK);
				break;
				
			default:
		        if ( (x + y)% 2 == 0) {
		              rect.setFill(Color.web("AAD751"));
		              } else {
		              rect.setFill(Color.web("A2D149"));
		              }

		}

			
//		return rect;
	}
	
	public static void updateTileMap(Rectangle[][] rects, int x, int y) {
		switch(getTileLabel(x,y)) {
		
		case "S":
			rects[y][x].setFill(Color.RED);
			break;
		case "H":
			rects[y][x].setFill(Color.BLACK);
			break;
		case "F":
			rects[y][x].setFill(Color.YELLOW);
			break;
		case "O":
			rects[y][x].setFill(Color.BLACK);
			break;
		default:
	        if ( (x + y)% 2 == 0) {
	              rects[y][x].setFill(Color.web("AAD751"));
	              } else {
	              rects[y][x].setFill(Color.web("A2D149"));
	              }

	}
	}
	
	
    public static Rectangle[][] getRectangles() {
		return rectangles;
	}

	public static void setRectangles(Rectangle[][] rectangles) {
		GameTile.rectangles = rectangles;
	}

	public static StackPane createTile(char v) {
        StackPane tile = new StackPane();
        Label label = new Label(String.valueOf(v));
        tile.getChildren().add(label);
        return tile;
    }

    
    public static String getTileLabel(int x, int y) {
    	boolean found = false;
        StackPane tile = (StackPane) gridPane.getChildren().get(x * ROWCOL + y);
        Label label = (Label) tile.getChildren().get(0); // Assuming the label is the first child
        String labelText = label.getText();
        
        return labelText;
    }
    
    
    private static boolean checkTile(char v, int x, int y) {
    	boolean found = false;
        StackPane tile = (StackPane) gridPane.getChildren().get(x * ROWCOL + y);
        Label label = (Label) tile.getChildren().get(0); // Assuming the label is the first child
        String labelText = label.getText();
        if (labelText == String.valueOf(v)) {
        	found = true;
        }
        return found;
    }
    
    public static void changeTile(char v, int x, int y) {
    	StackPane tile = (StackPane) gridPane.getChildren().get(x * ROWCOL + y);
    	
        Label label = (Label) tile.getChildren().get(0); // Assuming the label is the first child
        label.setText(String.valueOf(v));

    }
    
    public static void gameOverScreen(Group group) {
    	Text text = new Text();
    	text.setText("Hello how are you"); 
    	group.getChildren().addAll(text); 
    	
    	
    	for (int x = 0; x < ROWCOL; x++) {
    		for(int y = 0; y < ROWCOL; y++) {
    			changeTile('O', x ,y);
    		}
    	}
    }
    
    
    public static void iterateAndCheckLabels() {
        for (int i = 0; i < ROWCOL; i++) {
            for (int j = 0; j < ROWCOL; j++) {
                StackPane tile = (StackPane) gridPane.getChildren().get(i * ROWCOL + j);
                Label label = (Label) tile.getChildren().get(0); // Assuming the label is the first child
                String labelText = label.getText();
                System.out.print("| " + labelText + " |" );
            }
            System.out.println();
        }
    }
    
}
