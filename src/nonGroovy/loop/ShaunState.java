package nonGroovy.loop;

import nonGroovy.entitys.Background;
import nonGroovy.entitys.GameObject;
import nonGroovy.entitys.StaticObject;
import nonGroovy.models.ModelGenerator;
import nonGroovy.models.TexturedModel;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

class ShaunState implements Loopable {
	
	BasicRenderer renderer;

	Background background;
	
	public ShaunState() {
		background = new Background(-0.5, -1);
		
		renderer = new BasicRenderer();
		glClearColor(0,0,0,1);
	}

	@Override
	public void input() {

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
