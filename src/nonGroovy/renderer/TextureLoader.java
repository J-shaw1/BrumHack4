package nonGroovy.renderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader {

	private static HashMap<String, Integer> loadedTextures = new HashMap<>();
	
	
	public static int loadTexture(String textureName){
		int result = -1;
		if (loadedTextures.containsKey(textureName)) {
			return loadedTextures.get(textureName);
		}
		try {
			BufferedImage img = ImageIO.read(new File("res/textures/" + textureName));
			int width = img.getWidth();
			int height = img.getHeight();
			int[] pixels = new int[width * height];
			img.getRGB(0, 0, width, height, pixels, 0, width);
			
			int[] data = new int[width * height];
			for (int i = 0; i < width * height; i++) {
				int a = (pixels[i] & 0xff000000) >> 24;
				int r = (pixels[i] & 0xff0000) >> 16;
				int g = (pixels[i] & 0xff00) >> 8;
				int b = (pixels[i] & 0xff);
				
				data[i] = a << 24 | b << 16 | g << 8 | r;
			}
			
			result = glGenTextures();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, result);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, toIntBuffer(data));
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			
		} catch (IOException e) {
			System.err.println("Failed to load texture: " + textureName);
			e.printStackTrace();
			System.exit(0);
		}	
		loadedTextures.put(textureName, result);
		return result;
	}
	
	
	
	private static IntBuffer toIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

}
