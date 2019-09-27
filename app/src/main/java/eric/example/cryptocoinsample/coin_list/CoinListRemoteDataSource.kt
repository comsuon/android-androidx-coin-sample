package eric.example.cryptocoinsample.coin_list

import eric.example.cryptocoinsample.base_components.BaseRemoteDataSource
import eric.example.cryptocoinsample.data.CoinService

class CoinListRemoteDataSource(val service: CoinService) : BaseRemoteDataSource() {

    suspend fun fetchCoinList(start: Int, limit: Int, convert: String) =
        getResult { service.getCoinList(start.toString(), limit.toString(), convert) }

}