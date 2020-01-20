package com.beloushkin.lcticker.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.beloushkin.lcticker.R
import com.beloushkin.lcticker.data.models.CoinInfo
import com.beloushkin.lcticker.utils.RetrofitFactory
import com.beloushkin.lcticker.views.adapters.InfoListAdapter
import kotlinx.android.synthetic.main.fragment_info_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InfoList : Fragment() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val coinInfoService = RetrofitFactory.getCoinInfoService()
    private val infoListAdapter = InfoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        fetchInfo()
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(this.context)
        rvCoinInfo.layoutManager = layoutManager
        rvCoinInfo.adapter = infoListAdapter
    }

    private fun fetchInfo() {
        llLoading.visibility = View.VISIBLE
        rvCoinInfo.visibility = View.GONE

        coroutineScope.launch {
            try {
                val defData = coinInfoService.getCoinInfoAsync()
                val data: List<CoinInfo> = defData
                withContext(Dispatchers.Main) {
                    infoListAdapter.setData(data)
                    llLoading.visibility = View.GONE
                    llError.visibility = View.GONE
                    rvCoinInfo.visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    llLoading.visibility = View.GONE
                    rvCoinInfo.visibility = View.GONE
                    llError.visibility = View.VISIBLE
                }

            }

        }

    }

}
