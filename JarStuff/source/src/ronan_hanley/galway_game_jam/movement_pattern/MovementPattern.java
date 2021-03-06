package ronan_hanley.galway_game_jam.movement_pattern;

import ronan_hanley.galway_game_jam.nine.entity.Human;

public abstract class MovementPattern {
	private Human human;
	
	public abstract void tick();
	
	public void setHuman(Human human) {
		this.human = human;
	}
	
	public Human getHuman() {
		return human;
	}
	
}
