package org.knism.spectre.rendering.renderables

import org.knism.spectre.Spectre
import org.knism.spectre.rendering.engine.DirectX
import org.knism.spectre.rendering.renderer.DirectXRenderer
import org.knism.spectre.rendering.shaders.shader.DirectXShader
import org.knism.spectre.window.viewport.Viewport

/**
 * [DirectX]'s implementation of [IRenderable] interface to render
 * objects in DirectX [Viewport]
 *
 * @property renderer DirectXRenderer
 */
class DirectXRenderable : IRenderable<DirectX> {

    private val renderer: DirectXRenderer = Spectre.di.get(DirectXRenderer::class)


    private lateinit var shader: DirectXShader

    fun setShader(shader: DirectXShader) {
        this.shader = shader
    }

    override fun render() {
        renderer.render(this)
    }
}