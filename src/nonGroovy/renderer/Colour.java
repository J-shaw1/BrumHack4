package nonGroovy.renderer;

public class Colour {
	
	public float r;
	public float g;
	public float b;
	
	public Colour(){
		this(1, 0.5f, 1);
	}
	
	public Colour(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Colour(int r, int g, int b){
		this.r = r/255f;
		this.g = g/255f;
		this.b = b/255f;
	}
	
}
