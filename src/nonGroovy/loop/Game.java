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
		
		gameObjects.add(c);
		
		testModel = new ColouredModel(ModelGenerator.square(), new Colour(1, 0, 0));
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
