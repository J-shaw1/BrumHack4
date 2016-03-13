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
		left.setX(TransactionConstants.getPERFECT_HIT_X() - TransactionConstants.getPERFECT_FLOAT());
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
		right.setX(TransactionConstants.getPERFECT_HIT_X() + TransactionConstants.getPERFECT_FLOAT());
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

	int oneTimeout;
	int twoTimeout;
	int threeTimeout;
	int fourTimeout;
	
	int timeoutReset = 30;
	
	@Override
	public void input() {
		
		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_8] && oneTimeout < 0) {
			oneTimeout = timeoutReset;
			processAction(MoveType.one);
			System.out.println("KP8");
		}
		
		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_5]  && twoTimeout < 0) {
			twoTimeout = timeoutReset;
			processAction(MoveType.two);
			System.out.println("KP5");
		}
		
		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_2]  && threeTimeout < 0) {
			threeTimeout = timeoutReset;
			processAction(MoveType.three);
			System.out.println("KP2");
		}
		
		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_KP_0]  && fourTimeout < 0) {
			fourTimeout = timeoutReset;
			processAction(MoveType.four);
			System.out.println("KP0");
		}
		
		oneTimeout--;
		twoTimeout--;
		threeTimeout--;
		fourTimeout--;
		
	}

	public void processAction(MoveType moveType){	

		for(Transaction t : transactions)	
			if(t.getMoveTypes()[0] == moveType){
				if(t.getX() > TransactionConstants.getPERFECT_HIT_X() - TransactionConstants.getPERFECT_FLOAT() 
						&& t.getX() < TransactionConstants.getPERFECT_HIT_X() + TransactionConstants.getPERFECT_FLOAT()){
					//if we have a hit
					System.out.println("Worth: " + t.getAmount());
					System.out.println("Score change: " + t.calculateAmountEffect(Math.abs(t.getX() - TransactionConstants.getPERFECT_HIT_X())));
					
					c.changeScore(t.calculateAmountEffect(Math.abs(t.getX() - TransactionConstants.getPERFECT_HIT_X())));
					System.out.println("Score: " + c.getMoney());
					t.setRemove(true);
				}
			}

	}
	
	@Override
	public void update() {
		
		if(System.nanoTime() > nextTransaction){
			transactions.gotoNext();
			nextTransaction += transactionInterval;
		}
		
		//Clean up
		for(int i = transactions.getPlace(); i >=0; i--) {
			
			Transaction t = transactions.get(i);
			
			if((t.getX() < TransactionConstants.getPERFECT_HIT_X() - TransactionConstants.getPERFECT_FLOAT() - 50)){
				c.changeScore(2.5 * t.getAmount());
				t.setRemove(true);
			}
			
			if(t.getRemove()) {
				
				transactions.remove(i);
				transactions.backPlace();
			} else {
				t.update();
			}
		}
		
		
		for (GameObject gameObject : gameObjects) {			
			gameObject.update();
		}

	}

	@Override
	public void render() {
		
		for(int i = 0; i <= transactions.getPlace(); i++) {
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
