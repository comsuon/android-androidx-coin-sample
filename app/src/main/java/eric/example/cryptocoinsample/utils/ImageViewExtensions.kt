package eric.example.cryptocoinsample.utils

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(context: Context, url: String) {
    if (!TextUtils.isEmpty(url)) {
        Glide.with(context).load(url).into(this)
    }
}