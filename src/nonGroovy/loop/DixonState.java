package nonGroovy.loop;

import org.lwjgl.glfw.GLFW;

import groovy.Character;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import nonGroovy.entitys.GameObject;
import nonGroovy.models.ColouredModel;
import nonGroovy.models.ModelGenerator;
import nonGroovy.models.TexturedModel;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.Colour;
import nonGroovy.renderer.TextureLoader;
import nonGroovy.window.Window;
import nonGroovy.window.input.KeyInputCallback;

class DixonState implements Loopable {

	BasicRenderer renderer;
	
	public DixonState() {
		renderer = new BasicRenderer();
		glClearColor(1, 0, 0, 1);
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
