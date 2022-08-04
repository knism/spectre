package org.knism.spectre.rendering.shaders.factory

import org.knism.spectre.rendering.shaders.features.LightType
import org.knism.spectre.rendering.shaders.features.OpenGLShaderFeatures
import org.knism.spectre.rendering.shaders.features.TextureType
import org.knism.spectre.rendering.shaders.shader.OpenGLShader

class OpenGLShaderFactory {
    fun createShader(features: OpenGLShaderFeatures): OpenGLShader {
        val anyTextured = features.textured().any { it == TextureType.TEXTURE }

        val vertex = """
            #version 330 core
            
            layout (location = 0) in vec3 vector_position;
            ${"layout(location = 1) in vec2 vector_tex_coord;".by(anyTextured)}
            ${"layout(location = 2) in vec3 vector_normal;".by(features.normal == TextureType.NONE)}
            
            ${"uniform mat4 model;".by(features.model)}
            ${"uniform mat4 view;".by(features.view)}
            ${"uniform mat4 projection;".by(features.projection)}
            
            ${"out vec2 TexCoord;".by(anyTextured)}
            
            out vec3 FragPos;
            ${"out vec3 Normal;".by(features.normal == TextureType.NONE)}

            void main() {
                gl_Position = ${"projection * ".by(features.projection)}${"view * ".by(features.view)}${
            "model * ".by(
                features.model
            )
        }vec4(vector_position, 1.0);
                ${"TexCoord = vector_tex_coord;".by(anyTextured)}
                ${"Normal = vector_normal;".by(features.normal == TextureType.NONE)}
                FragPos = vec3(${"model * ".by(features.model)}${"projection * ".by(features.projection)}vector_position);
            }
            
        """.trimIndent()

        val fragment = """           
            #version 330 core
            
            ${
            """
                struct Lamp {
                    int type;
                    vec3 position;
                    float strength;
                    vec3 color;
                    vec3 data;
                }
            """.trimIndent().by(features.sceneLights.second > 0)
        }

            out vec4 FragColor;
            
            ${"in vec2 TexCoord;".by(anyTextured)}
            in vec3 FragPos;
            ${"in vec3 Normal;".by(features.normal == TextureType.NONE)}
            
            ${textureBy("base_color", features.baseColor)}
            ${textureBy("metallic", features.baseColor)}
            ${textureBy("specular", features.specular)}
            ${textureBy("roughness", features.roughness)}
            ${textureBy("transmission", features.transmission)}
            ${textureBy("emission", features.emission)}
            ${textureBy("emissionStrength", features.emissionStrength)}
            ${textureBy("normal", features.normal)}
            
            ${"uniform Lamp[${features.sceneLights.second}] lights;".by(features.sceneLights.second > 0)}
            
            uniform vec3 ViewPos; 
            
            void main() {
            
                ${"if (distance(vec2(.5, .5), FragPos.xy) > .5) {discard;}".by(features.circle)}
            
                vec4 final_color = vec(0.0, 0.0, 0.0, 1.0);
                vec3 norm = ${if (features.normal == TextureType.TEXTURE) "normalize(vec3(texture(normal, TexCoord))" else if (features.normal == TextureType.COLOR) "normal" else "Normal"};
                
                ${
            """
                    for (int i = 0; i < ${features.sceneLights.second}; i++) {
                        vec3 result;
                        ${
                """
                            if (lights[i].type == -1) {result = vec3(0.0, 0.0, 0.0)}
                        """.by(features.sceneLights.first.contains(LightType.NONE)).trimIndent()
            }
                        ${
                """
                            if (lights[i].type == 0) {
                                vec3 light_dir = normalize(lights[i].position - FragPos);
                                float diff = max(dot(norm, light_dir), 0.0);
                                vec3 diffuse = diff * (lights[i].color * lights[i].strength);
                                vec3 view_dir = normalize(ViewPos - FragPos);
                                vec3 reflect_dir = reflect(-light_dir, norm);
                                float spec = pow(max(dot(view_dir, reflect_dir), 0.0), 8);
                                vec3 rgh = ${
                    when (features.normal) {
                        TextureType.TEXTURE -> "vec3(texture(roughness, TexCoord))"
                        TextureType.COLOR -> "roughness"
                        TextureType.NONE -> "vec3(.5, .5, .5)"
                    }
                }
                                vec3 sp = ${
                    when (features.normal) {
                        TextureType.TEXTURE -> "vec3(texture(specular, TexCoord))"
                        TextureType.COLOR -> "specular"
                        TextureType.NONE -> "vec3(.5, .5, .5)"
                    }
                }
                                vec3 base = ${
                    when (features.normal) {
                        TextureType.TEXTURE -> "vec3(texture(base_color, TexCoord))"
                        TextureType.COLOR -> "base_color"
                        TextureType.NONE -> "vec3(1.0, 1.0, 1.0)"
                    }
                }
                                vec3 spm = rgh * spec * lights[i].color * sp
                                result = diff * base + spm
                            }
                        """.by(features.sceneLights.first.contains(LightType.POINT)).trimIndent()
            }
                        ${
                """
                            if (lights[i].type == 1) {result = vec3(0.0, 0.0, 0.0)}
                        """.by(features.sceneLights.first.contains(LightType.CONE)).trimIndent()
            }
                        ${
                """
                            if (lights[i].type == 2) {result = vec3(0.0, 0.0, 0.0)}
                        """.by(features.sceneLights.first.contains(LightType.GLOBAL)).trimIndent()
            }
                        final_color = final_color + vec4(result, 1.0)
                    }
                """.trimIndent().by(features.sceneLights.second > 0)
        }
                 FragColor = final_color
            }
            
        """.trimIndent()
        return OpenGLShader.compile(vertex, fragment)
    }

    private fun textureBy(n: String, t: TextureType): String {
        return "uniform ${"sampler2D".by(t == TextureType.TEXTURE, "vec3")} n;".by(t != TextureType.NONE)
    }
}

private fun String.by(b: Boolean, o: String = ""): String = if (b) this else o