package org.knism.spectre.rendering.renderables

import org.knism.spectre.Spectre
import org.knism.spectre.rendering.engine.OpenGL
import org.knism.spectre.rendering.renderer.OpenGLRenderer
import org.knism.spectre.rendering.shaders.shader.OpenGLShader
import org.knism.spectre.window.viewport.Viewport

/**
 * [OpenGL]'s implementation of [IRenderable] interface to render
 * objects in OpenGL [Viewport]
 *
 * @property renderer OpenGLRenderer
 */
class OpenGLRenderable : IRenderable<OpenGL> {

    private val renderer: OpenGLRenderer = Spectre.di.get(OpenGLRenderer::class)

    private lateinit var shader: OpenGLShader

    fun setShader(shader: OpenGLShader) {
        this.shader = shader
    }

    override fun render() {
        renderer.render(this)
    }
}