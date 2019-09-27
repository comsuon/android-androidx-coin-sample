package eric.example.cryptocoinsample.coin_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eric.example.cryptocoinsample.R
import eric.example.cryptocoinsample.data.Coin
import eric.example.cryptocoinsample.utils.CoinDiffUtils
import eric.example.cryptocoinsample.utils.loadImage

const val COIN_URL = "https://s2.coinmarketcap.com/static/img/coins/64x64/"
const val COIN_URL_SUFFIX = ".png"

class CoinListAdapter : ListAdapter<Coin, CoinListAdapter.CoinViewHolder>(CoinDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_coin_list_item, parent, false)

        return CoinViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val item = getItem(position)
        holder?.bindView(item)
    }


    class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Coin) {
            itemView.findViewById<TextView>(R.id.tv_coin_name)?.apply {
                text = "${item.name} (${item.symbol})"
            }

            itemView.findViewById<TextView>(R.id.tv_coin_price)?.apply { text = "${item.quote.USD.price} $" }

            val imageView = itemView.findViewById<ImageView>(R.id.imv_coin_symbol)
            imageView?.loadImage(itemView.context, "$COIN_URL${item.id}$COIN_URL_SUFFIX")
        }
    }
}

