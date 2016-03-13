package nonGroovy.window.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyInputCallback extends GLFWKeyCallback {

	public static boolean[] isKeyDown = new boolean[65536];
	
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if (key >= 0 &&  key < 65536) {
			System.out.println(key);
			isKeyDown[key] = action != GLFW.GLFW_RELEASE;
		}
	}


}
