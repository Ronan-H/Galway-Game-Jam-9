package ronan_hanley.galway_game_jam.nine.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import ronan_hanley.galway_game_jam.nine.Game;
import ronan_hanley.galway_game_jam.nine.level.Level;

public abstract class MovingEntity extends Entity {
	public static final double FULL_RAD_ROTATION = Math.PI * 2;
	
	private double maxSpeed;
	private double speed;
	private double angle;
	
	private int width, height;
	
	private Animation anim;
	
	public MovingEntity(int initialX, int initialY, double maxSpeed, Animation anim) {
		this(initialX, initialY, maxSpeed, anim.getImage(0).getWidth(), anim.getImage(0).getHeight());
		this.anim = anim;
	}
	
	public MovingEntity(int initialX, int initialY, double maxSpeed, int width, int height) {
		super(initialX, initialY);
		this.maxSpeed = maxSpeed;
		this.width = width;
		this.height = height;
	}
	
	public void tick(Level level) {
		double xDisp = Math.cos(angle) * speed; 
		double yDisp = Math.sin(angle) * speed;
		
		int backtrackX = (xDisp > 0 ? -1 : 1);
		int backtrackY = (yDisp > 0 ? -1 : 1);
		
		changeX(xDisp);
		
		while (level.collides(this)) {
			changeX(backtrackX);
		}
		
		changeY(yDisp);
		
		while (level.collides(this)) {
			changeY(backtrackY);
		}
	}
	
	public void render(Graphics g, Camera cam) {
		g.drawAnimation(getAnim(), getX() - cam.getX(),
						getY() - cam.getY());
		
		if (Game.DEBUG) {
			// hitbox
			g.setColor(Color.red);
			g.drawRect(getX() - cam.getX(), getY() - cam.getY(), getWidth(), getHeight());
		}
	}
	
	public int getCenterX() {
		return getX() + getHalfWidth();
	}
	
	public int getCenterY() {
		return getY() + getHalfHeight();
	}
	
	public double distanceTo(MovingEntity otherEntity) {
		return Math.sqrt(Math.pow(otherEntity.getCenterX() - getCenterX(), 2)
				+ Math.pow(otherEntity.getCenterY() - getCenterY(), 2));
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setSpeed(double newSpeed) {
		this.speed = newSpeed;
	}
	
	public void setAngle(double newAngle) {
		this.angle = newAngle % FULL_RAD_ROTATION;
		
		if (this.angle < 0) {
			this.angle = FULL_RAD_ROTATION + this.angle;
		}
	}
	
	public void changeAngle(double change) {
		setAngle(getAngle() + change);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getHalfWidth() {
		return getWidth() /2;
	}
	
	public int getHalfHeight() {
		return getHeight() /2;
	}
	
	public void setWidth(int newWidth) {
		width = newWidth;
	}
	
	public void setHeight(int newHeight) {
		height = newHeight;
	}
	
	public void goUp() {
		angle = FULL_RAD_ROTATION * 0.75d;
	}
	
	public void goDown() {
		angle = FULL_RAD_ROTATION * 0.25d;
	}
	
	public void goLeft() {
		angle = FULL_RAD_ROTATION * 0.5d;
	}
	
	public void goRight() {
		angle = 0;
	}
	
	public void goMaxSpeed() {
		speed = maxSpeed;
	}
	
	public void stop() {
		speed = 0;
	}
	
	public boolean isMoving() {
		return speed != 0;
	}
	
	public Animation getAnim() {
		return anim;
	}
	
}
