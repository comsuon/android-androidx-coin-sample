package eric.example.cryptocoinsample.coin_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import eric.example.cryptocoinsample.base_components.DataWrapper

class CoinListViewModel(private val coinRepository: CoinRepository) : ViewModel() {
    private var _coinList: LiveData<DataWrapper<CoinListResponse>>
    val coinList = MediatorLiveData<DataWrapper<CoinListResponse>>()

    init {
        _coinList = coinRepository.fetchCoinList()
        coinList.addSource(_coinList) {
            coinList.value = it
        }
    }

    fun refreshData() {
        coinList.removeSource(_coinList)
        _coinList = coinRepository.fetchCoinList()
        coinList.addSource(_coinList) {
            coinList.value = it
        }
    }
}