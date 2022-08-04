package org.knism.spectre.rendering.renderer

import org.knism.spectre.rendering.engine.RenderingEngine
import org.knism.spectre.rendering.renderables.IRenderable

interface IRenderer<T : RenderingEngine> {
    fun render(renderable: IRenderable<T>)
}