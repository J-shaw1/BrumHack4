varying vec4 vertColor;

in vec3 position;



void main(){
    gl_Position = gl_Vertex;
    
    vertColor = vec4(0.1, 0.3, 0.4, 1.0);
}