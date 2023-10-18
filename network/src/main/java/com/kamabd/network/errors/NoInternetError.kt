package com.kamabd.network.errors

class NoInternetError : Throwable()

class UnspecifiedError(
    val code: Int
) : Throwable()

class UnauthorizedError : Throwable()

class ApiError(
    val errorMessage: String,
    val errorCode: Int,
) : Throwable()