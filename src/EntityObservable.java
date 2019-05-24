import java.util.Observable;

import javafx.scene.image.ImageView;

public class EntityObservable extends Observable {

	double rate;
	double lastUpdate = 0.0;
	int[] position = {0, 0};
	ImageView imageView;
	
	public EntityObservable(ImageView iv) {
		
		imageView = iv;
		
	}
	
	public double getRate() {
		
		return rate;
		
	}
	
	public void setPosition(int[] newPos) {
		
		newPos = newPos.clone();
		
		position = newPos;
		imageView.setX(newPos[0] * OceanMap.getCellSize());
		imageView.setY(newPos[1] * OceanMap.getCellSize());
		
		setChanged();
		notifyObservers();
		
	}

	public int[] getPosition() {
		
		return position.clone();
		
	}
	
	public int getDistance(int[] target) {
		
		int x = Math.abs(target[0] - position[0]);
		int y = Math.abs(target[1] - position[1]);
		
		return x + y;
		
	}
	
	public void update(long currentNanoTime) {
		
		double t = (currentNanoTime - lastUpdate);
		
		if (t >= rate) {
			lastUpdate = currentNanoTime;
        }
	}
	
}
