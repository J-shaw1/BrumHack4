package nonGroovy.loop;

import static org.lwjgl.opengl.GL11.glClearColor;

import nonGroovy.entitys.Background;
import nonGroovy.renderer.BasicRenderer;
import nonGroovy.renderer.text.Font;
import nonGroovy.renderer.text.TextModel;
import nonGroovy.renderer.text.TextRenderer;

class ShaunState implements Loopable {
	
	TextRenderer textRenderer;
	BasicRenderer renderer;
	Background background;
	
	TextModel model = TextModel.generate("Hello Joe!", new Font("verdana"), 540, 360, 200, 50);
	
	public ShaunState() {
		
		background = new Background(-0.5, -1);
		textRenderer = new TextRenderer();
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
		//background.render(renderer);
		textRenderer.prepareEntity(model);
		textRenderer.render();
		//renderer.render();
	}

	@Override
	public boolean isLooping() {
		return true;
	}

}
