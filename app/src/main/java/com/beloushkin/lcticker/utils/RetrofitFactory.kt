package com.beloushkin.lcticker.utils

import com.beloushkin.lcticker.data.services.CoinInfoService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    companion object {
        val baseUrl = "https://api.livecoin.net/"
        val gson =  GsonBuilder()
            .setLenient()
            .create()

        private fun getOkHttpInstance(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        private fun getRetrofitClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpInstance())
                .addConverterFactory(InfoConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        fun getCoinInfoService() = getRetrofitClient().create(CoinInfoService::class.java)

    }


}