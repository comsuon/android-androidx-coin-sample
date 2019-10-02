package eric.example.cryptocoinsample.data

import eric.example.cryptocoinsample.base_components.BaseRemoteDataSource
import eric.example.cryptocoinsample.data.CoinService

class CoinRemoteDataSource(val service: CoinService) : BaseRemoteDataSource() {

    suspend fun fetchCoinList(start: Int, limit: Int, convert: String) =
        getResult { service.getCoinList(start.toString(), limit.toString(), convert) }

}