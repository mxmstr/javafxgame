import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.scene.image.ImageView;

public class EntityObserver implements Observer {
	
	double rate;
	double lastUpdate = 0.0;
	boolean canCatchPlayer;
	boolean caughtPlayer = false;
	int[] position = {0, 0};
	int viewDistance;
	boolean useDirection;
	int[] viewDirection = {0, 1};
	int[][] route;
	MoveMode defaultMove;
	MoveMode currentMove;
	ImageView imageView;
	ImageView arrow;
	
	public EntityObserver(ImageView shipImg, ImageView arrowImg) {
		
		imageView = shipImg;
		arrow = arrowImg;
		
		setDirection(viewDirection);
		
	}
	
	public double getRate() {
		
		return rate;
		
	}
	
	public boolean hasCaughtPlayer() {
		
		return caughtPlayer;
		
	}
	
	public void setPosition(int[] newPos) {
		
		newPos = newPos.clone();
		
		position = newPos;
		imageView.setX(newPos[0] * OceanMap.getCellSize());
		imageView.setY(newPos[1] * OceanMap.getCellSize());
		
		if (arrow != null) {
			arrow.setX((newPos[0] + viewDirection[0]) * OceanMap.getCellSize());
			arrow.setY((newPos[1] + viewDirection[1]) * OceanMap.getCellSize());
		}
		
	}

	public int[] getPosition() {
		
		return position.clone();
		
	}
	
	public int getDistance(int[] target) {
		
		int x = Math.abs(target[0] - position[0]);
		int y = Math.abs(target[1] - position[1]);
		
		return x + y;
		
	}
	
	public void setDirection(int[] dir) {
		
		viewDirection = dir;
		
		if (arrow != null) {
			arrow.setX((position[0] + dir[0]) * OceanMap.getCellSize());
			arrow.setY((position[1] + dir[1]) * OceanMap.getCellSize());
			
			if (dir[0] != 0)
				arrow.setRotate(-90.0 * dir[0]);
			else if (dir[1] < 0)
				arrow.setRotate(180.0);
			else if (dir[1] > 0)
				arrow.setRotate(0.0);
		}
		
	}
	
	private boolean isVisible(int[] goal) {
		
		int[] vector = {goal[0] - position[0], goal[1] - position[1]};
		int[] goalDir = {0, 0};
		
		if (vector[0] < 0)
			goalDir[0] = -1;
		else if (vector[0] > 0)
			goalDir[0] = 1;
		if (vector[1] < 0)
			goalDir[1] = -1;
		else if (vector[1] > 0)
			goalDir[1] = 1;
		
		if (viewDistance >= getDistance(goal)) {
			if (!useDirection ||
				goalDir[0] == viewDirection[0] &&
				goalDir[1] == viewDirection[1])
					return true;
		}
		
		return false;
		
	}
	
	public void moveToGoal(int[] goal) {
		
		Random rand = new Random();
		int[] newPos = position.clone();
		int[] vector = {goal[0] - newPos[0], goal[1] - newPos[1]};
		int[] direction = {0, 0};
		
		int axis = 0;
		
		if (vector[0] != 0 && vector[1] != 0) {
			axis = rand.nextInt(2);
		}
		else if (vector[1] != 0)
			axis = 1;
		
		if (vector[axis] < 0) {
			newPos[axis] -= 1;
			direction[axis] = -1;
		}
		else if (vector[axis] > 0) {
			newPos[axis] += 1;
			direction[axis] = 1;
		}
		
		if (OceanMap.getInstance().cellOpen(newPos)) {
			setDirection(direction);
			setPosition(newPos);
		}
		else {
			newPos[0] = rand.nextInt(3) - 1;
			newPos[1] = rand.nextInt(3) - 1;
			moveToGoal(newPos);
		}
		
	}
	
	public void runMoveMode() {
		
		currentMove.move(this);
		
	}
	
	public void update(Observable ship, Object arg) {
		
		int[] goal = ((EntityObservable)ship).getPosition();
		
		if (canCatchPlayer && goal[0] == position[0] && goal[1] == position[1])
			caughtPlayer = true;
		
		if (isVisible(goal) && currentMove == defaultMove)
			currentMove = new Chase(((EntityObservable)ship));
		
		if (getDistance(goal) > viewDistance && currentMove.getName() == "Chase")
			currentMove = defaultMove;
		
	}
	
	public void update(long currentNanoTime) {
		
		double t = (currentNanoTime - lastUpdate);
		
		if (t >= rate) {
			runMoveMode();
            lastUpdate = currentNanoTime;
        }
		
	}
	
}
