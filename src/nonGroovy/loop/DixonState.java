package nonGroovy.loop;

import java.io.File;
import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import groovy.Character;
import groovy.transactions.HealthBar;
import groovy.transactions.MoveType;
import groovy.transactions.Transaction;
import groovy.transactions.TransactionConstants;
import groovy.transactions.Transactions;
import nonGroovy.entitys.GameObject;
import nonGroovy.models.ColouredModel;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.Colour;
import nonGroovy.window.input.KeyInputCallback;

class DixonState implements Loopable {

	int xVel = 5;
	int yVel = 5;
	int oneTimeout;
	int twoTimeout;
	int threeTimeout;
	int fourTimeout;

	int timeoutReset = 30;

	private ArrayList<GameObject> gameObjects;
	private GameStateManager manager;
	Transactions transactions;
	long transactionInterval = (long) (1 * GameLoop.NANO_PER_SECOND);
	long nextTransaction;

	BasicRenderer renderer;
	private ColouredModel testModel;
	Character c;

	public DixonState(GameStateManager manager) {
		this.manager = manager;
		renderer = new BasicRenderer();
		gameObjects = new ArrayList<>();

		c = new Character();
		int width = 80;
		int height = 80;
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

		// demo lines
		Character left = new Character();
		left.setX(TransactionConstants.getPERFECT_HIT_X() - 100);
		left.setY(500);
		left.setWidth(1);
		left.setHeight(1000);
		left.setColour(new Colour(1f, 0f, 1f));

		Character center = new Character();
		center.setX(TransactionConstants.getPERFECT_HIT_X());
		center.setY(500);
		center.setWidth(1);
		center.setHeight(1000);
		center.setColour(new Colour(1f, 0f, 1f));

		Character right = new Character();
		right.setX(TransactionConstants.getPERFECT_HIT_X() + 100);
		right.setY(500);
		right.setWidth(1);
		right.setHeight(1000);
		right.setColour(new Colour(1f, 0f, 1f));

		gameObjects.add(left);
		gameObjects.add(center);
		gameObjects.add(right);

		gameObjects.add(healthBarBackground);
		gameObjects.add(healthBarForeground);
		gameObjects.add(c);

		File file = new File("res/nationwideData/customer131.csv");
		transactions = new Transactions(file);
		nextTransaction = System.nanoTime() + transactionInterval;

	}

	@Override
	public void input() {

		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_8] && oneTimeout < 0) {
			oneTimeout = timeoutReset;
			processAction(MoveType.one);
			c.setY(TransactionConstants.getY_ONE());
			System.out.println("KP8");
		}

		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_5] && twoTimeout < 0) {
			twoTimeout = timeoutReset;
			processAction(MoveType.two);
			c.setY(TransactionConstants.getY_TWO());
			System.out.println("KP5");
		}

		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_2] && threeTimeout < 0) {
			threeTimeout = timeoutReset;
			processAction(MoveType.three);
			c.setY(TransactionConstants.getY_THREE());
			System.out.println("KP2");
		}

		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_0] && fourTimeout < 0) {
			fourTimeout = timeoutReset;
			processAction(MoveType.four);
			c.setY(TransactionConstants.getY_FOUR());
			System.out.println("KP0");
		}

		oneTimeout--;
		twoTimeout--;
		threeTimeout--;
		fourTimeout--;

	}

	public void processAction(MoveType moveType) {

		for (Transaction t : transactions)
			if (t.getMoveTypes()[0] == moveType) {
				if (t.getX() > TransactionConstants.getPERFECT_HIT_X() - TransactionConstants.getPERFECT_FLOAT() && t
						.getX() < TransactionConstants.getPERFECT_HIT_X() + TransactionConstants.getPERFECT_FLOAT()) {
					// if we have a hit
					c.changeScore(
							t.calculateAmountEffect(Math.abs(t.getX() - TransactionConstants.getPERFECT_HIT_X())));
					t.setRemove(true);
				}
			}
	}

	@Override
	public void update() {

		if (System.nanoTime() > nextTransaction) {
			transactions.gotoNext();
			nextTransaction += transactionInterval;
		}

		// Clean up
		for (int i = transactions.getPlace(); i >= 0; i--) {

			if (transactions.get(i).getRemove() || (transactions.get(i).getX() < TransactionConstants.getPERFECT_HIT_X()
					- TransactionConstants.getPERFECT_FLOAT() - 50)) {
				transactions.remove(i);
				transactions.backPlace();
			} else {
				transactions.get(i).update();
			}
		}

		for (GameObject gameObject : gameObjects) {
			gameObject.update();
		}
		// System.out.println("Score: " + score);
	}

	@Override
	public void render() {
		for (int i = 0; i <= transactions.getPlace(); i++) {
			renderer.prepareEntity(transactions.get(i));
		}
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
