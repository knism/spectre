package org.knism.spectre.di

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * Application dependency injector for injecting references to provided
 * classes
 */
class ApplicationDI internal constructor() {

    val instances: MutableMap<KClass<*>, Any> = mutableMapOf()

    /**
     * function for delegate binding
     *
     * @param self X reference to parent class of delegated property
     * @param property KProperty<*> property to be delegated
     * @return T instance of property
     */
    inline operator fun <X, reified T> getValue(self: X, property: KProperty<*>): T {
        return tryGetSelfOrInterface(T::class)
    }

    /**
     * function for getting instance or interface implementation of
     * requested class
     *
     * @param kClass KClass<*> requested class
     * @return T instance of requested class
     */
    internal inline fun <reified T> get(kClass: KClass<*>): T {
        return tryGetSelfOrInterface(T::class)
    }

    /**
     * Provides an instance of a class
     *
     * @param instance T
     */
    inline fun <reified T : Any> provide(instance: T) {
        instances[T::class] = instance
    }

    /**
     * Tries to get the provided class and if not possible an
     * implementation/extension of it
     *
     * @param kClass KClass<*> requested class
     * @return T instance of self of extension
     */
    inline fun <reified T> tryGetSelfOrInterface(kClass: KClass<*>): T {
        return if (instances[T::class] != null) {
            instances[T::class]!! as T
        } else {
            instances.values.filterIsInstance<T>().first()
        }
    }
}