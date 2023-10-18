package com.kamabd.network.result_handler

import com.google.gson.annotations.SerializedName
import com.kamabd.base.coroutines.RequestResult
import com.kamabd.base.coroutines.map
import com.kamabd.base.coroutines.wrapResult
import com.kamabd.base.transformable.Transformable
import com.kamabd.network.CommonApiError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RequestResultHandlerDelegate(
    private val dependencies: RequestResultHandlerDelegateDependencies
) : RequestResultHandler {

    override suspend fun <R : Any> call(
        action: suspend () -> R
    ): RequestResult<R> =
        call(dependencies.dispatchersProvider.io(), action)

    override suspend fun <R : Any> call(
        dispatcher: CoroutineDispatcher,
        action: suspend () -> R
    ): RequestResult<R> = withContext(dispatcher) {
        return@withContext when (val result = wrapResult { action() }) {
            is RequestResult.Error -> handleError(result)
            is RequestResult.Success -> result
        }
    }

    override suspend fun <R : Transformable<D>, D> callAndMap(
        action: suspend () -> R
    ): RequestResult<D> = call(action)
        .map {
            it.transform()
        }

    private fun <D : Any> handleError(
        result: RequestResult.Error
    ): RequestResult<D> {
        val originalError = result.error
        val mappedError = if (originalError is HttpException) {
            originalError.toBaseApiError() ?: originalError
        } else {
            originalError
        }
        return RequestResult.Error(dependencies.errorMapper.mapError(mappedError))
    }

    private fun HttpException.toBaseApiError(): CommonApiError? {
        val errorString = response()?.errorBody()?.string() ?: ""
        val errorResponse = try {
            dependencies.gson.fromJson(errorString, ErrorResponse::class.java)
        } catch (ex: Throwable) {
            null
        } ?: return null
        val errorMessage = errorResponse.errorMessage ?: errorResponse.error
        if (!errorMessage.isNullOrBlank()) {
            return CommonApiError(
                errorMessage,
                code(),
            )
        }
        return null
    }

    private class ErrorResponse(
        @SerializedName("message")
        val errorMessage: String?,
        @SerializedName("error")
        val error: String?,
        @SerializedName("code")
        val errorCode: Int?,
    )
}