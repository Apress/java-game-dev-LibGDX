varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform float u_time;

void main()
{
    vec4 color = texture2D(u_texture, v_texCoords);
	
	float average = (color.r + color.g + color.b) / 3.0;
	vec4 grayscale = vec4(average, average, average, color.a);

	float value = (sin(6.0 * u_time) + 1.0) * 0.5;
	
	gl_FragColor = value * color + (1.0 - value) * grayscale;
}