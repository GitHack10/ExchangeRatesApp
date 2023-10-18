package com.kamabd.data.favorite_currencies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_currencies")
data class FavoriteCurrencyDTO(
    @PrimaryKey
    val currencyCode: String,
    val currencyValue: Double,
)