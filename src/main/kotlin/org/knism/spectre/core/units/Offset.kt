package org.knism.spectre.core.units

/**
 * offset property used in [Viewport] initialization
 *
 * @constructor x and y offset
 * @property x offset alongside x (--) axis
 * @property y offset alongside y (|) axis
 */
data class Offset(val x: Int, val y: Int)