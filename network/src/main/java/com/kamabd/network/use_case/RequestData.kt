package com.kamabd.network.use_case

data class RequestData<T : Any>(
    val data: T,
    val source: DataSource
) {

    companion object {

        fun <T : Any> ofLocal(data: T): RequestData<T> = RequestData(
            data = data,
            source = DataSource.Cache
        )

        fun <T : Any> ofRemote(data: T): RequestData<T> = RequestData(
            data = data,
            source = DataSource.Remote
        )
    }

    val isFromCache: Boolean
        get() = source.isFromCache
}