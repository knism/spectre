package org.knism.spectre.rendering.shaders.features

/**
 * * provide information to create optimal shader for any object
 *
 * @property circle Boolean should object be rendered as circle clipping
 *     anything outside a certain radius
 * @property lightType LightType should the rendered object be rendered
 *     as light source? Which one?
 * @property sceneLights Pair<Set<LightType>, Int> set of light types in
 *     the scene and maximum count of lights
 * @property baseColor TextureType texturing method
 * @property metallic TextureType texturing method
 * @property specular TextureType texturing method
 * @property roughness TextureType texturing method
 * @property transmission TextureType texturing method
 * @property emission TextureType texturing method
 * @property emissionStrength TextureType texturing method
 * @property normal TextureType texturing method
 */
data class OpenGLShaderFeatures(

    val projection: Boolean = false,
    val view: Boolean = false,
    val model: Boolean = false,

    val circle: Boolean = false,
    val lightType: LightType = LightType.NONE,
    val sceneLights: Pair<Set<LightType>, Int> = setOf(LightType.NONE) to 0,
    val baseColor: TextureType = TextureType.NONE,
    val metallic: TextureType = TextureType.NONE,
    val specular: TextureType = TextureType.NONE,
    val roughness: TextureType = TextureType.NONE,
    val transmission: TextureType = TextureType.NONE,
    val emission: TextureType = TextureType.NONE,
    val emissionStrength: TextureType = TextureType.NONE,
    val normal: TextureType = TextureType.NONE,
    val gammaCorrection: Boolean = false,
    val shadowMap: Boolean = false,
    val hdr: Boolean = false,
    val bloom: Boolean = false,
    val ao: Boolean = false,
) {
    fun textured(): Collection<TextureType> =
        listOf(baseColor, metallic, specular, roughness, transmission, emission, emissionStrength, normal)
}