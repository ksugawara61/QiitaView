package net.k2o_info.qiitaview.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.k2o_info.qiitaview.R

/**
 * 記事リスト用フラグメント
 *
 * @author katsuya
 * @since 1.0.0
 */
class ArticleListFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_article_list, container, false)
        return view
    }

    /**
     * ActivityのonCreateが呼ばれた直後に呼ばれる
     *
     * @param savedInstanceState 保存している状態
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}