package groovy

import nonGroovy.entitys.GameObject
import nonGroovy.models.ColouredModel
import nonGroovy.models.ModelGenerator;
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

	boolean remove = false

	public Character() {
		model = ModelGenerator.square();
		colour = new Colour();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	void changeScore(double scoreChange) {

		double curMoneys = this.money
		curMoneys = curMoneys + scoreChange
		setMoney(curMoneys)

	}

	@Override
	public boolean tinted() {
		// TODO Auto-generated method stub
		return false;
	}

}
