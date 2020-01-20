package com.beloushkin.lcticker.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beloushkin.lcticker.R
import com.beloushkin.lcticker.data.models.TickerInfo
import com.beloushkin.lcticker.extensions.loadNetworkImage
import com.beloushkin.lcticker.utils.CryptoIconUrl
import com.beloushkin.lcticker.utils.RetrofitFactory
import com.beloushkin.lcticker.utils.Style
import kotlinx.android.synthetic.main.fragment_coin_detail.*
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 */
class CoinDetail : Fragment() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val coinInfoService = RetrofitFactory.getCoinInfoService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            val symbol = CoinDetailArgs.fromBundle(args).symbol
            fetchInfo(symbol)
        }
    }

    private fun bind(symbol: String, data: TickerInfo) {

        llLoading.visibility = View.GONE
        container.visibility = View.VISIBLE
        llError.visibility = View.GONE

        val pairString = "${symbol.toUpperCase()}/USD"
        tvCoinName.text = pairString
        tvLastValue.text = data.last.toString()
        tvMaxBid.text = data.best_bid.toString()
        tvMinAsk.text = data.min_ask.toString()
        tvBestBid.text = data.best_bid.toString()
    }


    private fun fetchInfo(symbol: String) {

        llLoading.visibility = View.VISIBLE
        container.visibility = View.GONE
        llError.visibility = View.GONE
        coroutineScope.launch {
            try {
                val data =
                    coinInfoService.getCoinTickerInfoAsync("${symbol.toUpperCase()}/USD")
                withContext(Dispatchers.Main) {
                    bind(symbol, data)
                    delay(200L)
                    loadCoinImage(symbol)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    llLoading.visibility = View.GONE
                    container.visibility = View.GONE
                    llError.visibility = View.VISIBLE
                }

            }

        }

    }

    private fun loadCoinImage(symbol: String) {
        //txtWalletStatus.text = model.walletStatus
        val imgUrl = CryptoIconUrl.Builder()
            .code(symbol)
            .style(Style.color)
            .size(imvCoinIcon.height)
            .build()
            .Url

        imvCoinIcon.loadNetworkImage(imgUrl)
    }

}
