import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class OceanMap {

	static OceanMap instance;
	Pane p;
	Scene scene;
	final static int dimensions = 15;
	final static int cellSize = 50;
	private int[][] grid;
	private ArrayList<Line> lines = new ArrayList<Line>();
	
	private OceanMap() {
		
		p = new AnchorPane();
		scene = new Scene(p, cellSize*dimensions, cellSize*dimensions);
		
		grid = new int[dimensions][dimensions];
		for (int x = 0; x < dimensions; x++) {
		    for (int y = 0; y < dimensions; y++) {
		       grid[x][y] = 0;
		    }
		}
		
		p.getChildren().clear();
		
		createMap();
		 
	}
	
	public static OceanMap getInstance() {
		
		if(instance == null)
			instance = new OceanMap();
		
		return instance;
	      
	}
	
	public static int getCellSize() {
		
		return cellSize;
	      
	}
	
	public static int getDimensions() {
		
		return dimensions;
	      
	}
	
	public static void resetMap() {
		
		instance = new OceanMap();
		
	}
	
	public void addChild(ImageView imageView) {
		
		p.getChildren().add(imageView);
	
	}
	
 	private void setLine(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
		
    	Line line = new Line();
    	line.setStartX(topLeftX);
    	line.setStartY(topLeftY);
    	line.setEndX(bottomRightX);
    	line.setEndY(bottomRightY);
    	lines.add(line);
    	
    }
	
	private void createMap() {
		
		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {
				setLine(x, y, x + 1, y);
				setLine(x, y + 1, x + 1, y + 1);
				setLine(x, y, x, y + 1);
				setLine(x + 1, y, x + 1, y + 1);
			}
		}
		
		
		Rectangle rect = new Rectangle(0, 0, cellSize * dimensions, cellSize * dimensions);
		rect.setStroke(Color.BLACK);
		rect.setFill(Color.PALETURQUOISE);
		p.getChildren().add(rect);
		
    	for(Line line: lines){
    		line.setStartX(line.getStartX() * cellSize);
    		line.setStartY(line.getStartY() * cellSize);
    		line.setEndX(line.getEndX() * cellSize);
    		line.setEndY(line.getEndY() * cellSize);
    		p.getChildren().add(line);
    	}
		
	}

	public boolean cellOpen(int[] pos) {
		
		if (pos[0] < 0 || pos[0] > dimensions - 1 || pos[1] < 0 || pos[1] > dimensions - 1)
			return false;
		
		if (grid[pos[0]][pos[1]] > 0)
			return false;
		
		return true;
		
	}
	
	
}
