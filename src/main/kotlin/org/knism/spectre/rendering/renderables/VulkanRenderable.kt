package org.knism.spectre.rendering.renderables

import org.knism.spectre.Spectre
import org.knism.spectre.rendering.engine.Vulkan
import org.knism.spectre.rendering.renderer.VulkanRenderer
import org.knism.spectre.rendering.shaders.shader.VulkanShader
import org.knism.spectre.window.viewport.Viewport

/**
 * [Vulkan]'s implementation of [IRenderable] interface to render
 * objects in Vulkan [Viewport]
 *
 * @property renderer VulkanRenderer
 */
class VulkanRenderable : IRenderable<Vulkan> {

    private val renderer: VulkanRenderer = Spectre.di.get(VulkanRenderer::class)

    private lateinit var shader: VulkanShader

    fun setShader(shader: VulkanShader) {
        this.shader = shader
    }

    override fun render() {
        renderer.render(this)
    }
}