package net.k2o_info.qiitaview.viewmodel.fragment

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import net.k2o_info.qiitaview.model.AppRepository
import net.k2o_info.qiitaview.model.pojo.QiitaArticle
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider



class ArticleListViewModel(application: Application, query: String?): AndroidViewModel(application) {

    companion object {
        const val DEFAULT_PAGE     = 1
        const val DEFAULT_PER_PAGE = 50
    }

    private val appRepository: AppRepository = AppRepository.getInstance(application)
    private val query: String? = query
    private val mutableArticleList: MutableLiveData<List<QiitaArticle>> = MutableLiveData()
    private var articleList: LiveData<List<QiitaArticle>>

    /**
     * コンストラクタ
     */
    init {
        articleList = appRepository.getArticle(mutableArticleList, DEFAULT_PAGE, DEFAULT_PER_PAGE, this.query)
    }

    /**
     * 記事リストをリフレッシュ
     */
    fun refreshArticleList() {
        articleList = appRepository.getArticle(mutableArticleList, DEFAULT_PAGE, DEFAULT_PER_PAGE, this.query)
    }

    /**
     * 記事リストの取得
     *
     * @return 記事リスト
     */
    fun getArticleList(): LiveData<List<QiitaArticle>> {
        return articleList
    }

    class Factory(application: Application, query: String?) : ViewModelProvider.NewInstanceFactory() {

        private val application: Application = application
        private val query: String? = query

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleListViewModel(application, query) as T
        }

    }


}