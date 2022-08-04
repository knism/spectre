package org.knism.spectre.app.objects

import org.knism.spectre.app.SpectreObject
import org.knism.spectre.app.events.OnInit
import org.knism.spectre.app.objects.components.SpectreComponent
import org.knism.spectre.app.objects.components.instantiate
import org.knism.spectre.core.SpectreType
import kotlin.reflect.KClass

/**
 * TODO
 *
 * @property components MutableCollection<SpectreComponent>
 */
class GameObject : SpectreObject(SpectreType.ENTITY) {
    val components: MutableCollection<SpectreComponent> = mutableListOf()
        get() = field.toMutableList()

    fun <T : SpectreComponent> addComponent(component: KClass<T>) =
        component.instantiate(this).also { components.add(it); if (it is OnInit) it.onInit() }

    inline fun <reified T : SpectreComponent> getComponentOf(): T = components.filterIsInstance<T>().first()
    inline fun <reified T : SpectreComponent> getComponentsOf(): Collection<T> = components.filterIsInstance<T>()
}