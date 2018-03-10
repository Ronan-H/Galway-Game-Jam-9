package ronan_hanley.galway_game_jam.nine;

import static org.newdawn.slick.Input.*;

import org.newdawn.slick.InputListener;

public class Input implements InputListener {
	public boolean leftHeld;
	public boolean rightHeld;
	public boolean upHeld;
	public boolean downHeld;
	
	public void toggle(int keycode, boolean state) {
		switch (keycode) {
		case KEY_UP:
			upHeld = true;
			break;
		case KEY_DOWN:
			downHeld = true;
			break;
		case KEY_LEFT:
			leftHeld = true;
			break;
		case KEY_RIGHT:
			rightHeld = true;
			break;
		}
	}
	
	@Override
	public void keyPressed(int keycode, char keyChar) {
		toggle(keycode, true);
	}

	@Override
	public void keyReleased(int keycode, char keyChar) {
		toggle(keycode, false);
	}
	
	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {}

	@Override
	public void mouseWheelMoved(int arg0) {}

	@Override
	public void inputEnded() {}

	@Override
	public void inputStarted() {}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {}

	@Override
	public void controllerDownPressed(int arg0) {}

	@Override
	public void controllerDownReleased(int arg0) {}

	@Override
	public void controllerLeftPressed(int arg0) {}

	@Override
	public void controllerLeftReleased(int arg0) {}

	@Override
	public void controllerRightPressed(int arg0) {}

	@Override
	public void controllerRightReleased(int arg0) {}

	@Override
	public void controllerUpPressed(int arg0) {}

	@Override
	public void controllerUpReleased(int arg0) {}
	
	@Override
	public void setInput(org.newdawn.slick.Input arg0) {}
	
}