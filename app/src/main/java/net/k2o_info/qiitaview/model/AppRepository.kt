package net.k2o_info.qiitaview.model

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import net.k2o_info.qiitaview.R
import net.k2o_info.qiitaview.model.pojo.QiitaArticle
import net.k2o_info.qiitaview.model.service.QiitaApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import retrofit2.Call
import retrofit2.Callback
import timber.log.Timber


/**
 * アプリケーションリポジトリ
 *
 * @author katsuya
 * @since 1.0.0
 */
class AppRepository(application: Application) {

    private val qiitaApiService: QiitaApiService

    /**
     * コンストラクタ
     */
    init {

        // HTTPリクエスト設定
        val client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)  // サーバ接続にかかる時間でのタイムアウト
                .readTimeout(15, TimeUnit.SECONDS)     // リクエストを投げてレスポンスを返すまでの時間
                .writeTimeout(15, TimeUnit.SECONDS)    // リクエストヘッダー到達からレスポンス完了までの時間
                .build()

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)  // JSONのスネークケースをキャメルケースに変換
                .create()

        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(application.getString(R.string.qiita_api_base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        qiitaApiService = retrofit.create(QiitaApiService::class.java)
    }

    /**
     * Qiitaの記事を取得
     *
     * @param mutableArticleList 記事リスト
     * @param page ページ番号
     * @param perPage ページ辺りの記事数
     * @param query 検索クエリ
     * @return 記事リスト
     */
    fun getArticle(mutableArticleList: MutableLiveData<List<QiitaArticle>>, page: Int, perPage: Int, query: String?): LiveData<List<QiitaArticle>> {

        val call = qiitaApiService.getItems(page, perPage, query)
        call.enqueue(object : Callback<List<QiitaArticle>> {
            override fun onResponse(call: Call<List<QiitaArticle>>, response: retrofit2.Response<List<QiitaArticle>>) {
                if (response.isSuccessful && response.body() != null) {
                    Timber.d("success: ${response.body()}")
                    mutableArticleList.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<QiitaArticle>>, t: Throwable) {
                Timber.e(t.message)
            }
        })

        return mutableArticleList
    }

}