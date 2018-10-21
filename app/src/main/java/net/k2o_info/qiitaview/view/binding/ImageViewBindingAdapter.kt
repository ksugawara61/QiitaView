package net.k2o_info.qiitaview.view.binding

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.widget.ImageView
import com.squareup.picasso.Picasso
import net.k2o_info.qiitaview.R

/**
 * イメージビュー用カスタムバインディング
 */
object ImageViewBindingAdapter {

    /**
     * URLから画像を読み込み
     *
     * @param view ビュー
     * @param url 画像のURL
     */
    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "error"])
    fun loadImage(view: ImageView, url: String?, error: Drawable) {
        val context = view.context
        if (url != null && !url.isEmpty()) {
            Picasso.with(context).load(url).placeholder(error).error(error).into(view)
        } else {
            view.setImageDrawable(error)
        }
    }
}