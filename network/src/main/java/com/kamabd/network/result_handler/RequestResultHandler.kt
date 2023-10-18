package com.kamabd.network.result_handler

import com.kamabd.base.coroutines.RequestResult
import com.kamabd.base.transformable.Transformable
import kotlinx.coroutines.CoroutineDispatcher

interface RequestResultHandler {

    suspend fun <R : Any> call(
        action: suspend () -> R
    ): RequestResult<R>

    suspend fun <R : Any> call(
        dispatcher: CoroutineDispatcher,
        action: suspend () -> R
    ): RequestResult<R>

    suspend fun <R, D> callAndMap(
        action: suspend () -> R
    ): RequestResult<D> where R : Transformable<D>
}