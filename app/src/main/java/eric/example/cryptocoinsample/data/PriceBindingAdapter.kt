package eric.example.cryptocoinsample.data

import android.text.TextUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("coinPrice")
fun TextView.setPrice(price: String) {
    if(!TextUtils.isEmpty(price)) {
        this.text = "$price $"
    }
}