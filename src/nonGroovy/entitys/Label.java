package nonGroovy.entitys;

import nonGroovy.models.Renderable;
import nonGroovy.renderer.Colour;
import nonGroovy.renderer.text.TextModel;

public class Label implements GameObject{

	private GameObject object;
	private TextModel model;
	private boolean remove = false;
	
	public Label(GameObject object, TextModel model) {
		this.object = object;
		this.model = model;
	}
	
	
	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public void setX(int x) {

	}

	@Override
	public void setY(int y) {

	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public Colour getColour() {
		return null;
	}

	@Override
	public Renderable getModel() {
		return null;
	}

	@Override
	public void update() {
		model.setX(object.getX());
		model.setY((int) (object.getY() - object.getHeight() * 0.75));
		if(object.getRemove()){
			remove = true;
		}
	}

	@Override
	public boolean getRemove() {
		return remove;
	}


	public TextModel getTextModel() {
		return model;
	}


	@Override
	public boolean tinted() {
		// TODO Auto-generated method stub
		return false;
	}

}
