varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;

uniform vec2  u_imageSize;
uniform int   u_blurRadius;

void main()
{
    vec4 color = texture2D(u_texture, v_texCoords);
	vec2 pixelToTextureCoords = 1 / u_imageSize;
	
	vec4 averageColor = vec4(0.0, 0.0, 0.0, 0.0);
	
	for (int dx = -u_blurRadius; dx <= u_blurRadius; dx++)
	{
		for (int dy = -u_blurRadius; dy <= u_blurRadius; dy++)
		{
			vec2 point = v_texCoords + vec2(dx,dy) * pixelToTextureCoords;
			averageColor += texture2D(u_texture, point);
		}
	}
	
	averageColor /= pow(2.0 * u_blurRadius + 1.0, 2.0);

	gl_FragColor = v_color * averageColor;
}