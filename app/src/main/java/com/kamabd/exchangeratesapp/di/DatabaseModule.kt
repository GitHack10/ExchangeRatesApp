package com.kamabd.exchangeratesapp.di

import android.app.Application
import androidx.room.Room
import com.kamabd.data.AppDatabase
import com.kamabd.data.favorite_currencies.FavoriteCurrenciesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "exchanger-database")
            .build()
    }

    @Provides
    fun provideFavoriteCurrenciesDao(appDatabase: AppDatabase): FavoriteCurrenciesDao {
        return appDatabase.favoriteCurrenciesDao()
    }
}