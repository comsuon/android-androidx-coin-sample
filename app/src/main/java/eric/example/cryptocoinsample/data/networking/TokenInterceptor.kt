package eric.example.cryptocoinsample.data.networking

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accepts", "application/json")
            .addHeader("X-CMC_PRO_API_KEY", CoinService.API_KEY).build()

        return chain.proceed(request)
    }

}