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
