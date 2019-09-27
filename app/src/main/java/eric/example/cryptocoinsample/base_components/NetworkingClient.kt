package eric.example.cryptocoinsample.base_components

import eric.example.cryptocoinsample.data.CoinService
import eric.example.cryptocoinsample.data.TokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT: Long = 4 * 1000

object NetworkingClient {

    fun <T> getService(clazz: Class<T>): T {
        val okHttpClientBuilder = OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            callTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            addInterceptor(TokenInterceptor())
        }

        val retrofit = Retrofit.Builder().client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(CoinService.BASE_URL)
            .build()

        return retrofit.create(clazz)
    }
}