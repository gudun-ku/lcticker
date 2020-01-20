package com.beloushkin.lcticker.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.beloushkin.lcticker.R
import com.beloushkin.lcticker.data.models.CoinInfo
import com.beloushkin.lcticker.extensions.loadNetworkImage
import com.beloushkin.lcticker.utils.CryptoIconUrl
import com.beloushkin.lcticker.utils.Style
import com.beloushkin.lcticker.views.InfoListDirections

class InfoListAdapter: RecyclerView.Adapter<InfoListAdapter.ViewHolder>() {

    private val mInfoList: MutableList<CoinInfo> = mutableListOf()

    fun setData(newInfo: List<CoinInfo>) {
        mInfoList.clear()
        mInfoList.addAll(newInfo)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mInfoList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mInfoList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val txtCoinName = itemView.findViewById<TextView>(R.id.tvCoinName)
        private val txtWalletStatus = itemView.findViewById<TextView>(R.id.tvWalletStatus)
        private val imgIcon = itemView.findViewById<ImageView>(R.id.imvCoinIcon)

        fun bind(model: CoinInfo) {
            txtCoinName.text = model.name
            txtWalletStatus.text = model.walletStatus
            val imgUrl = CryptoIconUrl.Builder()
                                .code(model.symbol)
                                .style(Style.color)
                                .size(48)
                                .build()
                                .Url


            imgIcon.loadNetworkImage(imgUrl)

            itemView.setOnClickListener {
                val action = InfoListDirections.actionCoinDetail()
                action.symbol = model.symbol
                Navigation.findNavController(itemView).navigate(action)
            }
        }
    }
}
