package nonGroovy.loop;

import java.io.File;
import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import groovy.Character;
import groovy.transactions.MoveType;
import groovy.transactions.Transaction;
import groovy.transactions.TransactionConstants;
import groovy.transactions.Transactions;
import nonGroovy.entitys.GameObject;
import nonGroovy.models.ColouredModel;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.Colour;
import nonGroovy.window.input.KeyInputCallback;

class JoeState implements Loopable {

	int xVel = 5;
	int yVel = 5;
	
	Transactions transactions;
	long transactionInterval = (long) (1 * GameLoop.NANO_PER_SECOND);
	long nextTransaction;
		
	BasicRenderer renderer;
	private ColouredModel testModel;
	Character c;
	
	private ArrayList<GameObject> gameObjects;

	public JoeState() {
		renderer = new BasicRenderer();
		gameObjects = new ArrayList<>();
		
		c = new Character();
		c.setX(0);
		c.setY(0);
		c.setWidth(10);
		c.setHeight(10);
		c.setColour(new Colour(1f, 0f, 1f));
		
		
		//demo lines
		Character left = new Character();
		left.setX(TransactionConstants.getPERFECT_HIT_X() - 100);
		left.setY(500);
		left.setWidth(1);
		left.setHeight(1000);
		left.setColour(new Colour(1f, 0f, 1f));
		gameObjects.add(left);
		
		Character center = new Character();
		center.setX(TransactionConstants.getPERFECT_HIT_X());
		center.setY(500);
		center.setWidth(1);
		center.setHeight(1000);
		center.setColour(new Colour(1f, 0f, 1f));
		gameObjects.add(center);
		
		Character right = new Character();
		right.setX(TransactionConstants.getPERFECT_HIT_X() + 100);
		right.setY(500);
		right.setWidth(1);
		right.setHeight(1000);
		right.setColour(new Colour(1f, 0f, 1f));
		gameObjects.add(right);
		//
		
		
		
		File file = new File("res/nationwideData/customer131.csv");
		transactions = new Transactions(file);
		nextTransaction = System.nanoTime() + transactionInterval;
		
		gameObjects.add(c);
		
	}

	int upTimeout;
	int leftTimeout;
	int downTimeout;
	int rightTimeout;
	
	int timeoutReset = 30;
	
	@Override
	public void input() {
		
		
		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_UP] && upTimeout < 0) {
			upTimeout = timeoutReset;
			
			for(GameObject gameObject : gameObjects){
				if(gameObject instanceof Transaction){
					
					Transaction t = (Transaction) gameObject;
					
					if(t.getMoveTypes()[0] == MoveType.up){
						if(t.getX() > TransactionConstants.getPERFECT_HIT_X() - 100 && t.getX() < TransactionConstants.getPERFECT_HIT_X() + 100){
							//if we have a hit
							c.changeScore(t.calculateAmountEffect(Math.abs(t.getX() - TransactionConstants.getPERFECT_HIT_X())));
						}
					}
				}
			}
			System.out.println("Score: " + score);
			System.out.println("UP");
		}
		
		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_LEFT]  && leftTimeout < 0) {
			leftTimeout = timeoutReset;
			System.out.println("LEFT");
		}
		
		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_DOWN]  && downTimeout < 0) {
			downTimeout = timeoutReset;
			System.out.println("DOWN");
		}
		
		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_RIGHT]  && rightTimeout < 0) {
			rightTimeout = timeoutReset;
			System.out.println("RIGHT");
		}
		
		upTimeout--;
		leftTimeout--;
		downTimeout--;
		rightTimeout--;
		
	}

	@Override
	public void update() {
		
		if(System.nanoTime() > nextTransaction){
			gameObjects.add(transactions.getNext());
			nextTransaction += transactionInterval;
		}
		
		
		for (GameObject gameObject : gameObjects) {			
			gameObject.update();
		}
		//System.out.println("Score: " + score);
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
