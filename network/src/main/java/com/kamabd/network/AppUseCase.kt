package com.kamabd.network

import com.kamabd.base.coroutines.RequestResult
import kotlinx.coroutines.flow.Flow

interface AppBaseRunnable<I : Any?, R : Any?> {

    suspend fun run(input: I): R
}

interface AppUseCase<I : Any?, R : Any?> : AppBaseRunnable<I, R> {

    override suspend fun run(input: I): R
}

/**
 * For use cases with only 1 input params and output of type RequestResult<R>
 */
abstract class OneInputResultUseCase<I : Any?, R : Any?> : AppUseCase<I, RequestResult<R>> {

    abstract suspend operator fun invoke(input: I): RequestResult<R>

    override suspend fun run(input: I): RequestResult<R> = invoke(input)
}

/**
 * For use cases with only 1 input params and output of type flow of RequestResult<R>-s
 */
abstract class OneInputFlowResultUseCase<I : Any?, R : Any?> : AppUseCase<I, Flow<RequestResult<R>>> {

    abstract suspend operator fun invoke(input: I): Flow<RequestResult<R>>

    override suspend fun run(input: I): Flow<RequestResult<R>> = invoke(input)
}

/**
 * For use cases without any input params and output of type of RequestResult<R>
 */
abstract class NoInputsResultUseCase<R : Any?> : AppUseCase<Nothing, RequestResult<R>> {

    abstract suspend operator fun invoke(): RequestResult<R>

    override suspend fun run(input: Nothing): RequestResult<R> = invoke()
}

/**
 * For use cases without any input params and output of type flow of RequestResult<R>
 */
abstract class NoInputsFlowResultUseCase<R : Any?> : AppUseCase<Nothing, Flow<RequestResult<R>>> {

    abstract suspend operator fun invoke(): Flow<RequestResult<R>>

    override suspend fun run(input: Nothing): Flow<RequestResult<R>> = invoke()
}