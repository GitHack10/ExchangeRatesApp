package com.kamabd.network.error_mapper

import com.kamabd.network.CommonApiError
import com.kamabd.network.errors.ApiError
import com.kamabd.network.errors.NoInternetError
import com.kamabd.network.errors.UnspecifiedError
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class DefaultErrorMapper : ErrorMapper {

    override fun mapError(error: Throwable): Throwable {
        return when (error) {
            is UnknownHostException,
            is SocketTimeoutException,
            is ConnectException -> NoInternetError()
            is CommonApiError -> {
                ApiError(
                    error.errorMessage,
                    429,
                )
            }
            is HttpException -> {
                ApiError(
                    error.localizedMessage ?: "",
                    error.code(),
                )
            }
            else -> UnspecifiedError(0)
        }
    }
}