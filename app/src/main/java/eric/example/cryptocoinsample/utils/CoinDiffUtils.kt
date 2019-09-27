package eric.example.cryptocoinsample.utils

import androidx.recyclerview.widget.DiffUtil
import eric.example.cryptocoinsample.data.Coin

class CoinDiffUtils : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean = newItem.id == oldItem.id

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean =
        (newItem.id == oldItem.id) and (newItem.quote.USD.price == oldItem.quote.USD.price)
}