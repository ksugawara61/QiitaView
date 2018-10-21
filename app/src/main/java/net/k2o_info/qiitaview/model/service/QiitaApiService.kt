package net.k2o_info.qiitaview.model.service

import net.k2o_info.qiitaview.model.pojo.QiitaArticle
import net.k2o_info.qiitaview.model.pojo.QiitaTag
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Qiita API用サービスインタフェース
 *
 * @author katsuya
 * @since 1.0.0
 */
interface QiitaApiService {

    /**
     * Qiitaの記事リストを取得
     *
     * @param page ページ番号
     * @param perPage ページ辺りの記事数
     * @param query 検索クエリ
     * @return 記事リスト
     */
    @GET("v2/items")
    fun getItems(
            @Query(value = "page", encoded = true) page: Int,
            @Query(value = "per_page", encoded = true) perPage: Int,
            @Query(value = "query", encoded = true) query: String?
    ): Call<List<QiitaArticle>>

    /**
     * Qiitaのタグリストを取得
     *
     * @param page ページ番号
     * @param perPage ページ辺りの記事数
     * @param sort 並び順 (countで投稿数順、nameで名前順)
     * @return タグリスト
     */
    @GET("v2/tags")
    fun getTags(
            @Query(value = "page", encoded = true) page: Int,
            @Query(value = "per_page", encoded = true) perPage: Int,
            @Query(value = "sort", encoded = true) sort: String
    ): Call<List<QiitaTag>>

}