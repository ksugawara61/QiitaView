package net.k2o_info.qiitaview.model.pojo

import java.util.*

class QiitaArticle (
        val id: String,
        val title: String,
        val url: String,
        val likesCount: Int,
        val commentsCount: Int,
        val user: QiitaUser,
        val createdAt: Date,
        val updatedAt: Date
)