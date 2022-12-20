package com.example.squads.network.accounts

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

private const val BASE_URL =
    //"https://squadsacceptancea01.azurewebsites.net/user/"
    "https://localhost:25153/user/"

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

data class Reservation (
    val userId: Int,
    val SessionId: Int,
)


interface AccountApiService {
    @GET("{userId}")
    fun getUserDetailsAsync(@Path("userId") userId: Int): Deferred<AccountDto>

    //put
    //https://localhost:25153/user/1/reserve_session

    /*{
        "Reservation" : {
        "UserId" : 1,
        "SessionId": 22
    }
    }
    */


    @PUT("{userId}/reserve_session")
    fun ReserveSession(@Body Reservation : Reservation ) : Deferred<AccountDto>
}

object AccountApi {
    val retrofitService : AccountApiService by lazy {
        retrofit.create(AccountApiService::class.java)
    }
}