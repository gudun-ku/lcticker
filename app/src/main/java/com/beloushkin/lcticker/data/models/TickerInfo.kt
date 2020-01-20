package com.beloushkin.lcticker.data.models

data class TickerInfo(
    val cur: String,
    val symbol:String,
    val last: Double = 0.0,
    val high: Double = 0.0,
    val low: Double = 0.0,
    val volume: Double = 0.0,
    val vwap: Double = 0.0,
    val max_bid: Double = 0.0,
    val min_ask: Double = 0.0,
    val best_bid: Double = 0.0,
    val best_ask: Double = 0.0
)

