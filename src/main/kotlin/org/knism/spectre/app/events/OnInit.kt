package org.knism.spectre.app.events

/**
 * [Event][OnEvent] invoked on objects initialization just after objects
 * constructor and init field are resolved
 */
interface OnInit : OnEvent {
    fun onInit()
}