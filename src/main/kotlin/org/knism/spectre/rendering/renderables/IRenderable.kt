package org.knism.spectre.rendering.renderables

import org.knism.spectre.app.objects.components.SpectreComponent
import org.knism.spectre.rendering.engine.RenderingEngine

/**
 * Generic [SpectreComponent] providing ability to render self with
 * given [RenderingEngine]
 *
 * @param T: RenderingEngine
 */
interface IRenderable<T : RenderingEngine> : SpectreComponent {
    fun render()
}