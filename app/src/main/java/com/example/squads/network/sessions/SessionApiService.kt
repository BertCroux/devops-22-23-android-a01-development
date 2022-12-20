package com.example.squads.network.sessions

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL =
    //"https://squadsacceptancea01.azurewebsites.net/session/"
    "https://http://localhost:25153/session/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

private val client = OkHttpClient.Builder()
    .addInterceptor(logger)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface SessionApiService {
    @GET("currentWeek/{id}")
    fun getCurrentWeekSessionOverview(@Path("id") id: Int): Deferred<SessionDtoContainer>

    @GET("nextWeek/{id}")
    fun getNextWeekSessionOverview(@Path("id") id: Int): Deferred<SessionDtoContainer>

}

object SessionApi {
    val retrofitService : SessionApiService by lazy {
        retrofit.create(SessionApiService::class.java)
    }
}