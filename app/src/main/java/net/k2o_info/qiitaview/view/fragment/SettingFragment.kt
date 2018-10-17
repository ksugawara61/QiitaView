package net.k2o_info.qiitaview.view.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
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

    /**
     * 設定の項目選択時のイベント
     *
     * @param preference プリファレンス
     * @return true
     */
    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            getString(R.string.pref_opinion_key) -> {
                // ご意見・ご要望が選択された時にWebでご意見・ご要望ページへ遷移
                val tabsIntent = CustomTabsIntent.Builder()
                        .setShowTitle(true)
                        .setToolbarColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
                        .build()
                tabsIntent.launchUrl(context!!, Uri.parse(getString(R.string.opinion_form_url)))
            }

            getString(R.string.pref_license_key) -> {
                // ライセンスが選択された時にライセンスリストを表示
                OssLicensesMenuActivity.setActivityTitle(getString(R.string.pref_license))
                startActivity(Intent(activity, OssLicensesMenuActivity().javaClass))
            }

        }

        return super.onPreferenceTreeClick(preference)
    }

}