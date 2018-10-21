package net.k2o_info.qiitaview.model.pojo

import java.util.*

/**
 * Qiita記事のオブジェクト
 *
 * @author katsuya
 * @since 1.0.0
 */
class QiitaArticle (
        val id: String,
        val title: String,
        val url: String,
        val likesCount: Int,
        val commentsCount: Int,
        val user: QiitaArticleUser,
        val tags: List<QiitaArticleTag>,
        val createdAt: Date,
        val updatedAt: Date
)