package nonGroovy.loop;

import java.io.File;

import org.lwjgl.glfw.GLFW;

import groovy.Character;
import groovy.transactions.Transactions;
import nonGroovy.entitys.Background;
import nonGroovy.models.ModelGenerator;
import nonGroovy.models.TexturedModel;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.Colour;
import nonGroovy.renderer.TextureLoader;
import nonGroovy.renderer.text.TextRenderer;
import nonGroovy.window.input.KeyInputCallback;

class Game implements Loopable {
	
	GameStateManager manager;
	
	BasicRenderer renderer = new BasicRenderer();
	TextRenderer textRenderer = new TextRenderer();
	Background background = new Background(100);
	
	File file = new File("res/nationwideData/customer131.csv");
	Transactions transactions = new Transactions(file);
	
	Loopable nextState;
	
	Character c;

	public Game(GameStateManager manager) {
		this.manager = manager;
		
		int width = 80;
		int height = 80;
		
		this.c = new Character();
		this.c.setMoney(900.0);
		this.c.setX(10 + width / 2);
		this.c.setY(10 + height / 2);
		this.c.setWidth(width);
		this.c.setHeight(height);
		this.c.setColour(new Colour(1f, 0f, 1f));
		this.c.setModel(new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("rocket.png")));
		
		
		TextureLoader.loadTexture("singleAstorid.png");
		TextureLoader.loadTexture("rocket.png");
	}

	@Override
	public void input() {
		if(KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_D]){
			manager.pushState(new DixonState(manager));
		}
		if(KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_J]){
			manager.pushState(new JoeState());
		}
		if(KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_S]){
			nextState = new ShaunState(textRenderer, renderer, background, transactions, c);
		}
		
	}

	@Override
	public void update() {
		background.update();
		if (nextState != null) {
			if (background.speedMultiplier < 1) {
				background.speedMultiplier = 1;
				manager.pushState(nextState);
				
			} else {
				background.speedMultiplier -= 1f;
			}
		}
		c.update();
	}

	@Override
	public void render() {
		background.render(renderer);
		renderer.prepareEntity(c);
		renderer.render();
	}

	@Override
	public boolean isLooping() {
		return true;
	}

}
