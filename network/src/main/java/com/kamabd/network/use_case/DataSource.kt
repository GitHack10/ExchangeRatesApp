package com.kamabd.network.use_case

sealed class DataSource {

    object Cache : DataSource()

    object Remote : DataSource()
}

val DataSource.isFromCache: Boolean
    get() = this is DataSource.Cache