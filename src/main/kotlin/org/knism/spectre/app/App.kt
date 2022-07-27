package org.knism.spectre.app

/** abstract class providing handles for an application */
abstract class App {
    fun _init() {
        init()
    }

    abstract fun init()

    fun update() {

    }

    fun render() {

    }

    fun close() {
        // TODO
    }
}