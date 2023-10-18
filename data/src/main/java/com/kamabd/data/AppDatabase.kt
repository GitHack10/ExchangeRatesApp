package com.kamabd.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kamabd.data.favorite_currencies.FavoriteCurrencyDTO
import com.kamabd.data.favorite_currencies.FavoriteCurrenciesDao

@Database(
    entities = [FavoriteCurrencyDTO::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCurrenciesDao(): FavoriteCurrenciesDao
}