package nonGroovy.loop;

import java.util.ArrayList;

import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.text.TextRenderer;
import nonGroovy.window.Window;

public class GameStateManager implements Loopable{

	public ArrayList<Loopable> states;
	
	Window window;
	
	public GameStateManager(){
		states = new ArrayList<>();
		window = new Window();
	}
	
	public void pushState(Loopable loop){
		states.add(loop);
	}
	
	public void popState(Loopable loop){
		if(!states.isEmpty())
		states.remove(states.size()-1);
	}



	@Override
	public void input() {
		window.pollEvents();
		if (!states.isEmpty()) {
			states.get(states.size()-1).input();
		}
	}



	@Override
	public void update() {
		if (!states.isEmpty()) {
			states.get(states.size()-1).update();
		}
	}



	@Override
	public void render() {
		if (!states.isEmpty()) {
			states.get(states.size()-1).render();
		}
		window.swapBuffers();
	}



	@Override
	public boolean isLooping() {
		if (!states.isEmpty()) {
			return states.get(states.size()-1).isLooping() && !window.shouldClose();
		} else {
			return false;
		}
	}
}
