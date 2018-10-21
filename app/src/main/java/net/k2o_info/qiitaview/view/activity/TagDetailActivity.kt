package net.k2o_info.qiitaview.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import net.k2o_info.qiitaview.R
import net.k2o_info.qiitaview.databinding.ActivityTagDetailBinding
import net.k2o_info.qiitaview.view.fragment.ArticleListFragment

/**
 * タグの記事リストを表示するアクティビティ
 *
 * @author katsuya
 * @since 1.1.0
 */
class TagDetailActivity : AppCompatActivity() {

    companion object {
        const val TITLE_TAG_ID = "tag_id"
        const val QUERY_PREFIX = "tag:"
    }

    /**
     * アクティビティ起動時に呼ばれる
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTagDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_tag_detail)

        // Intentから遷移元の変数を取得
        val intent = intent
        val title  = intent.getStringExtra(TITLE_TAG_ID)

        // ツールバーの戻るボタンを追加
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = title
        }

        // 記事リストのフラグメントを設定
        val articleListFragment = ArticleListFragment.newInstance(QUERY_PREFIX + title)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentContainer.id, articleListFragment)
        transaction.commit()
    }

    /**
     * ツールバーのボタンがタップされた時の動作
     *
     * @param item タップされたボタン
     * @return true
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            when (item.itemId) {
                android.R.id.home -> {
                    // 戻るボタンタップ時の動作
                    onBackPressed()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

}