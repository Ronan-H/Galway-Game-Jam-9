package ronan_hanley.galway_game_jam.nine.level;

import static ronan_hanley.galway_game_jam.nine.entity.MovingEntity.FULL_RAD_ROTATION;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ronan_hanley.galway_game_jam.nine.Game;
import ronan_hanley.galway_game_jam.nine.entity.Camera;
import ronan_hanley.galway_game_jam.nine.entity.Human;
import ronan_hanley.galway_game_jam.nine.entity.MovingEntity;
import ronan_hanley.galway_game_jam.nine.entity.furniture.Furniture;

public class Level {
	private Image wallsImg;
	private Image floorImg;
	private int width;
	private int height;
	private boolean[] pixels;
	
	private int floorDrawTimesX;
	private int floorDrawTimesY;
	
	public Level(int levelNum) {
		try {
			wallsImg = new Image(String.format("res/images/level%d.png", levelNum));
			floorImg = new Image("res/images/carpet.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		loadPixels();
		
		width = wallsImg.getWidth();
		height = wallsImg.getHeight();
		
		floorDrawTimesX = (Game.screenWidth / floorImg.getWidth()) + 3;
		floorDrawTimesY = (Game.screenHeight / floorImg.getHeight()) + 3;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void loadPixels() {
		pixels = new boolean[wallsImg.getWidth() * wallsImg.getHeight()];
		
		int pixelCounter = 0;
		
		for (int y = 0; y < wallsImg.getHeight(); ++y) {
			for (int x = 0; x < wallsImg.getWidth(); x++) {
				pixels[pixelCounter++] = wallsImg.getColor(x, y).equals(Color.black);
			}
		}
	}
	
	public boolean collides(MovingEntity e) {
		if (!Game.DEBUG) {
			int fromX = e.getX();
			int fromY = e.getY();
			int toX = e.getX() + e.getWidth();
			int toY = e.getY() + e.getHeight();
			
			for (int y = fromY; y < toY; ++y) {
				for (int x = fromX; x < toX; ++x) {
					int pixelIndex = (y * width) + x;
					if (pixels[pixelIndex]) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean inLineOfSight(Human human, Furniture f) {
		final double angIncrement = human.getFov() / 50d;
		final int rayJump = 1;
		
		double rayX, rayY;
		
		for (double ang = -(human.getFov() /2); ang < human.getFov() / 2; ang += angIncrement) {
			double newAng = (human.getAngle() + ang) % FULL_RAD_ROTATION;
			double xInc = Math.cos(newAng) * rayJump;
			double yInc = Math.sin(newAng) * rayJump;
			
			rayX = human.getX() + human.getHalfWidth();
			rayY = human.getY() + human.getHalfHeight();
			for (int rayLen = 0; rayLen < human.getSightDistance(); rayLen += rayJump) {
				rayX += xInc;
				rayY += yInc;
				
				// check if the ray hits a wall
				if (pixels[((int)rayY) * width + ((int)rayX)]) {
					break;
				}
				
				// check if point is inside rectangle
				boolean rayIntersects = rayX >= f.getX() && rayX <= f.getX() + f.getWidth()
									 && rayY >= f.getY() && rayY <= f.getY() + f.getHeight();
				
				if (rayIntersects) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void render(Graphics g, Camera cam) {
		// render floor
		int topLeftX = -(cam.getX() % floorImg.getWidth()) - floorImg.getWidth();
		int topLeftY = -(cam.getY() % floorImg.getHeight()) - floorImg.getHeight();
		
		
		
		for (int y = 0; y < floorDrawTimesY; ++y) {
			for (int x = 0; x < floorDrawTimesX; ++x) {
				g.drawImage(floorImg, topLeftX + (x * floorImg.getWidth()), topLeftY + (y * floorImg.getHeight()));
			}
		}
		
		// render walls
		g.drawImage(wallsImg, -cam.getX(), -cam.getY());
	}
	
}
