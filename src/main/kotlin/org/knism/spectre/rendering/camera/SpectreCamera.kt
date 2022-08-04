package org.knism.spectre.rendering.camera

import org.knism.spectre.rendering.engine.RenderingEngine

/**
 * Spectre camera represents a vector3 in 3D space and it's rotation to
 * from where the scene should be rendered
 *
 * @param T: RenderingEngine
 */
interface SpectreCamera<T : RenderingEngine> {
    fun buffer()
}