package com.kamabd.base.coroutines

inline fun <T : Any?> wrapResult(block: () -> T): RequestResult<T> {
    runCatching {

    }
    return try {
        RequestResult.Success(block())
    } catch (ex: Throwable) {
        RequestResult.Error(ex)
    }
}

suspend fun <T : Any?, R : Any?> RequestResult<T>.map(mapper: suspend (T) -> R): RequestResult<R> =
    when (this) {
        is RequestResult.Error -> RequestResult.Error(error)
        is RequestResult.Success -> RequestResult.Success(mapper(data))
    }

fun <T : Any?> RequestResult<T>.resultOrDefault(default: T): T =
    when (this) {
        is RequestResult.Error -> default
        is RequestResult.Success -> data
    }