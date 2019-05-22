import javafx.scene.image.ImageView;

public class Player {

	public double rate;
	public double lastUpdate = 0.0;
	int[] position = {0, 0};
	int[] inputDirection = {0, 0};
	ImageView imageView;
	OceanMap m;
	
	public Player(ImageView iv) {
		
		m = OceanMap.getInstance();
		imageView = iv;
		rate = 200_000_000;
		
	}
	
	public void setPosition(int[] newPos) {
		
		newPos = newPos.clone();
		
		position = newPos;
		imageView.setX(newPos[0] * m.cellSize);// * scalingFactor);
		imageView.setY(newPos[1] * m.cellSize);// * scalingFactor);
		
	}

	public int[] getPosition() {
		
		return position.clone();
		
	}
	
	public int getDistance(int[] target) {
		
		int x = Math.abs(target[0] - position[0]);
		int y = Math.abs(target[1] - position[1]);
		
		return x + y;
		
	}

	public void goWest() {
		
		inputDirection[0] = -1;
		inputDirection[1] = 0;
		
	}

	public void goEast() {
		
		inputDirection[0] = 1;
		inputDirection[1] = 0;
			
	}

	public void goNorth() {
		
		inputDirection[0] = 0;
		inputDirection[1] = -1;
		
	}
	
	public void goSouth() {
		
		inputDirection[0] = 0;
		inputDirection[1] = 1;
		
	}
	
	public void stop() {
		
		inputDirection[0] = 0;
		inputDirection[1] = 0;
		
	}

	public void moveToInput() {
		
		int[] newPos = {position[0] + inputDirection[0], position[1] + inputDirection[1]};
		
		setPosition(newPos);
		
	}
	
	
}
