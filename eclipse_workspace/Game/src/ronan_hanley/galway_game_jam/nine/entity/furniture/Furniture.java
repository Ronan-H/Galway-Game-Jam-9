package ronan_hanley.galway_game_jam.nine.entity.furniture;

import org.newdawn.slick.Animation;

import ronan_hanley.galway_game_jam.nine.entity.MovingEntity;

public abstract class Furniture extends MovingEntity {

	public Furniture(int initialX, int initialY, Animation anim) {
		super(initialX, initialY, anim);
	}

}