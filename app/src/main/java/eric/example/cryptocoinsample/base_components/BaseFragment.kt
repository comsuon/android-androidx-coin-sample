package eric.example.cryptocoinsample.base_components

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected lateinit var rootView: View

    abstract fun getScreenTitle(): String

    abstract fun getLayout(): Int

    abstract fun onInit()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(getLayout(), container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!TextUtils.isEmpty(getScreenTitle())) {
            (activity as AppCompatActivity).supportActionBar?.title = getScreenTitle()
        }
        onInit()
    }
}