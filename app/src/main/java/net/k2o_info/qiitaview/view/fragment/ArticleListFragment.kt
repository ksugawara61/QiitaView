package net.k2o_info.qiitaview.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.k2o_info.qiitaview.R
import net.k2o_info.qiitaview.databinding.FragmentArticleListBinding
import net.k2o_info.qiitaview.model.pojo.QiitaArticle
import net.k2o_info.qiitaview.view.ui.ArticleRecyclerAdapter
import net.k2o_info.qiitaview.viewmodel.fragment.ArticleListViewModel
import timber.log.Timber

/**
 * 記事リスト用フラグメント
 *
 * @author katsuya
 * @since 1.0.0
 */
class ArticleListFragment : Fragment(), ArticleRecyclerAdapter.ArticleRecyclerListener {

    companion object {

        /**
         * インスタンスの生成
         *
         * @return フラグメント
         */
        fun newInstance(): ArticleListFragment {
            return ArticleListFragment()
        }

    }

    /**
     * フラグメント生成時に呼ばれる
     *
     * @param context コンテキスト
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    /**
     * フラグメントのビューを描画
     *
     * @param inflater レイアウトの拡張用
     * @param container 親ビューのUI
     * @param savedInstanceState 保存している状態
     * @return フラグメントを描画したビュー
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentArticleListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_list, container, false)
        val recyclerView: RecyclerView = binding.recyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        // RecyclerViewに下線を引く
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, linearLayoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        // アダプターを設定
        val recyclerAdapter = ArticleRecyclerAdapter(context!!, this)
        recyclerView.adapter = recyclerAdapter

        // ViewModelの設定
        val viewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)
        viewModel.getArticleList().observe(this, Observer { list: List<QiitaArticle>? ->

            // リストの更新があった場合にrecyclerAdapterをアップデート
            if (list != null) {
                recyclerAdapter.updateItems(list)
            }
        })

        // スワイプダウン時のリフレッシュ処理
        val swipeContainer: SwipeRefreshLayout = binding.swipeContainer
        swipeContainer.setOnRefreshListener {
            Timber.d("onRefresh")
            viewModel.refreshArticleList()
            swipeContainer.isRefreshing = !swipeContainer.isRefreshing
        }

        return binding.root
    }

    /**
     * ActivityのonCreateが呼ばれた直後に呼ばれる
     *
     * @param savedInstanceState 保存している状態
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    /**
     * 記事タップ時に呼ばれる
     *
     * @param view タップしたビュー
     * @param position 要素番号
     */
    override fun onRecyclerClickedListener(view: View, position: Int) {
        // タップした記事のURLへ遷移
        val tabsIntent = CustomTabsIntent.Builder()
                .setShowTitle(true)
                .setToolbarColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
                .build()
        tabsIntent.launchUrl(context!!, Uri.parse("https://qiita.com/"))
    }

}