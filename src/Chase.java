import java.util.Random;

public class Chase implements MoveMode {

	public EntityObservable target;
	public String name;
	
	public Chase(EntityObservable target) {
		
		this.target = target;
		name = "Chase";
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public void move(EntityObserver entity) {
		
		entity.moveToGoal(target.getPosition());
		
	}
	
}
