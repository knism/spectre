package org.knism.spectre.app.objects.rendering

import org.knism.spectre.Spectre
import org.knism.spectre.rendering.engine.OpenGL
import org.knism.spectre.rendering.renderer.OpenGLRenderer
import org.knism.spectre.window.viewport.Viewport

/**
 * [OpenGL]'s implementation of [IRenderable] interface to render
 * objects in OpenGL [Viewport]
 *
 * @property renderer DirectXRenderer
 */
class OpenGLRenderable : IRenderable<OpenGL> {

    private val renderer: OpenGLRenderer = Spectre.di.get(OpenGLRenderer::class)

    override fun render() {
        TODO("Not yet implemented")
    }
}