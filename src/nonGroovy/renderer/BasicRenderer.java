package nonGroovy.renderer;

import java.util.ArrayList;

import nonGroovy.entitys.GameObject;
import nonGroovy.models.ColouredModel;
import nonGroovy.models.Model;
import nonGroovy.models.Renderable;
import nonGroovy.renderer.shaders.BasicShader;
import nonGroovy.renderer.shaders.TextureShader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class BasicRenderer {
	
	private ArrayList<GameObject> toRender;
	private BasicShader basicShader;
	private TextureShader texturedShader;
	
	
	public BasicRenderer(){
		 toRender = new ArrayList<>();
		 basicShader = new BasicShader();
		 texturedShader = new TextureShader();
		 init();
	}
	
	private void init(){
		
	}
	
	public void prepareEntity(GameObject gameObject){
		toRender.add(gameObject);
	}
	
	public void render(){
		
		
		glClear(GL_COLOR_BUFFER_BIT);
		
		for (GameObject renderable : toRender) {
			
			Renderable masterModel = renderable.getModel();
			
			Model model = masterModel.getModel();
			
			if (masterModel.GetTexture() == -1) {
				basicShader.enable();
				basicShader.setColour(renderable.getColour());
				System.out.println(renderable.getColour());
				basicShader.setHeight(renderable.getHeight());
				basicShader.setWidth(renderable.getWidth());
				basicShader.setX(renderable.getX());
				basicShader.setY(renderable.getY());
				
				glBindVertexArray(model.getVaoID());
				
				glEnableVertexAttribArray(0);
				
				//glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
				glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());
				// Disable Positions Vertex Array
				glDisableVertexAttribArray(0);
				
				glBindVertexArray(0);
			} else {
				texturedShader.enable();
				texturedShader.setColour(renderable.getColour());
				texturedShader.setHeight(renderable.getHeight());
				texturedShader.setWidth(renderable.getWidth());
				texturedShader.setX(renderable.getX());
				texturedShader.setY(renderable.getY());
				
				glBindVertexArray(model.getVaoID());
				
				glEnableVertexAttribArray(0);
				
				glActiveTexture(GL_TEXTURE0);
				glBindTexture(GL_TEXTURE_2D, masterModel.GetTexture());	
				
				//glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
				glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());
				// Disable Positions Vertex Array
				glDisableVertexAttribArray(0);
				
				glBindVertexArray(0);
			}
			
			
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
