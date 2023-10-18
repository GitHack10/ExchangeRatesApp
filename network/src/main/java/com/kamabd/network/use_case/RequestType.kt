package com.kamabd.network.use_case

sealed class RequestType {

    object CacheOnly : RequestType()

    object RemoteOnly : RequestType()

    object CacheThenRemote : RequestType()
}