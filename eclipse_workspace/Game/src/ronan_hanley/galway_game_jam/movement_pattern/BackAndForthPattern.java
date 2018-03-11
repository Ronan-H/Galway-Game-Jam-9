package ronan_hanley.galway_game_jam.movement_pattern;

import ronan_hanley.galway_game_jam.nine.entity.Human;

public abstract class BackAndForthPattern extends MovementPattern {
	protected int srcX, srcY;
	protected int destX, destY;
	protected boolean upPhase;
	
	public BackAndForthPattern(int destX, int destY) {
		this.destX = destX;
		this.destY = destY;
		
		upPhase = true;
	}
	
	@Override
	public abstract void tick();
	
	public void setHuman(Human human) {
		super.setHuman(human);
		
		srcX = human.getX();
		srcY = human.getY();
	}
	
}