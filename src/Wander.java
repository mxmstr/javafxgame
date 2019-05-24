import java.util.Random;

public class Wander implements MoveMode {
	
	public int[] goal;
	public String name;
	
	public Wander() {
		
		name = "Wander";
		goal = new int[2];
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public void move(EntityObserver entity) {
		
		int[] pos = entity.getPosition();
		
		Random rand = new Random();
		goal[0] = pos[0] + rand.nextInt(3) - 1;
		goal[1] = pos[1] + rand.nextInt(3) - 1;
		
		entity.moveToGoal(goal);
		
	}
	
}
