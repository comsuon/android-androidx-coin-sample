package eric.example.cryptocoinsample.coin_list

import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import eric.example.cryptocoinsample.MainApplication
import eric.example.cryptocoinsample.R
import eric.example.cryptocoinsample.base_components.BaseFragment
import eric.example.cryptocoinsample.base_components.DataWrapper
import eric.example.cryptocoinsample.base_components.ListDivider
import eric.example.cryptocoinsample.data.CoinRepository
import eric.example.cryptocoinsample.utils.showToast
import kotlinx.android.synthetic.main.fragment_coin_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinListFragment : BaseFragment() {
    val uiScope = CoroutineScope(Dispatchers.Main)
    val bgScope = CoroutineScope(Dispatchers.Default)

    private val coinListViewModel by lazy {
        ViewModelProviders.of(this, CoinListViewModelFactory(CoinRepository()))
            .get(CoinListViewModel::class.java)
    }

    private val adapter by lazy { CoinListAdapter() }


    override fun getLayout(): Int = R.layout.fragment_coin_list

    override fun getScreenTitle(): String = "Coin List"

    override fun onInit() {
        swipe_refresh_layout.setColorSchemeColors(Color.BLUE, Color.CYAN, Color.YELLOW, Color.BLACK)

        initCoinList()

        coinListViewModel.coinList.observe(viewLifecycleOwner, Observer { list ->
            adapter.submitList(list)
        })

        coinListViewModel.getState().observe(viewLifecycleOwner, Observer {
            when (it) {
                DataWrapper.Status.LOADING -> if (!swipe_refresh_layout.isRefreshing)
                    swipe_refresh_layout.isRefreshing = true
                DataWrapper.Status.ERROR, DataWrapper.Status.SUCCESS -> swipe_refresh_layout.isRefreshing =
                    false
            }
        })

        coinListViewModel.getError().observe(viewLifecycleOwner, Observer {
            context!!.showToast(it)
        })

        swipe_refresh_layout.setOnRefreshListener { coinListViewModel.refreshData() }
    }

    private fun initCoinList() {
        rv_coin_list.layoutManager = LinearLayoutManager(context)
        rv_coin_list.addItemDecoration(
            ListDivider(
                context!!
            )
        )
        adapter.onItemClickListener = {

            uiScope.launch {
                val coin =
                    withContext(bgScope.coroutineContext) { getCoin(it.id.toString()) }
                context?.showToast(coin.quote.USD.price)
            }
        }

        rv_coin_list.adapter = adapter
    }

    private suspend fun getCoin(id: String) = MainApplication.INSTANCE.db.coinDao().getCoin(id)

    override fun onDestroy() {
        super.onDestroy()
    }
}