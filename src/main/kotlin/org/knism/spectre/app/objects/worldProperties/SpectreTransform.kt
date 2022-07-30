package org.knism.spectre.app.objects.worldProperties

import org.joml.Vector3f

/**
 * application unit transform, not bound to the on-screen pixels and
 * engine transforms
 *
 * @property translation Vector3f
 * @property rotation Vector3f
 * @property scale Vector3f
 */
class SpectreTransform {
    val translation = Vector3f()
    val rotation = Vector3f()
    val scale = Vector3f()
}