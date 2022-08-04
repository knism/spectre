package org.knism.spectre.rendering.shaders.shader

import org.knism.spectre.rendering.engine.OpenGL
import org.lwjgl.opengl.GL33
import java.io.File

class OpenGLShader(vertex: String, fragment: String) : Shader<OpenGL> {


    private fun loadShader(path: String): String = File(path).readText()

    private val vertexId: Int = GL33.glCreateShader(GL33.GL_VERTEX_SHADER)
    private val fragmentId: Int = GL33.glCreateShader(GL33.GL_FRAGMENT_SHADER)

    private val shaderId: Int = GL33.glCreateProgram()

    init {
        GL33.glShaderSource(vertexId, loadShader(vertex))
        GL33.glCompileShader(vertexId)

        val logV = GL33.glGetShaderInfoLog(vertexId)

        GL33.glShaderSource(fragmentId, loadShader(fragment))
        GL33.glCompileShader(fragmentId)

        val logF = GL33.glGetShaderInfoLog(fragmentId)

        GL33.glAttachShader(shaderId, vertexId)
        GL33.glAttachShader(shaderId, fragmentId)
        GL33.glLinkProgram(shaderId)

        val prg = GL33.glGetProgramInfoLog(shaderId)

        GL33.glDeleteShader(vertexId)
        GL33.glDeleteShader(fragmentId)
    }

    companion object {
        fun compile(vertex: String, fragment: String): OpenGLShader {
            return OpenGLShader(vertex, fragment)
        }
    }
}