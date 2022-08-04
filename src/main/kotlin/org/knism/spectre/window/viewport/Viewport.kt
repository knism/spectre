package org.knism.spectre.window.viewport

import org.knism.spectre.app.App
import org.knism.spectre.rendering.engine.RenderingEngine
import org.knism.spectre.window.WindowTimer

interface Viewport<T : RenderingEngine> {
    fun set(windowRef: Long)
    fun frame(context: App, windowTimer: WindowTimer)
}