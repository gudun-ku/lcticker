package com.beloushkin.lcticker.data.services


import com.beloushkin.lcticker.data.models.CoinInfo
import com.beloushkin.lcticker.data.models.TickerInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinInfoService {


    @HasRoot_Info
    @GET("info/coinInfo")
    suspend fun getCoinInfoAsync() : List<CoinInfo>

    @GET("exchange/ticker")
    suspend fun getCoinTickerInfoAsync(@Query("currencyPair") currencyPair: String) : TickerInfo

}