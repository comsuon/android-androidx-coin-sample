package eric.example.cryptocoinsample.coin_details

import eric.example.cryptocoinsample.R
import eric.example.cryptocoinsample.base_components.BaseFragment

class CoinDetailsFragment: BaseFragment() {
    override fun getScreenTitle(): String = "Coin details"

    override fun getLayout(): Int = R.layout.fragment_coin_detail

    override fun onInit() {
    }
}