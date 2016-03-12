package nonGroovy.models;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.opengl.GL20.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Model {

	private int vaoID;
	private int vertexCount;

	public Model(float[] positions) {
		
		this.vaoID = glGenVertexArrays();
		this.vertexCount = positions.length / 3;

		glBindVertexArray(vaoID);
		
		storeVertextAttributeData(0, positions);

		glBindVertexArray(0);
	}

	private void storeVertextAttributeData(int attributeLocation, float[] data) {

		// Generate VBO
		int vboID = glGenBuffers();
		// Bind VBO
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		// Convert float Array to Buffer
		FloatBuffer positionsBuffer = toFloatBuffer(data);
		// Store Position Data in VBO
		glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);
		// Specify The Structure and Type of The Data in The VBO
		glVertexAttribPointer(attributeLocation, 3, GL_FLOAT, false, 0, 0);
		// Unbind VBO
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public int getVaoID(){
		return this.vaoID;
	}
	
	public int getVertexCount(){
		return this.vertexCount;
	}

	private FloatBuffer toFloatBuffer(float[] floatArray) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(floatArray.length);
		buffer.put(floatArray);
		buffer.flip();
		return buffer;
	}

}
