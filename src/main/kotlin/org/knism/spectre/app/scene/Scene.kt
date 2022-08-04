package org.knism.spectre.app.scene

import org.knism.spectre.app.SpectreObject
import org.knism.spectre.app.events.OnUpdate
import org.knism.spectre.app.objects.GameObject
import org.knism.spectre.core.SpectreType
import org.knism.spectre.rendering.camera.SpectreCamera
import org.knism.spectre.rendering.renderables.IRenderable
import org.knism.spectre.window.Window

/**
 * Application scene/scree/view, independent entities, actions, threads.
 * Used to switch to different rendering contexts.
 *
 * @property spectreObjects Collection<SpectreObject> list of scene
 *     unique objects
 * @property camera SpectreCamera<*> scene camera, point and rotation
 *     from where the scene is rendered
 */
class Scene : SpectreObject(SpectreType.SCENE) {

    private val objects: MutableCollection<SpectreObject> = mutableListOf()
    val spectreObjects: Collection<SpectreObject>
        get() = objects.toList()

    lateinit var camera: SpectreCamera<*>
        private set

    /** called every update frame by the [Window] */
    fun update() {
        objects.filterIsInstance<GameObject>()
            .forEach {
                it.components.filterIsInstance<OnUpdate>().forEach(OnUpdate::onUpdate)
            }
    }

    /** called every render frame by the [Window] */
    fun render() {
        camera.buffer()
        objects.filterIsInstance<GameObject>()
            .forEach {
                it.getComponentsOf<IRenderable<*>>().forEach { it.render() }
            }
    }

    /** adds object to the objects collection */
    fun addObject(spectreObject: SpectreObject) = objects.add(spectreObject)

    /**
     * set scene camera
     *
     * accepts any [SpectreCamera]<Implementation>, but if different
     * from current rendering engine it will cause artifacts
     */
    fun setCamera(spectreCamera: SpectreCamera<*>) {
        camera = spectreCamera
    }
}