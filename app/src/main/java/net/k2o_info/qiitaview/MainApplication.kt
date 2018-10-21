package net.k2o_info.qiitaview

import android.app.Application
import timber.log.Timber

/**
 * アプリケーションクラス
 *
 * @author katsuya
 * @since 1.0.0
 */
class MainApplication : Application() {

    companion object {

        private var instance: MainApplication? = null

        /**
         * コンテキストの取得
         *
         * @return コンテキスト
         */
        fun getInstance(): MainApplication? {
            return instance
        }
    }

    /**
     * アプリケーション起動時に呼ばれる
     */
    override fun onCreate() {
        super.onCreate()

        MainApplication.instance = this

        if (BuildConfig.DEBUG) {
            // デバック時は Timberでログ出力
            Timber.plant(Timber.DebugTree())
        }
    }

}