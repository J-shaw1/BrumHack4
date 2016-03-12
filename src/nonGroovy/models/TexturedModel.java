package nonGroovy.models;

import nonGroovy.renderer.Colour;

public class TexturedModel implements Renderable{
	
	private Model model;
	private int textureID;
	
	public TexturedModel(Model model, int textureID){
		this.model = model;
		this.textureID = textureID;
	}

	public Model getModel() {
		return model;
	}
	
	public int GetTexture() {
		return textureID;
	}
}
