package nonGroovy.models;

import java.util.HashMap;

public class ModelGenerator {

	
	public static HashMap<String, Model> modelMap = new HashMap<>();
	
	public static Model square(){
		if (modelMap.containsKey("square")) {
			return modelMap.get("square");
		}
		float[] positions =  { -1f, 1f, 0f, -1f, -1f, 0f, 1f, -1f, 0f, 1f, -1f, 0f, 1f, 1f, 0f, -1f, 1f, 0f
		};
		
		float[] textureCoords =  { 0f, 1f, 0f, 0f, 1f, 0f, 1f, 0f, 1f, 1f, 0f, 1f
		};
		Model m = new Model(positions, textureCoords);
		modelMap.put("square", m);
		return m;
		
	}
	
	
	
	
	
}
