package org.knism.spectre.core.base

import org.knism.spectre.core.UID

/**
 * Simple interface providing ability to identify object by unique
 * [ID][UID] token
 */
interface Identifiable {
    val uid: UID
}