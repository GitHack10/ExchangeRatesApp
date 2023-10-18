package com.kamabd.domain

data class CurrenciesConvertedInfo(
    val date: String,
    val result: Double,
    val info: ConvertedInfo,
    val query: QueryInfo,
)

data class ConvertedInfo(
    val rate: Double,
    val timestamp: Long,
)

data class QueryInfo(
    val amount: Double,
    val from: String,
    val to: String,
)