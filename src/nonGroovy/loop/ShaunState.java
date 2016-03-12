package nonGroovy.loop;

import nonGroovy.renderer.BasicRenderer;
import static org.lwjgl.opengl.GL11.*;

class ShaunState implements Loopable {
	
	BasicRenderer renderer;

	public ShaunState() {
		
		renderer = new BasicRenderer();
		glClearColor(0,1,0,1);
	}

	@Override
	public void input() {

	}

	@Override
	public void update() {

	}

	@Override
	public void render() {
		
		renderer.render();
	}

	@Override
	public boolean isLooping() {
		return true;
	}

}
