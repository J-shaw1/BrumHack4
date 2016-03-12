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
		this.vertexCount = positions.length / 2;

		glBindVertexArray(vaoID);

		glBindVertexArray(0);
	}

	private void storeVertextAttributeData(int attributeLocation, float[] data, int sizeOfElement) {

		// Generate VBO
		int vboID = glGenBuffers();
		// Bind VBO
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		// Convert float Array to Buffer
		FloatBuffer positionsBuffer = toFloatBuffer(data);
		// Store Position Data in VBO
		glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);
		// Specify The Structure and Type of The Data in The VBO
		glVertexAttribPointer(attributeLocation, sizeOfElement, GL_FLOAT, false, 0, 0);
		// Unbind VBO
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	private IntBuffer toIntBuffer(int[] indices) {
		IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
		buffer.put(indices);
		buffer.flip();
		return buffer;
	}

	private FloatBuffer toFloatBuffer(float[] floatArray) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(floatArray.length);
		buffer.put(floatArray);
		buffer.flip();
		return buffer;
	}

}
