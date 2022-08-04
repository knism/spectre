package org.knism.spectre.app

import org.knism.spectre.Spectre
import org.knism.spectre.app.scene.Scene
import org.knism.spectre.core.UID

/** abstract class providing handles for an application */
abstract class App {

    private val scenes: MutableMap<UID, Scene> = mutableMapOf()
    private lateinit var activeScene: Scene

    fun activateScene(scene: UID) {
        activeScene = scenes[scene] ?: scenes.values.first()
        Spectre.di.provide(activeScene)
    }

    fun addScene(scene: Scene) {
        scenes[scene.uid] = scene
    }

    fun _init() {
        init()
    }

    abstract fun init()

    fun update() {
        activeScene.update()
    }

    fun render() {
        activeScene.render()
    }

    fun close() {
        // TODO
    }
}