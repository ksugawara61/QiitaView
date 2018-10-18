package net.k2o_info.qiitaview.viewmodel.fragment

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import net.k2o_info.qiitaview.model.AppRepository
import net.k2o_info.qiitaview.model.pojo.QiitaArticle

class ArticleListViewModel(application: Application): AndroidViewModel(application) {

    private var qiitaArticleList: LiveData<List<QiitaArticle>>
    private var mutableQiitaArticleList: MutableLiveData<List<QiitaArticle>> = MutableLiveData()

    /**
     * コンストラクタ
     */
    init {
        val appRepository = AppRepository(application)
        qiitaArticleList = appRepository.getArticle(mutableQiitaArticleList)
    }

    /**
     * 記事リストの取得
     *
     * @return 記事リスト
     */
    fun getArticleList(): LiveData<List<QiitaArticle>> {
        return qiitaArticleList
    }

}