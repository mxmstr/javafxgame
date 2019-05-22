import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class OceanGame extends Application {
	
	double speedMult = 1.0;
	Scene scene;
	OceanMap map;
	Stage stage;
	Player playerShip;
	

	@Override
	public void start(Stage oceanStage) throws Exception {
		
		stage = oceanStage;
		
		setUpGame();
		
	}
	
	private void setUpGame() {
		
		// Clears the map and repopulates it
		OceanMap.resetMap();
		map = OceanMap.getInstance();
		scene = map.scene;
		
		
		// Create window
		stage.setTitle("Ocean Game");
		stage.setScene(scene);
		stage.show();
		
		
		// Setup gameplay
		addPlayer();
		setGameUpdate();
		setKeys();
		
	}

	private void addPlayer() {
		
		playerShip = new Player(loadImage("ship.png", map.cellSize, map.cellSize));
		
	}
	
	private ImageView loadImage(String path, int sizeX, int sizeY) {
		
		Image shipImage = new Image(path, sizeX, sizeY, true, true);
		ImageView imageView = new ImageView(shipImage);
		imageView.setX(0 * map.cellSize);
		imageView.setY(0 * map.cellSize);
		map.addChild(imageView);
		
		return imageView;
		
	}

	
	
	public void setGameUpdate() {
		
		// Use AnimationTimer to set a persistent update rate for the player
		new AnimationTimer()
	    {
	    	
	        public void handle(long currentNanoTime)
	        {
	        	double t;
	        	
				
				t = (currentNanoTime - playerShip.lastUpdate);
				
				if (t >= playerShip.rate) {
					// Process Player input
					playerShip.moveToInput();
					playerShip.lastUpdate = currentNanoTime;
                }
					
				
	        }
	    }.start();
		
	}
	
	private void setKeys(){
		
		// Bind the arrow keys to their respective Player movement direction
		// Hold to move, release to stop
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			
			@Override
			public void handle(KeyEvent ke) {
				switch(ke.getCode()){
					case RIGHT:
						playerShip.goEast();
						break;
					case LEFT:
						playerShip.goWest();
						break;
					case UP:
						playerShip.goNorth();
						break;
					case DOWN:
						playerShip.goSouth();
						break;
					default:
						break;
				}
			}
		
		});
		
		// Stop the player when any movement key is released
		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
			
			@Override
			public void handle(KeyEvent ke) {
				playerShip.stop();
			}
			
		});
	}
	
	public static void main(String[] args) {
		
		launch(args);

	}

}
