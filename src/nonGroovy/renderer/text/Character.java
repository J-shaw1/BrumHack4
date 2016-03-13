package nonGroovy.renderer.text;

public class Character {

	public int charID;
	public int x;
	public int y;
	public int width;
	public int height;
	public int xoffset;
	public int yoffset;
	public int xadvance;
	
	
	public Character(int charID, int x, int y, int width, int height, int xoffset, int yoffset, int xadvance) {
		this.charID = charID;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		this.xadvance = xadvance;
	}
	
	public String toString(){
		return charID + "   " + x + "   " + y;
	}
	
	
	
}
