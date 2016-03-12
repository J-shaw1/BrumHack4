package groovy

import nonGroovy.entitys.GameObject
import nonGroovy.models.Renderable;
import nonGroovy.renderer.Colour

class Character implements GameObject{

	//Location for Icon
	File icon
	//Health
	double money

	int x,y
	int width,height
	
	Renderable model;

	Colour colour
	
	public Character() {
	}

	public Renderable getModel() {
		return model;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
