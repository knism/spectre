package org.knism.spectre.window.viewport

import org.knism.spectre.app.App
import org.knism.spectre.core.units.Offset
import org.knism.spectre.core.units.Size
import org.knism.spectre.rendering.engine.OpenGL
import org.knism.spectre.window.WindowTimer
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL33
import kotlin.properties.Delegates

/**
 * [OpenGL][GL33] [Viewport] inside a window
 *
 * @constructor
 * @property size Size
 * @property offset Offset
 * @property windowRef Long
 */
class OpenGLViewport(
    private val size: Size,
    private val offset: Offset,
) : Viewport<OpenGL> {

    private var windowRef by Delegates.notNull<Long>()

    override fun set(windowRef: Long) {

        this.windowRef = windowRef

        GL.createCapabilities()
        GL33.glViewport(offset.x, offset.y, size.width, size.height)

        GL33.glEnable(GL33.GL_DEPTH_TEST)
        GL33.glDepthFunc(GL33.GL_LESS)

        GL33.glEnable(GL33.GL_BLEND)
        GL33.glBlendFunc(GL33.GL_SRC_ALPHA, GL33.GL_ONE_MINUS_SRC_ALPHA)

        GL33.glEnable(GL33.GL_MULTISAMPLE)
    }

    override fun frame(context: App, windowTimer: WindowTimer) {

        windowTimer.startLoop()

        GL33.glClearColor(0f, 0f, 0f, 1f)
        GL33.glClear(GL33.GL_COLOR_BUFFER_BIT or GL33.GL_DEPTH_BUFFER_BIT)

        while (windowTimer.shouldTriggerUpdate()) {
            context.update()
            windowTimer.updated()
        }

        context.render()
        windowTimer.rendered()

        while (windowTimer.maySleep()) windowTimer.sleep()
    }
}