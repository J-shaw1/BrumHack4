package nonGroovy.entitys;

import nonGroovy.models.Renderable;
import nonGroovy.renderer.Colour;

public interface GameObject {
	public int getX();
	public int getY();
	
	public void setX(int x);
	public void setY(int y);
	
	public int getWidth();
	public int getHeight();
	
	public Colour getColour();
	public Renderable getModel();
	
	public void update();
	
	public boolean getRemove();
}
