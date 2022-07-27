package org.knism.spectre.window

import org.knism.spectre.app.App
import org.knism.spectre.rendering.engine.RenderingEngine

interface Viewport<T : RenderingEngine> {
    fun set(windowRef: Long)
    fun frame(context: App, windowTimer: WindowTimer)
}