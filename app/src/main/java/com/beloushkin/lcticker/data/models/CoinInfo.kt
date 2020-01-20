package com.beloushkin.lcticker.data.models

data class CoinInfo(
    val  name: String,
    val symbol: String,
    val walletStatus: String,
    val withdrawFee: Double = 0.0,
    val minDepositAmount: Double = 0.0,
    val minWithdrawAmount: Double = 0.0
)