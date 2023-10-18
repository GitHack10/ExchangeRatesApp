package com.kamabd.exchangeratesapp.di

import com.google.gson.Gson
import com.kamabd.base.coroutines.DefaultDispatchersProvider
import com.kamabd.exchangeratesapp.BuildConfig
import com.kamabd.exchangeratesapp.data.remote.ApiKeyInterceptor
import com.kamabd.i_currencies.api.ApiCurrencies
import com.kamabd.network.error_mapper.DefaultErrorMapper
import com.kamabd.network.result_handler.RequestResultHandler
import com.kamabd.network.result_handler.RequestResultHandlerDelegate
import com.kamabd.network.result_handler.RequestResultHandlerDelegateDependencies
import com.kamabd.network.use_case.DefaultRequestDataDelegate
import com.kamabd.network.use_case.RequestDataDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseURL(): String {
        return BuildConfig.BaseApiUrl
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .callTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(ApiKeyInterceptor(BuildConfig.ApiKey))
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiCurrencies(retrofit: Retrofit): ApiCurrencies {
        return retrofit.create(ApiCurrencies::class.java)
    }

    @Singleton
    @Provides
    fun provideRequestResultHandlerDelegateDependencies(): RequestResultHandlerDelegateDependencies =
        RequestResultHandlerDelegateDependencies(
            DefaultDispatchersProvider(),
            DefaultErrorMapper(),
            Gson()
        )

    @Singleton
    @Provides
    fun provideRequestResultHandler(
        dependencies: RequestResultHandlerDelegateDependencies
    ): RequestResultHandler = RequestResultHandlerDelegate(
        dependencies
    )

    @Singleton
    @Provides
    fun provideRequestDataDelegate(): RequestDataDelegate = DefaultRequestDataDelegate()
}