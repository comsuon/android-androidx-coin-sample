package eric.example.cryptocoinsample.coin_list

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import eric.example.cryptocoinsample.R
import eric.example.cryptocoinsample.base_components.BaseFragment
import eric.example.cryptocoinsample.base_components.DataWrapper
import eric.example.cryptocoinsample.base_components.ListDivider
import eric.example.cryptocoinsample.utils.showToast
import kotlinx.android.synthetic.main.fragment_coin_list.*

class CoinListFragment : BaseFragment() {

    private val coinListViewModel by lazy {
        ViewModelProviders.of(this, CoinListViewModelFactory(CoinRepository()))
            .get(CoinListViewModel::class.java)
    }

    private val adapter by lazy { CoinListAdapter() }

    private val coinListStateObserver: Observer<DataWrapper<CoinListResponse>> by lazy {
        Observer<DataWrapper<CoinListResponse>> { data ->
            Log.v("COIN_LIST", data.status.toString())
            when (data.status) {
                DataWrapper.Status.SUCCESS -> {
                    swipe_refresh_layout.isRefreshing = false
                    val body = data.data
                    body?.let { response ->
                        rv_coin_list.smoothScrollToPosition(0)
                        val coinList = response.coinList

                        adapter.submitList(coinList)
                    }
                }
                DataWrapper.Status.ERROR -> {
                    swipe_refresh_layout.isRefreshing = false
                    Log.v("COIN_LIST", data.message)
                    this.activity?.showToast(data.message)
                }
                DataWrapper.Status.LOADING -> {
                    swipe_refresh_layout.isRefreshing = true
                }
            }
        }
    }

    override fun getLayout(): Int = R.layout.fragment_coin_list

    override fun getScreenTitle(): String = "Coin List"

    override fun onInit() {
        swipe_refresh_layout.setColorSchemeColors(Color.BLUE, Color.CYAN, Color.YELLOW, Color.BLACK)
        rv_coin_list.layoutManager = LinearLayoutManager(context)
        rv_coin_list.addItemDecoration(
            ListDivider(
                context!!
            )
        )
        rv_coin_list.adapter = adapter

        coinListViewModel.coinList.observe(viewLifecycleOwner, coinListStateObserver)

        swipe_refresh_layout.setOnRefreshListener { coinListViewModel.refreshData() }
    }

    override fun onDestroy() {
        super.onDestroy()
        coinListViewModel.coinList.removeObserver(coinListStateObserver)
    }
}