import javafx.scene.image.ImageView;

public class Player extends EntityObservable {

	int[] inputDirection = {0, 0};
	ImageView imageView;
	OceanMap m;
	
	public Player(ImageView iv) {
		
		super(iv);
		
		imageView = iv;
		rate = 400_000_000;
		
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
	
	@Override
	public void update(long currentNanoTime) {
		
		double t = (currentNanoTime - lastUpdate);
		
		if (t >= rate) {
			// Process Player input
			moveToInput();
			lastUpdate = currentNanoTime;
        }
	}
	
	
}
