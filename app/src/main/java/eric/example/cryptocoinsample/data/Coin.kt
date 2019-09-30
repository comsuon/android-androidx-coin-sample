package eric.example.cryptocoinsample.data

import eric.example.cryptocoinsample.coin_list.COIN_URL
import eric.example.cryptocoinsample.coin_list.COIN_URL_SUFFIX

data class Coin(val id: Int, val name: String, val symbol: String, val quote: Quote) {
    val url: String
        get() = COIN_URL + id + COIN_URL_SUFFIX

    class Quote(val USD: Currency) {
        class Currency(val price: String)
    }
}