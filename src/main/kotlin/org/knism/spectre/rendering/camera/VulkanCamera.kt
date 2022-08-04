package org.knism.spectre.rendering.camera

import org.knism.spectre.app.SpectreObject
import org.knism.spectre.core.SpectreType
import org.knism.spectre.rendering.engine.Vulkan

class VulkanCamera : SpectreCamera<Vulkan>, SpectreObject(SpectreType.CAMERA) {
    override fun buffer() {
        TODO("Not yet implemented")
    }
}