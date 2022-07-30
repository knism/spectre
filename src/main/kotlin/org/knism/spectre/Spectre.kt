package org.knism.spectre

import org.knism.spectre.app.App
import org.knism.spectre.di.ApplicationDI
import org.knism.spectre.window.Window

/** Core static object for holding app state independent data. */
object Spectre {
    val di: ApplicationDI = ApplicationDI()
    lateinit var window: Window
    lateinit var app: App
}