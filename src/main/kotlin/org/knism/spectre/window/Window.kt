package org.knism.spectre.window

import org.knism.spectre.app.App
import org.knism.spectre.core.units.Size
import org.knism.spectre.execptions.WindowCouldNotBeOpenedException
import org.knism.spectre.window.viewport.Viewport
import org.lwjgl.glfw.GLFW.*

/**
 * Application window
 *
 * @property size Initial window size
 * @property title OS handled window title
 * @property targetFPS Max FPS the window should render
 * @property viewport Instance of a [Viewport] that should be used to
 *     render application
 */
class Window internal constructor(
    val size: Size,
    val title: String,
    val targetFPS: Long,
    val viewport: Viewport<*>,
) {

    private val windowRef: Long

    init {
        glfwInit()
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
        glfwWindowHint(GLFW_SAMPLES, 16)
        windowRef = glfwCreateWindow(size.width, size.height, title, 0, 0)
        if (windowRef == 0L) {
            glfwTerminate()
            throw WindowCouldNotBeOpenedException("Window opened with id: $windowRef and was marked as unopened!")
        }
        glfwMakeContextCurrent(windowRef)

        viewport.set(windowRef)
    }

    fun start(context: App) {
        context._init()

        val windowTimer = WindowTimer(targetFPS)

        while (!glfwWindowShouldClose(windowRef)) {
            viewport.frame(context, windowTimer)
            glfwSwapBuffers(windowRef)
            glfwPollEvents()
        }

        context.close()
        glfwTerminate()
    }
}