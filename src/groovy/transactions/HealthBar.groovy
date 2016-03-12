package groovy.transactions

import groovy.Character
import nonGroovy.entitys.GameObject
import nonGroovy.models.ModelGenerator
import nonGroovy.models.Renderable
import nonGroovy.renderer.Colour


class HealthBar implements GameObject{

	Character c
	int x, y
	int width, height
	Renderable model
	Colour colour
	boolean green



	public HealthBar(Character c) {
		this.c = c;
		model = ModelGenerator.square();
	}

	public void update(){
		if(green){
			int y = (int)Math.round(c.getMoney());
			setWidth(y)
		}
	}
}
