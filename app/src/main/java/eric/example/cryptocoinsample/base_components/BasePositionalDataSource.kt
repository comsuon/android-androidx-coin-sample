package eric.example.cryptocoinsample.base_components

import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource

abstract class BasePositionalDataSource<T> : PositionalDataSource<T>(){
    var error = MutableLiveData<String>()

    var state = MutableLiveData<DataWrapper.Status>()

    protected fun postError(msg: String) {
        error.postValue(msg)
    }

    protected fun updateState(newState: DataWrapper.Status) {
        state.postValue(newState)
    }
}