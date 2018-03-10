package ronan_hanley.galway_game_jam.nine.level;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ronan_hanley.galway_game_jam.nine.entity.Camera;
import ronan_hanley.galway_game_jam.nine.entity.MovingEntity;

public class Level {
	private Image image;
	private int width;
	private int height;
	private boolean[] pixels;
	
	public Level(int levelNum) {
		try {
			image = new Image(String.format("res/level%d.png", levelNum));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		loadPixels();
		
		width = image.getWidth();
		height = image.getHeight();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void loadPixels() {
		pixels = new boolean[image.getWidth() * image.getHeight()];
		
		int pixelCounter = 0;
		
		for (int y = 0; y < image.getHeight(); ++y) {
			for (int x = 0; x < image.getWidth(); x++) {
				pixels[pixelCounter++] = image.getColor(x, y).equals(Color.black);
			}
		}
	}
	
	public boolean collides(MovingEntity e) {
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
		
		return false;
	}
	
	public void render(Graphics g, Camera cam) {
		g.drawImage(image, -cam.getX(), -cam.getY());
	}
	
}