package net.k2o_info.qiitaview.view.fragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import net.k2o_info.qiitaview.R
import timber.log.Timber

/**
 * 設定用フラグメント
 *
 * @author katsuya
 * @since 1.0.0
 */
class SettingFragment : PreferenceFragmentCompat() {

    /**
     * フラグメント生成時に呼ばれる
     *
     * @param savedInstanceState
     * @param rootKey
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    /**
     * フラグメント生成時に呼ばれる
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // バージョン情報を設定
        val packageManager = context!!.packageManager
        try {
            val packageInfo = packageManager.getPackageInfo(context!!.packageName, 0)
            val versionName = packageInfo.versionName
            val versionPreference = preferenceScreen.findPreference(getString(R.string.pref_version_key))
            versionPreference.summary = versionName
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.e(e.message)
        }

    }

}