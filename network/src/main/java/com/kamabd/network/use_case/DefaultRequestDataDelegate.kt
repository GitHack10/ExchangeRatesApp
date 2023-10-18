package com.kamabd.network.use_case

import com.kamabd.base.coroutines.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class DefaultRequestDataDelegate : RequestDataDelegate {

    override suspend fun <T : Any> makeRequest(
        requestType: RequestType,
        remoteSource: suspend () -> RequestResult<T>,
        localSource: suspend () -> RequestResult<T>,
        updateLocal: suspend (RequestResult<T>) -> Unit
    ): Flow<RequestResult<RequestData<T>>> = flow {
        when (requestType) {
            is RequestType.CacheOnly -> mapToLocalRequestData(localSource())
            is RequestType.RemoteOnly -> {
                val remote = remoteSource()
                updateLocal(remote)
                mapToRemoteRequestData(remote)
            }
            is RequestType.CacheThenRemote -> {
                val local = localSource()
                mapToLocalRequestData(local)
                val remote = remoteSource()
                updateLocal(remote)
                mapToRemoteRequestData(remote)
            }
        }
    }

    private suspend fun <T : Any> FlowCollector<RequestResult<RequestData<T>>>.mapToRemoteRequestData(
        result: RequestResult<T>
    ) {
        mapToRequestData(result, RequestData.Companion::ofRemote)
    }

    private suspend fun <T : Any> FlowCollector<RequestResult<RequestData<T>>>.mapToLocalRequestData(
        result: RequestResult<T>
    ) {
        mapToRequestData(result, RequestData.Companion::ofLocal)
    }

    private suspend fun <T : Any> FlowCollector<RequestResult<RequestData<T>>>.mapToRequestData(
        result: RequestResult<T>,
        factory: (T) -> RequestData<T>
    ) {
        when (result) {
            is RequestResult.Success -> emit(RequestResult.Success(factory(result.data)))
            is RequestResult.Error -> emit(RequestResult.Error(result.error))
        }
    }
}