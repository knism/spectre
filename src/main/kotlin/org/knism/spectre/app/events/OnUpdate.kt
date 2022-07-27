package org.knism.spectre.app.events

/** [Event][OnEvent] invoked whenever window signals an update frame */
interface OnUpdate : OnEvent {
    fun onUpdate()
}