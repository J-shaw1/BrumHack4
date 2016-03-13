package nonGroovy.loop;

import org.lwjgl.glfw.GLFW;

import nonGroovy.entitys.Background;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.TextureLoader;
import nonGroovy.renderer.text.TextRenderer;
import nonGroovy.window.input.KeyInputCallback;

class Game implements Loopable {
	
	GameStateManager manager;
	
	BasicRenderer renderer = new BasicRenderer();
	TextRenderer textRenderer = new TextRenderer();
	Background background = new Background(-0.5, -1.0);

	public Game(GameStateManager manager) {
		this.manager = manager;
		
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
			manager.pushState(new ShaunState(textRenderer, renderer, background));
		}
	}

	@Override
	public void update() {
		background.update();
	}

	@Override
	public void render() {
		background.render(renderer);
		renderer.render();
	}

	@Override
	public boolean isLooping() {
		return true;
	}

}
