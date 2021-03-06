package nonGroovy.loop;

import java.awt.font.FontRenderContext;
import java.io.File;
import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import groovy.Character;
import groovy.transactions.HealthBar;
import groovy.transactions.MoveType;
import groovy.transactions.Transaction;
import groovy.transactions.TransactionConstants;
import groovy.transactions.Transactions;
import nonGroovy.entitys.Background;
import nonGroovy.entitys.GameObject;
import nonGroovy.entitys.Label;
import nonGroovy.models.ModelGenerator;
import nonGroovy.models.TexturedModel;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.Colour;
import nonGroovy.renderer.TextureLoader;
import nonGroovy.renderer.text.Font;
import nonGroovy.renderer.text.TextModel;
import nonGroovy.renderer.text.TextRenderer;
import nonGroovy.window.input.KeyInputCallback;

class ShaunState implements Loopable {

	int xVel = 5;
	int yVel = 5;
	int oneTimeout;
	int twoTimeout;
	int threeTimeout;
	int fourTimeout;

	int timeoutReset = 30;

	long transactionInterval = (long) (1f * GameLoop.NANO_PER_SECOND);
	long nextTransaction;

	private ArrayList<GameObject> gameObjects;
	private ArrayList<Label> labels;
	private GameStateManager manager;
	Transactions transactions;

	BasicRenderer renderer;
	TextRenderer textRenderer;
	Character c;
	
	Label hitLabel;
	
	private Background background;

	private Font verdana = new Font("verdana");
	
	public ShaunState(TextRenderer textRenderer, BasicRenderer renderer, Background background, Transactions transactions, Character c) {
		this.textRenderer = textRenderer;
		this.transactions = transactions;
		this.manager = manager;
		this.background = background;
		this.renderer = renderer;
		this.gameObjects = new ArrayList<>();
		labels = new ArrayList<>();

		

		this.c = c;

		int height = 40;
		int width = 900;

		HealthBar healthBarBackground = new HealthBar(c);
		healthBarBackground.setColour(new Colour(1f, 0f, 0f));

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

		// Demo lines
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

		this.gameObjects.add(left);
		this.gameObjects.add(center);
		this.gameObjects.add(right);

		this.gameObjects.add(healthBarBackground);
		this.gameObjects.add(healthBarForeground);
		this.gameObjects.add(c);

		nextTransaction = System.nanoTime() + transactionInterval;
	}

	@Override
	public void input() {

		Transaction t = transactions.get(0);
		
		if (t.getX() < 250 && t.getX() > 240 && !t.getRemove()) {
			if (t.getY() == 600) {
				KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_8] = true;
			} else if(t.getY() == 500){
				KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_5] = true;
			} else if(t.getY() == 300){
				KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_2] = true;
			} else if(t.getY() == 200){
				KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_0] = true;
			}
			
		}
		
		if ((KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_8]|| KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_1]) && oneTimeout < 0) {
			oneTimeout = timeoutReset;
			processAction(MoveType.one);
			c.setY(TransactionConstants.getY_ONE());
			System.out.println("KP8");
		}

		if ((KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_5]|| KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_2]) && twoTimeout < 0) {
			twoTimeout = timeoutReset;
			processAction(MoveType.two);
			c.setY(TransactionConstants.getY_TWO());
			System.out.println("KP5");
		}

		if ((KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_2]|| KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_3]) && threeTimeout < 0) {
			threeTimeout = timeoutReset;
			processAction(MoveType.three);
			c.setY(TransactionConstants.getY_THREE());
			System.out.println("KP2");
		}

		if ((KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_0]|| KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_4]) && fourTimeout < 0) {
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
					Label l = new Label(null, TextModel.generate("Amount: " + t.getAmount() + "  Description: " + t.getDescription(), verdana, 600, -1000, 1080, 300));
					l.setY(-300);
					l.setX(500);
					labels.add(l);
					if (c.getMoney() > 0) {
						transactionInterval -= 1000000;
					}
				}
			}
	}

	@Override
	public void update() {
		background.speedMultiplier = 1;
		if (System.nanoTime() > nextTransaction) {
			transactions.gotoNext();
			labels.add(new Label((transactions.get(transactions.getPlace())), TextModel.generate((transactions.get(transactions.getPlace()).getAmount()) + "", verdana, 0, 0, 100, 100)));
			
			
			nextTransaction += transactionInterval;
			System.out.println(transactionInterval);
		}

		// Clean up
		for (int i = transactions.getPlace(); i >= 0; i--) {

			Transaction t = transactions.get(i);

			if ((t.getX() < TransactionConstants.getPERFECT_HIT_X() - TransactionConstants.getPERFECT_FLOAT() - 50)) {

				t.setRemove(true);
				if (t.getAmount() < 0) {
					c.changeScore(2.5 * t.getAmount());
				}
				if (transactionInterval > 500000000) {
					transactionInterval -= 50000000;
				} else {
					transactionInterval = 500000000;
				}

			}

			if (t.getRemove()) {
				transactions.remove(i);
				transactions.backPlace();
			} else {
				t.update();
			}
		}

		for (GameObject gameObject : gameObjects) {
			gameObject.update();
		}
		
		for (int i = labels.size()-1; i >= 0; i--) {
			if(labels.get(i).getRemove()){
				labels.remove(i);
			} else {
				labels.get(i).update();	
			}
		}
		
		if (hitLabel != null) {
			hitLabel.update();
		}
		
		background.update();
	}

	@Override
	public void render() {

		background.render(renderer);
		for (int i = 0; i <= transactions.getPlace(); i++) {
			renderer.prepareEntity(transactions.get(i));
		}
		for (GameObject gameObject : gameObjects) {
			renderer.prepareEntity(gameObject);
		}
		if (hitLabel != null) {
			textRenderer.prepareEntity(hitLabel.getTextModel());
		}
		for (Label label : labels) {
			textRenderer.prepareEntity(label.getTextModel());
		}

		renderer.render();
		textRenderer.render();

	}

	@Override
	public boolean isLooping() {
		return true;
	}

}
