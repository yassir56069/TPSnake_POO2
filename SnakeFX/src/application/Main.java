package application;
	
import java.util.EmptyStackException;

import application.lib.SnakeStackPane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;




public class Main extends Application {
	float SQUARE;
	int direction = 0;
	@Override
	
	public void start(Stage primaryStage) throws Exception {    
		primaryStage.setTitle("Rectangle Example");  

		Group group = new Group(); //creating Group   

		// Stage menu = new Stage();
//		GameStage rootStage = new GameStage("SnakeFx", false, 800, 800, "file:src/Assets/logo.png");
		SQUARE = GameTile.generateTileMap(group,800);
		
		SnakeStackPane.GenerateSnake(5, 10 ,5);

		for (int x = 0; x < GameTile.ROWCOL;x++) {
			for (int y = 0; y < GameTile.ROWCOL; y++) {
				GameTile.updateTileMap(GameTile.getRectangles(), x, y);
			}
		}
		GameTile.iterateAndCheckLabels();
		
//		SnakeStackPane.moveSnake(1);
//		SnakeStackPane.moveSnake(2);
		SnakeStackPane.addFruit();

	    Scene scene = new Scene(group,800,800,Color.CHOCOLATE);  
	    primaryStage.setScene(scene);  
	    primaryStage.show();  
	    
        // Set up keyboard input handling
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        direction = 3;		// Up
                        break;
                    case A:
                    	direction = 0; 		// Left
                        break;
                    case S:
                        direction = 2; 		// Down
                        break;
                    case D:
                        direction = 1; 	    // Right
                        break;
                }
            }
        });
	    
        Duration duration = Duration.millis(100); // Move every 200 milliseconds

        KeyFrame keyFrame = new KeyFrame(duration, event -> SnakeStackPane.moveSnake(group, direction));
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
     


		
	}
	public static void main(String[] args) {
		Application.launch();
	}

}
