package eric.example.cryptocoinsample.coin_list

import androidx.lifecycle.ViewModel

class CoinListViewModel(coinRepository: CoinRepository) : ViewModel() {
    val coinList = coinRepository.fetchCoinList()
}