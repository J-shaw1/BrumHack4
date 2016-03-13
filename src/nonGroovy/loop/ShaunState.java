package nonGroovy.loop;

import nonGroovy.entitys.GameObject;
import nonGroovy.entitys.StaticObject;
import nonGroovy.models.ModelGenerator;
import nonGroovy.models.TexturedModel;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

class ShaunState implements Loopable {
	
	BasicRenderer renderer;

	StaticObject backgroundA;
	StaticObject backgroundB;
	
	StaticObject backgroundPA;
	StaticObject backgroundPB;
	
	public ShaunState() {
		backgroundA = new StaticObject(540, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("background.png")));
		backgroundPA = new StaticObject(540, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("backgroundParallax.png")));
		backgroundB = new StaticObject(2340, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("background.png")));
		backgroundPB = new StaticObject(2340, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("backgroundParallax.png")));
		
		renderer = new BasicRenderer();
		glClearColor(0,1,0,1);
	}

	@Override
	public void input() {

	}

	@Override
	public void update() {
		for (int i = 0; i < 10; i++) {
			backgroundA.increaseX(-0.25);
			backgroundPA.increaseX(-0.5);
			backgroundB.increaseX(-0.25);
			backgroundPB.increaseX(-0.5);
			
		}
		
		if (backgroundA.getX() < -900) {
			backgroundA.increaseX(3600);
		}
		if (backgroundPA.getX() < -900) {
			backgroundPA.increaseX(3600);
		}
		if (backgroundB.getX() < -900) {
			backgroundB.increaseX(3600);
		}
		if (backgroundPB.getX() < -900) {
			backgroundPB.increaseX(3600);
		}
	}

	@Override
	public void render() {
		renderer.prepareEntity(backgroundA);
		renderer.prepareEntity(backgroundPA);
		renderer.prepareEntity(backgroundB);
		renderer.prepareEntity(backgroundPB);
		renderer.render();
	}

	@Override
	public boolean isLooping() {
		return true;
	}

}
