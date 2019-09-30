package eric.example.cryptocoinsample.data

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, url: String) {
    Log.v("COIN_IMAGE", url)
    Glide.with(view.context).load(url).into(view)
}