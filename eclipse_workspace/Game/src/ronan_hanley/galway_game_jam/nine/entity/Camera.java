package ronan_hanley.galway_game_jam.nine.entity;

import ronan_hanley.galway_game_jam.nine.Game;

public class Camera extends Entity {
	private MovingEntity followingEntity;
	
	public Camera(MovingEntity followingEntity) {
		this(0, 0, followingEntity);
	}
	
	public Camera(int x, int y, MovingEntity followingEntity) {
		super(x, y);
		this.followingEntity = followingEntity;
	}
	
	public void tick() {
		if (followingEntity != null) {
			setX(followingEntity.getXExact() - Game.screenWidth / 2);
			setY(followingEntity.getYExact() - Game.screenHeight / 2);
		}
	}

}
