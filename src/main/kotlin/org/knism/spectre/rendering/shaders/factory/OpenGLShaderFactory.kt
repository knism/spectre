package org.knism.spectre.rendering.shaders.factory

import org.knism.spectre.rendering.shaders.features.OpenGLShaderFeatures
import org.knism.spectre.rendering.shaders.features.TextureType
import org.knism.spectre.rendering.shaders.shader.OpenGLShader

class OpenGLShaderFactory {
    fun createShader(features: OpenGLShaderFeatures): OpenGLShader {
        val anyTextured = features.textured().any { it == TextureType.TEXTURE }

        val vertex = """
            #version 330 core
            
            layout (location = 0) in vec3 vector_position;
            ${"layout(location = 1) in vec2 aTexCoord;".by(anyTextured)}
            
            ${"uniform mat4 model;".by(features.model)}
            ${"uniform mat4 view;".by(features.view)}
            ${"uniform mat4 projection;".by(features.projection)}
            
            ${"out vec2 TexCoord;".by(anyTextured)}
            
            out vec3 FragPos;
            
            void main() {
                gl_Position = ${"projection * ".by(features.projection)}${"view * ".by(features.view)}${
            "model * ".by(
                features.model
            )
        }vec4(vector_position, 1.0);
                ${"TexCoord = aTexCoord;".by(anyTextured)}
                FragPos = vec3(${"model * ".by(features.model)}vector_position);
            }
            
        """.trimIndent()

        val fragment = """
            #version 330 core

            out vec4 FragColor;
            
            ${"in vec2 TexCoord;".by(anyTextured)}
            in vec3 FragPos;
            
            ${textureBy("base_color", features.baseColor)}
            ${textureBy("metallic", features.baseColor)}
            ${textureBy("specular", features.specular)}
            ${textureBy("roughness", features.roughness)}
            ${textureBy("transmission", features.transmission)}
            ${textureBy("emission", features.emission)}
            ${textureBy("emissionStrength", features.emissionStrength)}
            ${textureBy("normal", features.normal)}
            
            vec3[] light_colors = 
        """.trimIndent()
        return OpenGLShader.compile(vertex, fragment)
    }

    private fun textureBy(n: String, t: TextureType): String {
        return "uniform ${"sampler2D".by(t == TextureType.TEXTURE, "vec3")} n;".by(t != TextureType.NONE)
    }
}

private fun String.by(b: Boolean, o: String = ""): String = if (b) this else o