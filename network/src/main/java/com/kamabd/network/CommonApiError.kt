package com.kamabd.network

import com.google.gson.annotations.SerializedName

class CommonApiError(
    @SerializedName("message")
    val errorMessage: String,
    val code: Int? = null
) : Throwable()