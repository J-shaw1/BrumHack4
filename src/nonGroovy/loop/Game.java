package nonGroovy.loop;

import org.lwjgl.glfw.GLFW;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.window.input.KeyInputCallback;

class Game implements Loopable {
	
	BasicRenderer renderer;
	GameStateManager manager;

	public Game(GameStateManager manager) {
		this.manager = manager;
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
			manager.pushState(new ShaunState());
		}
	}

	@Override
	public void update() {

	}

	@Override
	public void render() {
		
	}

	@Override
	public boolean isLooping() {
		return true;
	}

}
