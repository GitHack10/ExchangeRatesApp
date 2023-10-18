package com.kamabd.network.error_mapper

interface ErrorMapper {

    fun mapError(error: Throwable): Throwable
}