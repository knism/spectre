package org.knism.spectre.rendering.camera

import org.knism.spectre.app.SpectreObject
import org.knism.spectre.core.SpectreType
import org.knism.spectre.rendering.engine.OpenGL

class OpenGLCamera : SpectreCamera<OpenGL>, SpectreObject(SpectreType.CAMERA) {
    override fun buffer() {
        TODO("Not yet implemented")
    }
}