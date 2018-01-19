varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;

void main()
{
    vec4 color = texture2D(u_texture, v_texCoords);
	float average = (color.r + color.g + color.b) / 3.0;
	gl_FragColor = vec4(average, average, average, color.a);
}