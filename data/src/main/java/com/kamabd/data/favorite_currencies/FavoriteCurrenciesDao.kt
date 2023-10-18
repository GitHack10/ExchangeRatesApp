package com.kamabd.data.favorite_currencies

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCurrenciesDao {

    @Query("SELECT * FROM favorite_currencies")
    fun subscribeToFavoriteCurrencies(): Flow<List<FavoriteCurrencyDTO>>

    @Query("SELECT * FROM favorite_currencies")
    suspend fun getFavoriteCurrencies(): List<FavoriteCurrencyDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavoriteCurrencies(currencies: List<FavoriteCurrencyDTO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCurrency(currency: FavoriteCurrencyDTO)

    @Delete
    suspend fun deleteFavoriteCurrency(currency: FavoriteCurrencyDTO)
}