package nonGroovy.entitys;

import nonGroovy.models.Renderable;
import nonGroovy.renderer.Colour;

public class StaticObject implements GameObject{

	private double x, y;
	private int width, height;
	private Renderable masterModel;
	
	public StaticObject(int x, int y, int width, int height, Renderable masterModel) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.masterModel = masterModel;
	}

	@Override
	public int getX() {
		return (int)x;
	}

	@Override
	public int getY() {
		return (int)y;
	}

	public void increaseX(double dx){
		x += dx;
	}
	
	public void increaseY(double dy){
		y+= dy;
	}
	
	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Colour getColour() {
		return new Colour();
	}

	@Override
	public Renderable getModel() {
		return masterModel;
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean getRemove() {
		
		return false;
	}

}
