package nonGroovy.renderer.shaders;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import nonGroovy.maths.Vec2f;
import nonGroovy.maths.Vec3f;
import nonGroovy.renderer.Colour;

public class TextureShader {

	int programID;
	
	public TextureShader(){
//		String vertexShaderFilepath = "/BrumHack4/src/nonGroovy/renderer/shaders/basic.frag";
//		String fragmentShaderFilepath = "/res/shaders/basic.frag";

		String vertexString = "#version 330\n"
				+ "layout (location = 0) in vec3 position;\n"
				+ "layout (location = 1) in vec3 normal;\n"
				+ "layout (location = 2) in vec2 texture;\n"
				+ "uniform float width;\n"
				+ "uniform float height;\n"
				+ "uniform float xPosition;\n"
				+ "uniform float yPosition;\n"
				+ "out vec2 textureCoord;\n"
				+ "\n"
				+ "void main(){\n"
				+ "    textureCoord = texture;\n"
				+ "    gl_Position = vec4(position,1);\n"
				+ "	   gl_Position.x = (gl_Position.x * (float(width)/1080.0)) + ((xPosition - 540)/540);\n"
				+ "	   gl_Position.y = gl_Position.y * (float(height) / 720.0) + ((yPosition - 360)/360);\n"
				+ "}";
		
		String fragString = ""
				+ "out vec4 FragColor;\n"
				+ "uniform vec3 colour;\n"
				+ "uniform sampler2D myTexture;\n"
				+ "in vec2 textureCoord;\n"
				+ ""
				+ "void main(){\n"
				+ "    FragColor = vec4(colour,1);\n"
				+ "	   FragColor = texture2D(myTexture, textureCoord);"
				+ "}";
		
		int vertexShaderID = loadShader(vertexString, GL_VERTEX_SHADER);
		int fragmentShaderID = loadShader(fragString, GL_FRAGMENT_SHADER);

		programID = glCreateProgram();
		glAttachShader(programID, vertexShaderID);
		glAttachShader(programID, fragmentShaderID);
		
		glLinkProgram(programID);
		glValidateProgram(programID);
		
		locationX = glGetUniformLocation(programID, "xPosition");
		locationY = glGetUniformLocation(programID, "yPosition");
		locationColour = glGetUniformLocation(programID, "colour");
		locationWidth = glGetUniformLocation(programID, "width");
		locationHeight = glGetUniformLocation(programID, "height");
		
		
	}

	private int locationColour;
	private int locationWidth;
	private int locationHeight;
	private int locationX;
	private int locationY;
	
	public void setColour(Colour c){
		loadVector3(locationColour, new Vec3f(c.r, c.g, c.b));
	}
	
	public void setWidth(int f){
		loadFloat(locationWidth, f);
	}
	
	public void setHeight(int f){
		loadFloat(locationHeight, f);
	}
	
	public void setX(int f){
		loadFloat(locationX, f);
	}
	
	public void setY(int f){
		loadFloat(locationY, f);
	}
	
	protected void loadVector3(int location, Vec3f vector) {
		glUniform3f(location, vector.x, vector.y, 1);
	}
	
	protected void loadVector2(int location, Vec2f vector) {
		glUniform3f(location, vector.x, vector.y, 1);
	}
	
	protected void loadFloat(int location, float f) {
		glUniform1f(location, f);
	}
	
	private int loadShader(String shaderDefinition, int shaderType) {
		int shaderID = 0;

		shaderID = glCreateShader(shaderType);
		glShaderSource(shaderID, shaderDefinition);
		glCompileShader(shaderID);
		int compileStatus = glGetShaderi(shaderID, GL_COMPILE_STATUS);
		
		if (compileStatus == GL_FALSE) {
			String compileLog;
			compileLog = glGetShaderInfoLog(shaderID);
			System.err.println(compileLog);
			System.exit(1);
		} else {
			System.out.println("Success");
		}

		return shaderID;
	}
	
	private String loadShaderFileToString(String filepath) {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		FileReader fr;

		try {
			fr = new FileReader(filepath);
			br = new BufferedReader(fr);
			String line;

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: " + filepath);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Exception Reading File: " + filepath);
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	public void enable() {
		glUseProgram(programID);
	}

	public void disable() {
		glUseProgram(0);
	}
	
	public static void main(String[] args) {
		TextureShader bs = new TextureShader();
	}
	
}
