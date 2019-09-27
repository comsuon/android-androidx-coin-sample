package eric.example.cryptocoinsample.base_components

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository {
    protected fun <T> getLiveData(networkCall: suspend () -> DataWrapper<T>): LiveData<DataWrapper<T>> =
        liveData(Dispatchers.IO) {
            emit(DataWrapper.loading<T>())

            val response = networkCall.invoke()
            if (response.status == DataWrapper.Status.SUCCESS) {
                emit(response)
            } else {
                emit(DataWrapper.error<T>(response.message ?: "Technical Error"))
            }
        }
}

