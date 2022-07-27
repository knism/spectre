package org.knism.spectre.core.base

/**
 * Simple interface providing ability to name objects to easily
 * recognise them. Does not provide any duplicate safety.
 */
interface Nameable {
    val name: String
}