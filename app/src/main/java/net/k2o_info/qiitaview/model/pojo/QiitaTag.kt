package net.k2o_info.qiitaview.model.pojo

/**
 * Qiitaのタグ一覧APIから取得可能なタグオブジェクト
 *
 * @author katsuya
 * @since 1.1.0
 */
class QiitaTag (
        val id: String,
        val iconUrl: String,
        val followersCount: Int,
        val itemsCount: Int
)
