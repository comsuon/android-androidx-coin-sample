package eric.example.cryptocoinsample.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eric.example.cryptocoinsample.data.CoinRepository

class CoinListViewModelFactory(private val coinRepo: CoinRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CoinRepository::class.java).newInstance(coinRepo)
    }

}