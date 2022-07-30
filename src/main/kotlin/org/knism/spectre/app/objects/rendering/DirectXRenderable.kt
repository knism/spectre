package org.knism.spectre.app.objects.rendering

import org.knism.spectre.Spectre
import org.knism.spectre.rendering.engine.DirectX
import org.knism.spectre.rendering.renderer.DirectXRenderer
import org.knism.spectre.window.viewport.Viewport

/**
 * [DirectX]'s implementation of [IRenderable] interface to render
 * objects in DirectX [Viewport]
 *
 * @property renderer DirectXRenderer
 */
class DirectXRenderable : IRenderable<DirectX> {

    private val renderer: DirectXRenderer = Spectre.di.get(DirectXRenderer::class)

    override fun render() {
        TODO("Not yet implemented")
    }
}