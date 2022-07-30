package org.knism.spectre.app.scene

import org.knism.spectre.app.SpectreObject
import org.knism.spectre.core.SpectreType
import org.knism.spectre.rendering.engine.RenderingEngine

/**
 * Spectre camera represents a vector3 in 3D space and it's rotation to
 * from where the scene should be rendered
 *
 * @param T: RenderingEngine
 */
class SpectreCamera<T : RenderingEngine> : SpectreObject(SpectreType.CAMERA) {

    fun buffer() {
        TODO()
    }
}