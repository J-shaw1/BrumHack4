package nonGroovy.models;

import nonGroovy.renderer.Colour;

public class ColouredModel implements Renderable{
	
	private Model model;
	private Colour baseColour;
	
	public ColouredModel(Model model, Colour baseColour){
		this.model = model;
		this.baseColour = baseColour;
	}

	public Model getModel() {
		return model;
	}

	public Colour getBaseColour() {
		return baseColour;
	}
}
