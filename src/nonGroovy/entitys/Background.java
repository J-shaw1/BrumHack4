package nonGroovy.entitys;

import nonGroovy.models.ModelGenerator;
import nonGroovy.models.TexturedModel;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.TextureLoader;

public class Background {
	
	private double backgroundSpeed = -0.25;
	private double parallaxSpeed = -0.5;
	
	private StaticObject backgroundA;
	private StaticObject backgroundPA;
	private StaticObject backgroundB;
	private StaticObject backgroundPB;
	
	public float speedMultiplier;

	public Background(double backgroundSpeed, double parallaxSpeed){
		this.backgroundSpeed = backgroundSpeed;
		this.parallaxSpeed = parallaxSpeed;
		backgroundA = new StaticObject(540, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("background.png")));
		backgroundPA = new StaticObject(540, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("backgroundParallax.png")));
		backgroundB = new StaticObject(2340, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("background.png")));
		backgroundPB = new StaticObject(2340, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("backgroundParallax.png")));
	}
	
	
	public Background(float i) {
		this.speedMultiplier = i;
		this.backgroundSpeed = -2 * i;
		this.parallaxSpeed = -4 * i;
		backgroundA = new StaticObject(540, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("background.png")));
		backgroundPA = new StaticObject(540, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("backgroundParallax.png")));
		backgroundB = new StaticObject(2340, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("background.png")));
		backgroundPB = new StaticObject(2340, 360, 1800, 720, new TexturedModel(ModelGenerator.square(), TextureLoader.loadTexture("backgroundParallax.png")));
	}

	public void update(){
		this.backgroundSpeed = -2 * speedMultiplier;
		this.parallaxSpeed = -4 * speedMultiplier;
		backgroundA.increaseX(backgroundSpeed);
		backgroundPA.increaseX(parallaxSpeed);
		backgroundB.increaseX(backgroundSpeed);
		backgroundPB.increaseX(parallaxSpeed);
	
	
	if (backgroundA.getX() < -900) {
		backgroundA.setX(backgroundB.getX()+1800);
	}
	if (backgroundPA.getX() < -900) {
		backgroundPA.setX(backgroundPB.getX()+1800);
	}
	if (backgroundB.getX() < -900) {
		backgroundB.setX(backgroundA.getX()+1800);
	}
	if (backgroundPB.getX() < -900) {
		backgroundPB.setX(backgroundPA.getX()+1800);
	}
	}
	
	public void render(BasicRenderer renderer){
		renderer.prepareEntity(backgroundA);
		renderer.prepareEntity(backgroundB);
		renderer.prepareEntity(backgroundPA);
		renderer.prepareEntity(backgroundPB);
	}
	
	
}
