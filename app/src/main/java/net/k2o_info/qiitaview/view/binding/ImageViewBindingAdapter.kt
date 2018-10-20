package net.k2o_info.qiitaview.view.binding

import android.databinding.BindingAdapter
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
    @BindingAdapter(value = ["imageUrl"])
    fun loadImage(view: ImageView, url: String?) {
        val context = view.context
        val error = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_person_blue_grey_400_24dp, null)
        if (url != null && !url.isEmpty()) {
            Picasso.with(context).load(url).placeholder(error).error(error).into(view)
        } else {
            view.setImageDrawable(error)
        }
    }
}