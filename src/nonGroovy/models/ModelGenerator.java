package nonGroovy.models;

public class ModelGenerator {

	
	public static Model square(){
		
		float[] positions =  { -0.5f, 0.5f, 0f, -0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, 0.5f, 0f, -0.5f, 0.5f, 0f
		};
		
		return new Model(positions);
		
	}
	
	
	
	
	
}
