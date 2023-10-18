package com.kamabd.network.result_handler

import com.google.gson.Gson
import com.kamabd.base.coroutines.DispatchersProvider
import com.kamabd.network.error_mapper.ErrorMapper

class RequestResultHandlerDelegateDependencies(
    val dispatchersProvider: DispatchersProvider,
    val errorMapper: ErrorMapper,
    val gson: Gson
)