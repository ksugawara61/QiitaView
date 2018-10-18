package net.k2o_info.qiitaview.viewmodel.fragment

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import net.k2o_info.qiitaview.model.AppRepository

class ArticleListViewModel(application: Application): AndroidViewModel(application) {

    private val appRepository: AppRepository

    /**
     * コンストラクタ
     */
    init {
        appRepository = AppRepository(application)
        appRepository.getArticle()
    }

    /*fun getArticleList(): LiveData<List<QiitaArticle>> {
        return null
    }*/

}