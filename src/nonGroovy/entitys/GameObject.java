package nonGroovy.entitys;

import nonGroovy.models.Renderable;
import nonGroovy.renderer.Colour;

public interface GameObject {
	public int getX();
	public int getY();
	
	public int getWidth();
	public int getHeight();
	
	public Colour getColour();
	public Renderable getModel();
}
