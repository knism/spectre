package org.knism.spectre.app

import org.knism.spectre.app.objectProperties.Tag
import org.knism.spectre.app.objects.worldProperties.SpectreTransform
import org.knism.spectre.core.SpectreType
import org.knism.spectre.core.UID
import org.knism.spectre.core.base.Identifiable
import org.knism.spectre.core.base.Nameable

/**
 * Base of any object from Spectre Library.
 *
 * @constructor
 * @property uid [UID] unique identifier for each object
 * @property name [String] non-unique identifier for each object
 * @property tags [Collection] of [Tag]
 * @property transform [SpectreTransform] object's transformation
 *     relative to world space Spectre units
 */
abstract class SpectreObject(spectreType: SpectreType = SpectreType.UNKNOWN) : Identifiable, Nameable {
    override val uid: UID = UID.next(spectreType)
    override lateinit var name: String

    val tags: MutableCollection<Tag> = mutableListOf()
    val transform: SpectreTransform = SpectreTransform()
}