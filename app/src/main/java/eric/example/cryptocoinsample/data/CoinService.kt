package eric.example.cryptocoinsample.data

import eric.example.cryptocoinsample.coin_list.CoinListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinService {
    companion object {
        const val BASE_URL = "https://pro-api.coinmarketcap.com/v1/"
        const val API_KEY = "3888227d-f120-430a-8e16-39e6fb841006"
    }

    @GET("cryptocurrency/listings/latest")
    suspend fun getCoinList(
        @Query("start") start: String,
        @Query("limit") limit: String,
        @Query("convert") convert: String
    ): Response<CoinListResponse>
}