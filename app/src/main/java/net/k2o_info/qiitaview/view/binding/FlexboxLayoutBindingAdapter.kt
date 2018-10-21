package net.k2o_info.qiitaview.view.binding

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import com.google.android.flexbox.FlexboxLayout
import net.k2o_info.qiitaview.BR
import net.k2o_info.qiitaview.R
import net.k2o_info.qiitaview.databinding.ArticleTagItemBinding
import net.k2o_info.qiitaview.model.pojo.QiitaArticleTag

/**
 * FlexboxLayout用カスタムバインディング
 */
object FlexboxLayoutBindingAdapter {

    /**
     * タグリストをFlexboxに追加
     *
     * @param view ビュー
     * @param tagList タグリスト
     */
    @JvmStatic
    @BindingAdapter(value = ["tagList"])
    fun addTagList(view: FlexboxLayout, tagList: List<QiitaArticleTag>) {
        view.removeAllViews()  // RecyclerViewの再利用の影響でタグが増殖するため要素を初期化する
        val inflater: LayoutInflater = LayoutInflater.from(view.context)
        for (tag in tagList) {
            val tagViewBinding = DataBindingUtil.inflate<ArticleTagItemBinding>(inflater, R.layout.article_tag_item, null, false)
            tagViewBinding.setVariable(BR.tagName, tag.name)
            view.addView(tagViewBinding.root)
        }
    }

}