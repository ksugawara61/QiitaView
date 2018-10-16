package net.k2o_info.qiitaview.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import net.k2o_info.qiitaview.R
import net.k2o_info.qiitaview.databinding.ActivityMainBinding
import timber.log.Timber

/**
 * メインアクティビティ
 *
 * @author katsuya
 * @since 1.0.0
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val BOTTOM_NAV_FEED    = 0
        const val BOTTOM_NAV_SETTING = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // ボトムナビゲーションバーの設定
        val bnb: BottomNavigationBar = binding.bnb
        bnb.addItem(BottomNavigationItem(R.drawable.ic_rss_feed_black_24dp, getString(R.string.nav_feed)))
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
                    BOTTOM_NAV_FEED -> {

                    }
                    BOTTOM_NAV_SETTING -> {

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
    }

}
