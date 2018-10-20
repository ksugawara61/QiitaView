package net.k2o_info.qiitaview.view.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexboxLayout
import net.k2o_info.qiitaview.BR
import net.k2o_info.qiitaview.R
import net.k2o_info.qiitaview.databinding.TagItemBinding
import net.k2o_info.qiitaview.model.pojo.QiitaArticle
import timber.log.Timber

class ArticleRecyclerAdapter(context: Context, listener: ArticleRecyclerListener) : RecyclerView.Adapter<ViewHolder>() {

    interface ArticleRecyclerListener {
        fun onRecyclerClickedListener(view: View, article: QiitaArticle, position: Int)
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val listener: ArticleRecyclerListener = listener
    private var articleList: List<QiitaArticle> = emptyList()

    /**
     * 新規ViewHolderが渡されたときに呼ばれる
     *
     * @param viewGroup ビューの要素
     * @param viewType ビューの種類
     * @return 新しいViewHolder
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.article_item, viewGroup, false))
    }

    /**
     * 要素が表示されたときに呼ばれる
     *
     * @param viewHolder ビューホルダー
     * @param position 要素番号
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val article = articleList.getOrNull(position)

        if (article != null) {
            // 変数の格納
            viewHolder.getBinding().setVariable(BR.article, article)

            // タップ時の処理
            viewHolder.getView().setOnClickListener {
                Timber.d("Article of position $position is clicked")
                listener.onRecyclerClickedListener(it, article, position)
            }
        }
    }

    /**
     * 要素数を返却
     *
     * @return 要素数
     */
    override fun getItemCount(): Int {
        return articleList.size
    }

    /**
     * 要素のアップデート
     */
    fun updateItems(articleList: List<QiitaArticle>) {
        this.articleList = articleList
        notifyDataSetChanged()
    }

}
