import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.scene.image.ImageView;

public class Pirate extends EntityObserver {
	
	public Pirate(ImageView shipImg, ImageView arrowImg) {
		
		super(shipImg, arrowImg);
		canCatchPlayer = true;
		rate = 500_000_000;
		viewDistance = 8;
		useDirection = true;
		defaultMove = new Wander();
		currentMove = defaultMove;
		
	}
	
}
