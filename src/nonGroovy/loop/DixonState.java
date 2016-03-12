package nonGroovy.loop;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import groovy.Character;
import groovy.transactions.HealthBar;
import nonGroovy.entitys.GameObject;
import nonGroovy.models.ColouredModel;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.Colour;
import nonGroovy.window.Window;
import nonGroovy.window.input.KeyInputCallback;

class DixonState implements Loopable {

	int xVel = 5;
	int yVel = 5;

	BasicRenderer renderer;
	private ColouredModel testModel;

	private ArrayList<GameObject> gameObjects;
	private GameStateManager manager;

	public DixonState(GameStateManager manager) {
		this.manager = manager;
		renderer = new BasicRenderer();
		gameObjects = new ArrayList<>();

		Character c = new Character();
		int width = 250;
		int height = 540;
		c.setMoney(500.0);
		c.setX(10 + width / 2);
		c.setY(10 + height / 2);
		c.setWidth(width);
		c.setHeight(height);
		c.setColour(new Colour(1f, 0f, 1f));

		HealthBar healthBarBackground = new HealthBar(c);
		healthBarBackground.setColour(new Colour(1f, 0f, 0f));

		height = 40;
		width = 500;

		healthBarBackground.setX(10 + width / 2);
		healthBarBackground.setY(660 + height / 2);
		healthBarBackground.setWidth(width);
		healthBarBackground.setHeight(height);

		HealthBar healthBarForeground = new HealthBar(c);
		healthBarForeground.setColour(new Colour(0f, 1f, 0f));

		healthBarForeground.setX(10 + width / 2);
		healthBarForeground.setY(660 + height / 2);
		healthBarForeground.setWidth(width);
		healthBarForeground.setHeight(height);
		healthBarForeground.setGreen(true);

		gameObjects.add(healthBarBackground);
		gameObjects.add(healthBarForeground);
		gameObjects.add(c);

	}

	@Override
	public void input() {
		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_SPACE]) {
			System.out.println("Space");
		}
	}

	@Override
	public void update() {
		for (GameObject gameObject : gameObjects) {
			gameObject.update();
		}
	}

	@Override
	public void render() {

		for (GameObject gameObject : gameObjects) {
			renderer.prepareEntity(gameObject);
		}

		renderer.render();

	}

	@Override
	public boolean isLooping() {
		return true;
	}

}
