package net.k2o_info.qiitaview.view.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.k2o_info.qiitaview.R
import timber.log.Timber
import java.util.*

class ArticleRecyclerAdapter(context: Context, listener: ArticleRecyclerListener) : RecyclerView.Adapter<ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val context: Context = context
    private val listener: ArticleRecyclerListener = listener
    private var articleList: List<String> = Arrays.asList("sampleA", "sampleB")//emptyList()

    interface ArticleRecyclerListener {
        fun onRecyclerClickedListener(view: View, position: Int)
    }

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

        // タップ時の処理
        viewHolder.getView().setOnClickListener{
            Timber.d("Article of position $position is clicked")
            listener.onRecyclerClickedListener(it, position)
        }
    }

    /**
     * 要素数を返却
     *
     * @return 要素数
     */
    override fun getItemCount(): Int {
        return 20//articleList.size
    }

    /**
     * 要素のアップデート
     */
    fun updateItems(musicList: List<String>) {
        this.articleList = musicList
        notifyDataSetChanged()
    }

}
