package org.knism.spectre.app.objects.components

import org.knism.spectre.Spectre
import org.knism.spectre.app.objects.GameObject
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

/**
 * instantiate any SpectreComponent by KClass<Ref> with passable
 * constructor arguments
 *
 * @param T <must be of [SpectreComponent]>
 * @param gameObject GameObject reference to parent gameObject
 * @return T instance of created component
 * @receiver KClass<T> component to instantiate
 */
internal fun <T : SpectreComponent> KClass<T>.instantiate(gameObject: GameObject): T {
    val params: Map<KParameter, Any> = this::primaryConstructor.get()!!.parameters.associateWith {
        when (it.type.jvmErasure) {
            GameObject::class -> gameObject

            else -> Spectre.di.get(it.type.jvmErasure)
        }
    }

    return this::primaryConstructor.get()!!.callBy(params)
}