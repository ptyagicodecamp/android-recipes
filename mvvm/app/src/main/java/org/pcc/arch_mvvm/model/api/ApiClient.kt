package org.pcc.arch_mvvm.model.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.pcc.arch_mvvm.model.api.C.Companion.BASE_URL
import org.pcc.arch_mvvm.model.api.C.Companion.DEBUG
import org.pcc.arch_mvvm.model.api.C.Companion.REQ_TIMEOUT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {


    val instance: ApiService = Retrofit.Builder().run {
        val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .create()

        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create(gson))
        client(requestInterceptorClient())
        build()
    }.create(ApiService::class.java)


    private fun requestInterceptorClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        return if (DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()
        }

    }

}

class C {
    companion object{
        const val BASE_URL = "https://api.github.com/"
        const val REQ_TIMEOUT = 10
        const val DEBUG = true
    }
}