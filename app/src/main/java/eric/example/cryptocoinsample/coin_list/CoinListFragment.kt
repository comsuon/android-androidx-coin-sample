package eric.example.cryptocoinsample.coin_list

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

    override fun getLayout(): Int = R.layout.fragment_coin_list

    override fun getScreenTitle(): String = "Coin List"

    override fun onInit() {
        rv_coin_list.layoutManager = LinearLayoutManager(context)

        rv_coin_list.addItemDecoration(
            ListDivider(
                context!!
            )
        )

        rv_coin_list.adapter = adapter


        coinListViewModel.coinList.observe(viewLifecycleOwner, Observer { data ->
            Log.v("COIN_LIST", data.status.toString())
            when (data.status) {
                DataWrapper.Status.SUCCESS -> {
                    val body = data.data
                    body?.let { response ->
                        val coinList = response.coinList ?: listOf()

                        adapter.submitList(coinList)
                    }
                }
                DataWrapper.Status.ERROR -> {
                    Log.v("COIN_LIST", data.message)
                    this.activity?.showToast(data.message)
                }
            }
        })
    }

}