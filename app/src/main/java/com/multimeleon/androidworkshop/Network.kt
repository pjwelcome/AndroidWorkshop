package com.multimeleon.androidworkshop

import android.support.annotation.VisibleForTesting
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class Network(private val baseUrl: String, enableLog: Boolean) {


    private val sGson: Gson = GsonBuilder()
            .setLenient()
            .create()


    @VisibleForTesting
    internal val okHttpClient: OkHttpClient

    private val gsonConverterFactory = GsonConverterFactory.create(sGson)

    init {
        val httpClientBuilder = OkHttpClient.Builder()
                .readTimeout(NetworkConfig.READ_TIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(NetworkConfig.WRITE_TIMEOUT, TimeUnit.MINUTES)
                .connectTimeout(NetworkConfig.CONNECTION_TIMEOUT, TimeUnit.MINUTES)

        //Add debug interceptors
        if (enableLog) {
            httpClientBuilder.addInterceptor(HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY }
            )
        }

        okHttpClient = httpClientBuilder.build()
    }

    fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

}

internal object NetworkConfig {


    internal const val READ_TIMEOUT = 1L

    internal const val WRITE_TIMEOUT = 1L

    internal const val CONNECTION_TIMEOUT = 1L
}

internal interface Endpoint {

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("search/users")
    fun searchUser(
            @Query("q") query: String,
            @Query("sort") sort: String,
            @Query("order") order: String
    ): Call<SearchRepoResponse>


    @Headers("Accept: application/vnd.github.v3+json")
    @GET("search/repositories")
    fun searchRepo(
            @Query("q") query: String,
            @Query("sort") sort: String,
            @Query("order") order: String
    ): Call<SearchResponse>


}