package org.knism.spectre.rendering.renderer

import org.knism.spectre.app.objects.rendering.IRenderable
import org.knism.spectre.rendering.engine.RenderingEngine

interface IRenderer<T : RenderingEngine> {
    fun render(renderable: IRenderable<T>)
}