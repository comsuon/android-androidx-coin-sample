package eric.example.cryptocoinsample.coin_list

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import eric.example.cryptocoinsample.data.Coin
import eric.example.cryptocoinsample.data.CoinRepository
import kotlinx.coroutines.CoroutineScope

class CoinPagedDataSourceFactory(val repo: CoinRepository, val scope: CoroutineScope) :
    DataSource.Factory<Int, Coin>() {

    val coinDataSource = MutableLiveData<CoinPagedDataSource>()

    override fun create(): DataSource<Int, Coin> {
        val newDataSource = CoinPagedDataSource(repo, scope)
        coinDataSource.postValue(newDataSource)
        return newDataSource
    }
}