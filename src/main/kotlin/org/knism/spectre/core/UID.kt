package org.knism.spectre.core

import java.util.*

/** Spectre Unique Identifier based on Java [UUID] */
data class UID internal constructor(
    /** Java unique identifier field */
    private val uuid: UUID,
    /** Spectre type value */
    private val type: SpectreType,
) {

    override fun equals(other: Any?): Boolean = other is UID && other.uuid == this.uuid
    override fun hashCode(): Int = uuid.hashCode()
    override fun toString(): String = "<$type> $uuid"

    companion object {
        fun next(type: SpectreType) = UID(UUID.randomUUID(), type)
    }
}