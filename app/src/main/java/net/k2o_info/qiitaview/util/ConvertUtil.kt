package net.k2o_info.qiitaview.util

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 時間処理関連のUtilクラス
 *
 * @author katsuya
 * @since 1.0.0
 */
object TimeUtil {

    private const val MINUTE_SECONDS = 60
    private const val HOUR_SECONDS   = 3600
    private const val DAY_SECONDS    = 86400

    /**
     * Dateオブジェクトから作成日のテキストに変換
     *
     * @param date Dateオブジェクト
     * @return テキスト
     */
    @JvmStatic
    fun dateToCreatedAt(date: Date): String {
        var timeText = String()
        var unitText = String()

        val now = System.currentTimeMillis()
        val diffMilliSec = now - date.time
        val diffSec = TimeUnit.MILLISECONDS.toSeconds(diffMilliSec)  // ミリ秒を秒に変換

        when {
            diffSec < HOUR_SECONDS -> {
                timeText = (diffSec / MINUTE_SECONDS).toString()
                unitText = "分前"
            }
            diffSec < DAY_SECONDS -> {
                timeText = (diffSec / HOUR_SECONDS).toString()
                unitText = "時間前"
            }
            else -> {
                timeText = (diffSec / DAY_SECONDS).toString()
                unitText = "日前"
            }
        }

        return timeText + unitText
    }

}