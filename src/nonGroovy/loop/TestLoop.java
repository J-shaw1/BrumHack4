package nonGroovy.loop;

import org.lwjgl.glfw.GLFW;

import nonGroovy.window.Window;
import nonGroovy.window.input.KeyInputCallback;

class TestLoop implements Loopable{

	int count = 300;
	Window w;
	
	public TestLoop(){
		w = new Window();
	}
	
	
	@Override
	public void input() {
		if(KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_SPACE]){
			System.out.println("Space");
		}
		w.pollEvents();
	}

	@Override
	public void update() {
		count--;
		
	}

	@Override
	public void render() {
		w.swapBuffers();
	}

	@Override
	public boolean isLooping() {
		
		return !w.shouldClose();
	}

}
