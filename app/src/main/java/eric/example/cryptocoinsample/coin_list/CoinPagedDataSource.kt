package eric.example.cryptocoinsample.coin_list

import eric.example.cryptocoinsample.base_components.BasePositionalDataSource
import eric.example.cryptocoinsample.base_components.DataWrapper
import eric.example.cryptocoinsample.data.Coin
import eric.example.cryptocoinsample.data.CoinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinPagedDataSource(val coinRepo: CoinRepository, val scope: CoroutineScope) :
    BasePositionalDataSource<Coin>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Coin>) {
        updateState(DataWrapper.Status.LOADING)
        scope.launch(Dispatchers.IO) {

            val response = coinRepo.fetchCoinList(1, params.pageSize)

            if (response.status == DataWrapper.Status.ERROR) {
                updateState(DataWrapper.Status.ERROR)
                postError(response.message ?: "Technical error")
            } else {
                val data = response.data
                callback.onResult(data?.coinList ?: listOf(), 0)
                updateState(DataWrapper.Status.SUCCESS)
            }
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Coin>) {
        updateState(DataWrapper.Status.LOADING)
        scope.launch(Dispatchers.IO) {

            val response = coinRepo.fetchCoinList(params.startPosition, params.loadSize)

            if (response.status == DataWrapper.Status.ERROR) {
                updateState(DataWrapper.Status.ERROR)
                postError(response.message ?: "Technical error")
            } else {
                val data = response.data
                callback.onResult(data?.coinList ?: listOf())
                updateState(DataWrapper.Status.SUCCESS)
            }
        }
    }
}