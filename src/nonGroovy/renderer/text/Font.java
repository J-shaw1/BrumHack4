package nonGroovy.renderer.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import nonGroovy.renderer.TextureLoader;

public class Font {

	HashMap<Integer, Character> fontMap = new HashMap<>();
	
	public int textureID;
	
	public Font(String fontName){
		
		textureID = TextureLoader.loadTexture(fontName + ".png");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("res/font/" + fontName + ".fnt")));
			String line = null;
			while((line = reader.readLine()) != null){
				if (line.startsWith("char id")) {
					String[] lineParts = line.split("=");
					int charID = Integer.parseInt(lineParts[1].split(" ")[0]);
					fontMap.put(charID, new Character(charID, Integer.parseInt(lineParts[2].split(" ")[0]), Integer.parseInt(lineParts[3].split(" ")[0]), Integer.parseInt(lineParts[4].split(" ")[0]), Integer.parseInt(lineParts[5].split(" ")[0]), Integer.parseInt(lineParts[6].split(" ")[0]), Integer.parseInt(lineParts[7].split(" ")[0]), Integer.parseInt(lineParts[8].split(" ")[0])));
					System.out.println(fontMap.get(charID));
				}
			}
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public Character getCharacter(char character){
		return fontMap.get((int)character);
	}
	
	public static void main(String[] args) {
		Font font = new Font("verdana");
	}
	
	
}
