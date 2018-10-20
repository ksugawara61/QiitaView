package net.k2o_info.qiitaview.viewmodel.fragment

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import net.k2o_info.qiitaview.model.AppRepository
import net.k2o_info.qiitaview.model.pojo.QiitaArticle

class ArticleListViewModel(application: Application): AndroidViewModel(application) {

    private val appRepository: AppRepository = AppRepository(application)
    private val mutableArticleList: MutableLiveData<List<QiitaArticle>> = MutableLiveData()
    private var articleList: LiveData<List<QiitaArticle>>


    /**
     * コンストラクタ
     */
    init {
        articleList = appRepository.getArticle(mutableArticleList, 1, 40, null)
    }

    /**
     * 記事リストをリフレッシュ
     */
    fun refreshArticleList() {
        articleList = appRepository.getArticle(mutableArticleList, 1, 40, null)
    }

    /**
     * 記事リストの取得
     *
     * @return 記事リスト
     */
    fun getArticleList(): LiveData<List<QiitaArticle>> {
        return articleList
    }

}