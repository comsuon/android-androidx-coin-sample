package eric.example.cryptocoinsample.coin_list

import eric.example.cryptocoinsample.base_components.BaseRepository
import eric.example.cryptocoinsample.base_components.NetworkingClient
import eric.example.cryptocoinsample.data.CoinService

class CoinRepository : BaseRepository() {
    private val remoteDataSource =
        CoinListRemoteDataSource(NetworkingClient.getService(CoinService::class.java))

    fun fetchCoinList(start: Int = 1, limit: Int = 50, convert: String = "USD") =
        getLiveData { remoteDataSource.fetchCoinList(start, limit, convert) }
}