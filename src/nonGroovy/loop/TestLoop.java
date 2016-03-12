package nonGroovy.loop;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.opengl.GL11.*;

import nonGroovy.models.ColouredModel;
import nonGroovy.models.ModelGenerator;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.Colour;
import nonGroovy.window.Window;
import nonGroovy.window.input.KeyInputCallback;

class TestLoop implements Loopable {

	int count = 300;
	Window w;

	BasicRenderer renderer;
	private ColouredModel testModel;

	public TestLoop() {
		w = new Window();
		renderer = new BasicRenderer();

		testModel = new ColouredModel(ModelGenerator.square(), new Colour(1, 0, 0));
	}

	@Override
	public void input() {
		if (KeyInputCallback.isKeyDown[GLFW.GLFW_KEY_SPACE]) {
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
		renderer.prepareEntity(testModel);
		renderer.render();

		w.swapBuffers();
	}

	@Override
	public boolean isLooping() {
		return !w.shouldClose();
	}

}
