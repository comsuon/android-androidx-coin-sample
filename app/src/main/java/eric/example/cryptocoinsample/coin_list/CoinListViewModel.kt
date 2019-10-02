package eric.example.cryptocoinsample.coin_list

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import eric.example.cryptocoinsample.base_components.DataWrapper
import eric.example.cryptocoinsample.data.Coin
import eric.example.cryptocoinsample.data.CoinRepository

class CoinListViewModel(private val coinRepository: CoinRepository) : ViewModel() {
    var coinList: LiveData<PagedList<Coin>>
    private val coinPagedDataSourceFactory: CoinPagedDataSourceFactory =
        CoinPagedDataSourceFactory(
            coinRepository,
            viewModelScope
        )

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(CoinRepository.PAGE_SIZE)
            .setInitialLoadSizeHint(CoinRepository.PAGE_SIZE / 2)
            .setEnablePlaceholders(false)
            .build()

        coinList = LivePagedListBuilder<Int, Coin>(coinPagedDataSourceFactory, config).build()
    }

    fun getState(): LiveData<DataWrapper.Status> = Transformations.switchMap(coinPagedDataSourceFactory.coinDataSource, CoinPagedDataSource::state)

    fun getError(): LiveData<String> = Transformations.switchMap(coinPagedDataSourceFactory.coinDataSource, CoinPagedDataSource::error)

    fun refreshData() {
        coinPagedDataSourceFactory.coinDataSource.value?.invalidate()
    }
}