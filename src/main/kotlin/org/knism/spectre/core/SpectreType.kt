package org.knism.spectre.core

import org.knism.spectre.app.SpectreObject

/** Enum containing all identifiable types of [SpectreObject]s */
enum class SpectreType {
    UNKNOWN,

    SCENE,

    ENTITY,
    PARTICLE,

    CAMERA,
    LIGHT

}