package nonGroovy.loop;

import org.lwjgl.glfw.GLFW;

import groovy.Character;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import nonGroovy.entitys.GameObject;
import nonGroovy.models.ColouredModel;
import nonGroovy.models.ModelGenerator;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.Colour;
import nonGroovy.window.Window;
import nonGroovy.window.input.KeyInputCallback;

class Game implements Loopable {

	Window w;

	int xVel = 5;
	int yVel = 5;
	
	BasicRenderer renderer;
	private ColouredModel testModel;
	
	private ArrayList<GameObject> gameObjects;

	public Game() {
		w = new Window();
		renderer = new BasicRenderer();
		gameObjects = new ArrayList<>();
		
		Character c = new Character();
		c.setX(0);
		c.setY(0);
		c.setWidth(10);
		c.setHeight(10);
		c.setColour(new Colour(1f, 0f, 1f));
		
		gameObjects.add(c);
		
	}

	@Override
	public void input() {
		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_SPACE]) {
			System.out.println("Space");
		}
		w.pollEvents();
	}

	@Override
	public void update() {
		for (GameObject gameObject : gameObjects) {
			int tempX = gameObject.getX() + xVel;
			int tempY = gameObject.getY() + yVel;
			
			if (tempX > 1080) {
				tempX = 1080 - xVel;
				xVel = -5;
			}
			
			if (tempX < 0) {
				tempX = xVel;
				xVel = 5;
			}
			
			if (tempY > 720) {
				tempY = 720 - yVel;
				yVel = -5;
			}
			
			if (tempY < 0) {
				tempY = yVel;
				yVel = 5;
			}
			
			gameObject.setX(tempX);
			gameObject.setY(tempY);
		}
	}

	@Override
	public void render() {
		
		for (GameObject gameObject : gameObjects) {
			renderer.prepareEntity(gameObject);
		}
		
		renderer.render();

		w.swapBuffers();
	}

	@Override
	public boolean isLooping() {
		return !w.shouldClose();
	}

}
