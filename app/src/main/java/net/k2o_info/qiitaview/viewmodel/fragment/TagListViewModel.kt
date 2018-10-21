package net.k2o_info.qiitaview.viewmodel.fragment

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import net.k2o_info.qiitaview.model.AppRepository
import net.k2o_info.qiitaview.model.pojo.QiitaTag

/**
 * タグリストフラグメント用ViewModel
 *
 * @author katsuya
 * @since 1.1.0
 */
class TagListViewModel(application: Application): AndroidViewModel(application) {

    companion object {
        const val DEFAULT_PAGE     = 1
        const val DEFAULT_PER_PAGE = 50
        const val DEFAULT_SORT     = "count"
    }

    private val appRepository: AppRepository = AppRepository.getInstance(application)
    private val mutableTagList: MutableLiveData<List<QiitaTag>> = MutableLiveData()
    private var tagList: LiveData<List<QiitaTag>>

    /**
     * コンストラクタ
     */
    init {
        tagList = appRepository.getTag(mutableTagList, DEFAULT_PAGE, DEFAULT_PER_PAGE, DEFAULT_SORT)
    }

    /**
     * タグリストをリフレッシュ
     */
    fun refreshTagList() {
        tagList = appRepository.getTag(mutableTagList, DEFAULT_PAGE, DEFAULT_PER_PAGE, DEFAULT_SORT)
    }

    /**
     * タグリストの取得
     *
     * @return タグリスト
     */
    fun getTagList(): LiveData<List<QiitaTag>> {
        return tagList
    }

}