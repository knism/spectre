package org.knism.spectre.core.units

import org.knism.spectre.window.Window
import org.knism.spectre.window.viewport.OpenGLViewport

/**
 * Size class used for [Window]/[OpenGLViewport] setup
 *
 * @constructor
 * @property width Int
 * @property height Int
 */
data class Size(val width: Int, val height: Int)