package eric.example.cryptocoinsample.coin_list

import com.google.gson.annotations.SerializedName
import eric.example.cryptocoinsample.data.Coin

class CoinListResponse(@SerializedName("data") val coinList: List<Coin>)