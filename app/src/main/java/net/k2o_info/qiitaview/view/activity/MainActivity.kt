package net.k2o_info.qiitaview.view.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import net.k2o_info.qiitaview.R
import net.k2o_info.qiitaview.databinding.ActivityMainBinding
import net.k2o_info.qiitaview.view.fragment.ArticleListFragment
import net.k2o_info.qiitaview.view.fragment.SettingFragment
import timber.log.Timber

/**
 * メインアクティビティ
 *
 * @author katsuya
 * @since 1.0.0
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val BOTTOM_NAV_ARTICLE = 0
        const val BOTTOM_NAV_TAG     = 1
        const val BOTTOM_NAV_SETTING = 2
    }

    /**
     * アクティビティ起動時に呼ばれる
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // フラグメントの設定
        val articleListFragment = ArticleListFragment()
        val settingFragment     = SettingFragment()

        // ボトムナビゲーションバーの設定
        val bnb: BottomNavigationBar = binding.bnb
        bnb.addItem(BottomNavigationItem(R.drawable.ic_rss_feed_black_24dp, getString(R.string.nav_feed)))
                .addItem(BottomNavigationItem(R.drawable.ic_style_black_24dp, getString(R.string.nav_tag)))
                .addItem(BottomNavigationItem(R.drawable.ic_settings_black_24dp, getString(R.string.nav_setting)))
                .initialise()
        bnb.setTabSelectedListener(object:BottomNavigationBar.OnTabSelectedListener {

            /**
             * ボトムナビゲーション選択時に呼ばれる
             *
             * @param position 選択した要素番号
             */
            override fun onTabSelected(position: Int) {
                Timber.d("onTabSelected: $position")
                when (position) {
                    BOTTOM_NAV_ARTICLE -> {
                        val transaction = supportFragmentManager.beginTransaction()
                        transaction.replace(binding.fragmentContainer.id, articleListFragment)
                        transaction.commit()
                    }
                    BOTTOM_NAV_TAG -> {

                    }
                    BOTTOM_NAV_SETTING -> {
                        val transaction = supportFragmentManager.beginTransaction()
                        transaction.replace(binding.fragmentContainer.id, settingFragment)
                        transaction.commit()
                    }
                }
            }

            override fun onTabUnselected(position: Int) {
                Timber.d("onTabUnselected: $position")
            }

            override fun onTabReselected(position: Int) {
                Timber.d("onTabReselected: $position")
            }

        })

        // 起動時は記事リストを表示
        bnb.selectTab(BOTTOM_NAV_ARTICLE)
    }

}
