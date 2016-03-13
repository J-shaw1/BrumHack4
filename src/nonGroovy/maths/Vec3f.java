package nonGroovy.maths;

public class Vec3f {

	public float x;
	public float y;
	public float z;
	
	
	public Vec3f(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3f(float f){
		this.x = f;
		this.y = f;
		this.z = f;
	}
	
	public Vec3f(){
		this(1);
	}

	public void set(float r, float g, float b) {
		this.x = r;
		this.y = g;
		this.z = b;
	}
	
}
