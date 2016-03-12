package groovy

import nonGroovy.entitys.GameObject
import nonGroovy.renderer.Colour

class Character implements GameObject{

	//Location for Icon
	File icon
	//Health
	double money

	int x,y
	int width,height

	Colour colour
	
	public Character() {
	}

}
