package application.lib;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javafx.stage.Stage;


public class GameStage extends Stage {
	static Group root = new Group();
	static Scene rootScene = new Scene(root, Color.DARKGREEN);
	static int WIDTH; static int HEIGHT;
	

	/**
	 * Constructs root stage for game background and loading
	 * <p>
	 * This class extends the Stage javaFX class implementing it for the purposes of the game.
	 * <p>
	 *
	 * @param  title  		:Title of the game
	 * @param  fullscreen	:make window fullscreen (ESC to exit)
	 * @param  width  		:Width of game screen
	 * @param  height 		:Height of game screen
	 * @param  iconPath		:Path of icon for image
	 */
	public GameStage(String title, boolean fullscreen, int width, int height, String iconPath) {
		
		// Window Icon
		Image icon = new Image(iconPath);
		this.getIcons().add(icon);
		
		// Window Title
		this.setTitle(title);
				
		
		//#region -- Screen Properties
		GameStage.WIDTH = width; 
		GameStage.HEIGHT = height;
		
		// Screen Dimensions
		this.setWidth(GameStage.WIDTH); this.setHeight(GameStage.HEIGHT);
		this.setResizable(false);		
		this.setFullScreen(fullscreen);
		
		//#endregion

		
		

	}


	public static int getWIDTH() {
		return WIDTH;
	}


	public static void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}


	public static int getHEIGHT() {
		return HEIGHT;
	}


	public static void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}
	
	
}