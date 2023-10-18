package com.kamabd.network.use_case

import com.kamabd.base.coroutines.RequestResult
import kotlinx.coroutines.flow.Flow

interface RequestDataDelegate {

    suspend fun <T : Any> makeRequest(
        requestType: RequestType,
        remoteSource: suspend () -> RequestResult<T>,
        localSource: suspend () -> RequestResult<T>,
        updateLocal: suspend (RequestResult<T>) -> Unit = {}
    ): Flow<RequestResult<RequestData<T>>>
}