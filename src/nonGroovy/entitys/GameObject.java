package nonGroovy.entitys;

import nonGroovy.renderer.Colour;

public interface GameObject {
	public int getX();
	public int getY();
	
	public int getWidth();
	public int getHeight();
	
	public Colour getColour();
}
