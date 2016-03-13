package nonGroovy.renderer.text;

import java.util.ArrayList;

import groovy.ui.SystemOutputInterceptor;
import nonGroovy.entitys.GameObject;
import nonGroovy.models.ColouredModel;
import nonGroovy.models.Model;
import nonGroovy.models.Renderable;
import nonGroovy.models.TexturedModel;
import nonGroovy.renderer.shaders.BasicShader;
import nonGroovy.renderer.shaders.TextureShader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class TextRenderer {

	private ArrayList<TextModel> toRender;
	private TextShader textShader;

	public TextRenderer() {
		toRender = new ArrayList<>();
		textShader = new TextShader();
		init();
	}

	private void init() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public void prepareEntity(TextModel gameObject) {
		toRender.add(gameObject);
	}

	public void render() {

		glClear(GL_COLOR_BUFFER_BIT);
		for (TextModel renderable : toRender) {
			
			Renderable masterModel = renderable.getModel();

			Model model = masterModel.getModel();
			

			textShader.enable();
			textShader.setX(540);
			textShader.setY(360);
			textShader.setWidth(renderable.getWidth());
			textShader.setHeight(renderable.getWidth());

			glBindVertexArray(model.getVaoID());

			glEnableVertexAttribArray(0);
			glEnableVertexAttribArray(1);

			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, renderable.GetTexture());

			glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());
			// Disable Positions Vertex Array
			glDisableVertexAttribArray(0);
			glDisableVertexAttribArray(1);

			glBindVertexArray(0);
			textShader.disable();
		}

		toRender.clear();
	}


}
