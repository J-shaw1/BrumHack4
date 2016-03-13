package nonGroovy.renderer;

import java.util.ArrayList;

import groovy.transactions.Transaction;
import groovy.ui.SystemOutputInterceptor;
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
		glEnable(GL_BLEND); 
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
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
				basicShader.disable();
			} else {
				texturedShader.enable();
				if (renderable instanceof Transaction) {
					Transaction t = (Transaction)renderable;
					if(t.getAmount() > 0){
						basicShader.setColour(new Colour(0f,1f,0f));
					} else {
						basicShader.setColour(new Colour(1f,0f,0f));
					}
					
				} else {
					texturedShader.setColour(renderable.getColour());
				}
				texturedShader.setHeight(renderable.getHeight());
				texturedShader.setWidth(renderable.getWidth());
				texturedShader.setX(renderable.getX());
				texturedShader.setY(renderable.getY());
				texturedShader.setTinted(renderable.tinted());
				
				glBindVertexArray(model.getVaoID());
				
				glEnableVertexAttribArray(0);
				glEnableVertexAttribArray(1);
				
				glActiveTexture(GL_TEXTURE0);
				glBindTexture(GL_TEXTURE_2D, masterModel.GetTexture());	
				
				//glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
				glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());
				// Disable Positions Vertex Array
				glDisableVertexAttribArray(0);
				glDisableVertexAttribArray(1);
				
				glBindVertexArray(0);
				texturedShader.disable();
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
