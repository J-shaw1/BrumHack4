package nonGroovy.renderer.text;

import nonGroovy.entitys.GameObject;
import nonGroovy.models.Model;
import nonGroovy.models.Renderable;
import nonGroovy.renderer.Colour;



public class TextModel implements Renderable{

	public static final double TEXTURE_SIZE = 512.0;
	
	private int x,y,width,height; 
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	private Model model;
	private int textureID;
	
	public TextModel(int x, int y, int width, int height, float fontSize, Model model, int textureID) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.model = model;
		this.textureID = textureID;
	}

	public Model getModel() {
		return model;
	}
	
	public int GetTexture() {
		return textureID;
	}

	public int getX() {
		return (int) (x - width / 2.0);
	}

	public int getY() {
		return (int) (y - height / 2.0);
	}

	public static TextModel generate(String text, Font font, int x, int y, int width, int height){
		
		float[] positions = new float[text.length() * 18];
		
		float[] textures = new float[text.length() * 12];
		
		int xCursor = 0;
		
		for (int i = 0; i < text.length(); i++){
		    char c = text.charAt(i);
		    
		    Character character = font.getCharacter(c);
		    
		    if (character != null) {
		    	
				positions[i * 18 + 0] = xCursor + character.xoffset;
				positions[i * 18 + 1] = character.yoffset;
				
				textures[i * 12 + 0] = (float) (character.x / TEXTURE_SIZE);
				textures[i * 12 + 1] = (float) (character.y / TEXTURE_SIZE);
				
				positions[i * 18 + 3] = xCursor + character.xoffset + character.width;
				positions[i * 18 + 4] = character.yoffset;
				
				textures[i * 12 + 2] = (float) ((character.x + character.width) / TEXTURE_SIZE);
				textures[i * 12 + 3] = (float) (character.y / TEXTURE_SIZE);
				
				positions[i * 18 + 6] = xCursor + character.xoffset + character.width;
				positions[i * 18 + 7] = character.yoffset + character.height;
				
				textures[i * 12 + 4] = (float) ((character.x + character.width) / TEXTURE_SIZE);
				textures[i * 12 + 5] = (float) ((character.y + character.height) / TEXTURE_SIZE);
				
				positions[i * 18 + 9] = xCursor + character.xoffset;
				positions[i * 18 + 10] = character.yoffset;
				
				textures[i * 12 + 6] = (float) (character.x / TEXTURE_SIZE);
				textures[i * 12 + 7] = (float) (character.y / TEXTURE_SIZE);
				
				positions[i * 18 + 12] = xCursor + character.xoffset + character.width;
				positions[i * 18 + 13] = character.yoffset + character.height;
				
				textures[i * 12 + 8] = (float) ((character.x + character.width) / TEXTURE_SIZE);
				textures[i * 12 + 9] = (float) ((character.y + character.height) / TEXTURE_SIZE);
				
				positions[i * 18 + 15] = xCursor + character.xoffset;
				positions[i * 18 + 16] = character.yoffset + character.height;
				
				textures[i * 12 + 10] = (float) ((character.x) / TEXTURE_SIZE);
				textures[i * 12 + 11] = (float) ((character.y + character.height) / TEXTURE_SIZE);
			
				xCursor += character.xadvance - 12;
		    }
		    
		}
		
		float highestY = -10000;
		float lowestY = 10000;
		
		for (int j = 1; j < positions.length; j+= 3) {
			if(positions[j]> highestY) highestY = positions[j];
			if(positions[j]< lowestY) lowestY = positions[j];
		}
		
//		float yDiff = highestY -lowestY;
		
		float scale = (float) (1.0 / (xCursor>highestY? (xCursor> width? xCursor : width) : (highestY> width? xCursor : width))); 
		
		
		for (int j = 0; j < positions.length; j+= 3) {
			positions[j + 0] *= scale; 
			positions[j + 1] *= scale; 
			positions[j + 1] = 1- positions[j + 1]; 
		}

		System.out.println("textures");
		for (int j = 1; j < textures.length; j+= 2) {
			textures[j] = 1- textures[j];
		}
		
		return new TextModel(x, y, width, height, 1, new Model(positions, textures, 1), font.textureID);
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
}
