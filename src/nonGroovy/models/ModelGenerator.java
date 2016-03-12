package nonGroovy.models;

public class ModelGenerator {

	
	public static Model square(){
		
		float[] positions =  { -1f, 1f, 0f, -1f, -1f, 0f, 1f, -1f, 0f, 1f, -1f, 0f, 1f, 1f, 0f, -1f, 1f, 0f
		};
		
		float[] textureCoords =  { 0f, 1f, 0f, 0f, 1f, 0f, 1f, 0f, 1f, 1f, 0f, 1f
		};
		
		return new Model(positions, textureCoords);
		
	}
	
	
	
	
	
}
