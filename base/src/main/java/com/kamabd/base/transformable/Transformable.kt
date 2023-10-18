package com.kamabd.base.transformable

interface Transformable<T : Any?> {

    fun transform(): T
}