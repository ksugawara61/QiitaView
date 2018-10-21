package net.k2o_info.qiitaview.model.service

import net.k2o_info.qiitaview.model.pojo.QiitaArticle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Qiita API用サービスインタフェース
 */
interface QiitaApiService {

    /**
     * Qiitaの記事リストを取得
     *
     * @param page ページ番号
     * @param perPage ページ辺りの記事数
     * @param query 検索クエリ
     */
    @GET("v2/items")
    fun getItems(
            @Query(value = "page", encoded = true) page: Int,
            @Query(value = "per_page", encoded = true) perPage: Int,
            @Query(value = "query", encoded = true) query: String?
    ): Call<List<QiitaArticle>>



}