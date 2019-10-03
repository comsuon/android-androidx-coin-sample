package eric.example.cryptocoinsample.data

import eric.example.cryptocoinsample.base_components.BaseRepository
import eric.example.cryptocoinsample.base_components.NetworkingClient
import eric.example.cryptocoinsample.data.networking.CoinRemoteDataSource
import eric.example.cryptocoinsample.data.networking.CoinService

class CoinRepository : BaseRepository() {
    companion object {
        val PAGE_SIZE = 50
    }

    private val remoteDataSource =
        CoinRemoteDataSource(
            NetworkingClient.getService(
                CoinService::class.java
            )
        )

    suspend fun fetchCoinList(start: Int = 1, limit: Int = 50, convert: String = "USD") =
        remoteDataSource.fetchCoinList(start, limit, convert)

}