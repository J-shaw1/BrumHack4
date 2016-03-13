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

	boolean remove = false

	public HealthBar(Character c) {
		this.c = c;
		model = ModelGenerator.square();
	}

	public void update(){
		if(green){
			int y = (int)Math.round(c.getMoney());
			if(y < 900){
				if(y > 0){
					int curX = this.getX()
					curX = 260 - (500-y)/2
					this.setX(curX)
					setWidth(y)
				}else {
					y = 0;
					setWidth(y)
				}
			} else {
				y = 900
				setWidth(y)
			}
		}
	}
}