package eric.example.cryptocoinsample.base_components

import retrofit2.Response

abstract class BaseRemoteDataSource {
    protected suspend fun <T> getResult(serviceCall: suspend () -> Response<T>): DataWrapper<T> {
        return try {
            val response = serviceCall.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return DataWrapper.success<T>(body)
                }
            }
            DataWrapper.error<T>(response.errorBody()?.string())
        } catch (e: Exception) {
            DataWrapper.error<T>(e.localizedMessage)
        }
    }
}