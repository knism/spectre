package org.knism.spectre.app.objects.rendering

import org.knism.spectre.Spectre
import org.knism.spectre.rendering.engine.Vulkan
import org.knism.spectre.rendering.renderer.VulkanRenderer
import org.knism.spectre.window.viewport.Viewport

/**
 * [Vulkan]'s implementation of [IRenderable] interface to render
 * objects in Vulkan [Viewport]
 *
 * @property renderer DirectXRenderer
 */
class VulkanRenderable : IRenderable<Vulkan> {

    private val renderer: VulkanRenderer = Spectre.di.get(VulkanRenderer::class)

    override fun render() {
        TODO("Not yet implemented")
    }
}