package nonGroovy.renderer;

import java.util.ArrayList;

import nonGroovy.models.ColouredModel;
import nonGroovy.models.Model;
import nonGroovy.models.Renderable;
import nonGroovy.renderer.shaders.BasicShader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class BasicRenderer {
	
	private ArrayList<Renderable> toRender;
	private BasicShader shader;
	
	
	public BasicRenderer(){
		 toRender = new ArrayList<>();
		 shader = new BasicShader();
		 init();
	}
	
	private void init(){
//		glEnable(GL_CULL_FACE);
//		glCullFace(GL_BACK);
//		glEnable(GL_DEPTH_TEST);
//		glDepthFunc(GL_LESS);
	}
	
	public void prepareEntity(Renderable model){
		toRender.add(model);
	}
	
	public void render(){
		
		shader.enable();
		glClear(GL_COLOR_BUFFER_BIT);
		
		for (Renderable renderable : toRender) {
			Model model = renderable.getModel();
			
			glBindVertexArray(model.getVaoID());
			
			glEnableVertexAttribArray(0);
			
			//glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
			glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());
			// Disable Positions Vertex Array
			glDisableVertexAttribArray(0);
			
			glBindVertexArray(0);
			
		}
		toRender.clear();
	}

	public void renderModel(ColouredModel testModel) {
		Model model = testModel.getModel();
		System.out.println(model.getVaoID());
		glBindVertexArray(model.getVaoID());
		
		glEnableVertexAttribArray(0);
		
		//glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
		glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());
		// Disable Positions Vertex Array
		glDisableVertexAttribArray(0);
		
		glBindVertexArray(0);
	}
	
	
}
