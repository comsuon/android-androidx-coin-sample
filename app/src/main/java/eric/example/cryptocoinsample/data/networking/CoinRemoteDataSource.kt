package eric.example.cryptocoinsample.data.networking

import eric.example.cryptocoinsample.base_components.BaseRemoteDataSource
import eric.example.cryptocoinsample.data.networking.CoinService

class CoinRemoteDataSource(val service: CoinService) : BaseRemoteDataSource() {

    suspend fun fetchCoinList(start: Int, limit: Int, convert: String) =
        getResult { service.getCoinList(start.toString(), limit.toString(), convert) }

}