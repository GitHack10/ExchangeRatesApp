package com.kamabd.base.transformable

fun <T : Any, E> transform(item: E?): T? where E : Transformable<T> =
    item?.transform()

fun <T : Any, E> Collection<E>?.transformCollection(): List<T> where E : Transformable<T> {
    this ?: return emptyList()
    return map { it.transform() }
}