package net.k2o_info.qiitaview.view.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.k2o_info.qiitaview.BR
import net.k2o_info.qiitaview.R
import net.k2o_info.qiitaview.model.pojo.QiitaArticle
import net.k2o_info.qiitaview.model.pojo.QiitaTag
import timber.log.Timber

/**
 * タグリスト用アダプター
 *
 * @author katsuya
 * @since 1.1.0
 */
class TagRecyclerAdapter(context: Context/*, listener: ArticleRecyclerListener*/) : RecyclerView.Adapter<ViewHolder>() {

    /*interface ArticleRecyclerListener {
        fun onRecyclerClickedListener(view: View, article: QiitaArticle, position: Int)
    }*/

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    //private val listener: ArticleRecyclerListener = listener
    private var tagList: List<QiitaTag> = emptyList()

    /**
     * 新規ViewHolderが渡されたときに呼ばれる
     *
     * @param viewGroup ビューの要素
     * @param viewType ビューの種類
     * @return 新しいViewHolder
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.tag_item, viewGroup, false))
    }

    /**
     * 要素が表示されたときに呼ばれる
     *
     * @param viewHolder ビューホルダー
     * @param position 要素番号
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val tag = tagList.getOrNull(position)

        if (tag != null) {
            // 変数の格納
            viewHolder.getBinding().setVariable(BR.tag, tag)

            // タップ時の処理
            viewHolder.getView().setOnClickListener {
                Timber.d("Article of position $position is clicked")
//                listener.onRecyclerClickedListener(it, article, position)
            }
        }
    }

    /**
     * 要素数を返却
     *
     * @return 要素数
     */
    override fun getItemCount(): Int {
        return tagList.size
    }

    /**
     * 要素のアップデート
     *
     * @param tagList タグリスト
     */
    fun updateItems(tagList: List<QiitaTag>) {
        this.tagList = tagList
        notifyDataSetChanged()
    }

}
