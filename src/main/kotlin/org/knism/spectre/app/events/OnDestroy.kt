package org.knism.spectre.app.events

/**
 * [Event][OnEvent] invoked when objects lifetime ends or is forcibly
 * destroyed
 */
interface OnDestroy : OnEvent {
    fun onDestroy()
}