package org.knism.spectre.window

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.math.floor
import kotlin.math.round

class WindowTimer internal constructor(private val targetFps: Long) {


    private val initialTime = System.nanoTime()

    private val targetFrameTime: Long = floor(1_000_000_000.0 / targetFps).toLong()

    private var frameStartTime: Long = 0
    var delta: Long = 0
        private set
    private var accumulator: Long = 0

    private var framesCounter = 0

    fun startLoop() {
        delta = now() - frameStartTime
        accumulator += delta
        frameStartTime = now()
    }

    fun shouldTriggerUpdate(): Boolean = accumulator >= targetFrameTime

    fun updated() {
        accumulator -= targetFrameTime
    }

    fun maySleep(): Boolean = (now() - frameStartTime) < targetFrameTime

    fun sleep() {
        runBlocking {
            delay(((targetFrameTime - (now() - frameStartTime)) / 1_000_000) / 16)
        }
    }

    fun rendered() {
        framesCounter++
    }

    fun fps() = round(framesCounter / ((now() - initialTime) / 1_000_000_000.0))

    private fun now() = System.nanoTime()

    fun uptime(): Long = (now() - initialTime)

}