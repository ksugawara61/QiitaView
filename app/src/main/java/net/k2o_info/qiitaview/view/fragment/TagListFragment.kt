package net.k2o_info.qiitaview.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.k2o_info.qiitaview.R
import net.k2o_info.qiitaview.databinding.FragmentTagListBinding
import net.k2o_info.qiitaview.model.pojo.QiitaTag
import net.k2o_info.qiitaview.view.ui.TagRecyclerAdapter
import net.k2o_info.qiitaview.viewmodel.fragment.TagListViewModel
import timber.log.Timber

/**
 * タグリスト用フラグメント
 *
 * @author katsuya
 * @since 1.0.0
 */
class TagListFragment : Fragment()/*, ArticleRecyclerAdapter.ArticleRecyclerListener*/ {

    companion object {

        /**
         * インスタンスの生成
         *
         * @return フラグメント
         */
        fun newInstance(): TagListFragment {
            return TagListFragment()
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
        val binding: FragmentTagListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tag_list, container, false)
        val recyclerView: RecyclerView = binding.recyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        // RecyclerViewに下線を引く
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, linearLayoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        // アダプターを設定
        val recyclerAdapter = TagRecyclerAdapter(context!!)
        recyclerView.adapter = recyclerAdapter

        // ViewModelの設定
        val viewModel = ViewModelProviders.of(this).get(TagListViewModel::class.java)
        viewModel.getTagList().observe(this, Observer { list: List<QiitaTag>? ->

            // リストの更新があった場合にrecyclerAdapterをアップデート
            if (list != null && list.isNotEmpty()) {
                binding.emptyView.visibility    = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                recyclerAdapter.updateItems(list)
            } else {
                // 要素が空の場合は空のビューを表示
                binding.recyclerView.visibility = View.GONE
                binding.emptyView.visibility    = View.VISIBLE
            }
        })

        // スワイプダウン時のリフレッシュ処理
        val swipeContainer: SwipeRefreshLayout = binding.swipeContainer
        swipeContainer.setOnRefreshListener {
            Timber.d("onRefresh")
            viewModel.refreshTagList()
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
     * @param article 記事
     * @param position 要素番号
     */
    /*override fun onRecyclerClickedListener(view: View, article: QiitaArticle, position: Int) {
        // タップした記事のURLへ遷移
        val tabsIntent = CustomTabsIntent.Builder()
                .setShowTitle(true)
                .setToolbarColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
                .build()
        tabsIntent.launchUrl(context!!, Uri.parse(article.url))
    }*/

}