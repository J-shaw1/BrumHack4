package nonGroovy.window;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import nonGroovy.window.input.KeyInputCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

	public static final int HEIGHT = 720;
	public static final int WIDTH = 1080;
	
	public static final String WINDOW_TITLE = "We Need A Name!";
	
	private  GLFWKeyCallback keyCallback;
	
	long windowID;
	
	public Window(){
		 
		if ( glfwInit() != GLFW_TRUE )
	            throw new IllegalStateException("Unable to initialize GLFW");
	 
	        // Configure our window
	        glfwDefaultWindowHints();
	        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
	        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
	 
	        // Create the window
	        windowID = glfwCreateWindow(WIDTH, HEIGHT, WINDOW_TITLE, NULL, NULL);
	        if ( windowID == NULL )
	            throw new RuntimeException("Failed to create the GLFW window");
	        keyCallback = new KeyInputCallback();    
		glfwSetKeyCallback(windowID, keyCallback);
		
		glfwMakeContextCurrent(windowID);
		GL.createCapabilities();
		
	    glClearColor(0.5f, 0.7f, 0.9f,1);
		 
		glfwShowWindow(windowID);
	}

	public void pollEvents() {
		GLFW.glfwPollEvents();
	}
	
	public boolean shouldClose(){
		return glfwWindowShouldClose(windowID) == GL_TRUE || KeyInputCallback.isKeyDown[GLFW_KEY_ESCAPE];
	}
	
	public void setClearColour(float r, float g, float b){
		glClearColor(r, g, b, 1f);
	}
	
	public void swapBuffers(){
        glfwSwapBuffers(windowID);
	}
	
}
