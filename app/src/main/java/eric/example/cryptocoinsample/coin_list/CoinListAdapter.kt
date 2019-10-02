package eric.example.cryptocoinsample.coin_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import eric.example.cryptocoinsample.BR
import eric.example.cryptocoinsample.R
import eric.example.cryptocoinsample.data.Coin
import eric.example.cryptocoinsample.utils.CoinDiffUtils

const val COIN_URL = "https://s2.coinmarketcap.com/static/img/coins/64x64/"
const val COIN_URL_SUFFIX = ".png"

class CoinListAdapter : PagedListAdapter<Coin, CoinListAdapter.CoinViewHolder>(CoinDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_coin_list_item, parent, false)

        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindView(item)
    }


    class CoinViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Coin?) {
            binding.setVariable(BR.data, item)
            binding.executePendingBindings()
        }
    }
}

