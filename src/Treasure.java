import javafx.scene.image.ImageView;

public class Treasure extends EntityObservable {
	
	public Treasure(ImageView iv) {
		
		super(iv);
		rate = 500_000_000;
		
	}
	
	public void hide() {
		
		imageView.setVisible(false);
		
	}


}
