
@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.add.mini

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.metalab.asyncawait.async
import com.afollestad.materialdialogs.MaterialDialog
import ankh.eranews.aC
import ankh.eranews.exported.Checkfeed
import ankh.eranews.exported.feed.bRt
import ankh.eranews.exported.feed.oU
import ankh.eranews.exported.added.*
import ankh.eranews.Itcannotbeworse.*
import ankh.eranews.mA
import ankh.readit.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class Mainset : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
	private var sC: Context = context ?: mA ?: aC!!

	private var sRc = object : BroadcastReceiver() {
		override fun onReceive(context: Context?, intent: Intent?) {
			this@Mainset.rLST()
		}
	}
	private var sRReg = false

	private val cCP: Preference? by lazy { findPreference(R.string.prefs_key_clear_cache.resStr()) }
	private val cHP: Preference? by lazy { findPreference(R.string.prefs_key_clear_history.resStr()) }
	private val vLP: Preference? by lazy { findPreference(R.string.prefs_key_view_libs.resStr()) }
	private val vAP: Preference? by lazy { findPreference(R.string.prefs_key_view_apis.resStr()) }
	private val aP: Preference? by lazy { findPreference(R.string.prefs_key_about_nc.resStr()) }
	private val bP: Preference? by lazy { findPreference(R.string.prefs_key_backup.resStr()) }
	private val iP: Preference? by lazy { findPreference(R.string.prefs_key_import_opml.resStr()) }
	private val sNP: Preference? by lazy { findPreference(R.string.prefs_key_sync_now.resStr()) }
	private val sP: Preference? by lazy { findPreference(R.string.prefs_key_sync.resStr()) }
	private val sIP: Preference? by lazy { findPreference(R.string.prefs_key_sync_interval.resStr()) }
	private val iPf: Preference? by lazy { findPreference(R.string.prefs_key_issue.resStr()) }
	private val sTP: Preference? by lazy { findPreference(R.string.prefs_key_show_tutorial.resStr()) }

	override fun onCreatePreferences(p0: Bundle?, p1: String?) {
		addPreferencesFromResource(R.xml.preferences)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val vw = super.onCreateView(inflater, container, savedInstanceState)
		if (!sRReg) {
			sC.registerReceiver(sRc, IntentFilter("syncStatus"))
			sRReg = true
		}
		return vw
	}

	override fun onViewCreated(vw: View, savedInstanceState: Bundle?) {
		super.onViewCreated(vw, savedInstanceState)
		// отзыв на клик
		cCP?.onPreferenceClickListener = this
		cHP?.onPreferenceClickListener = this
		vLP?.onPreferenceClickListener = this
		vAP?.onPreferenceClickListener = this
		aP?.onPreferenceClickListener = this
		bP?.onPreferenceClickListener = this
		iP?.onPreferenceClickListener = this
		sIP?.onPreferenceClickListener = this
		sNP?.onPreferenceClickListener = this
		iPf?.onPreferenceClickListener = this
		sTP?.onPreferenceClickListener = this
		sP?.onPreferenceChangeListener = this

		rLST()
		rSID()
	}

	override fun onPreferenceClick(preference: Preference?): Boolean {
		when (preference) {
			cCP -> {
				sC.clearCache {
					Snackbar.make(mA!!.findViewById(R.id.mainactivity_container), R.string.cleared_cache, Snackbar.LENGTH_SHORT).show()
				}
			}
			cHP -> {
				doAsync {
					Db.aLCf = arrayOf()
					uiThread {
						sC.sendBroadcast(Intent("feed_state"))
						Snackbar.make(mA!!.findViewById(R.id.mainactivity_container), R.string.cleared_history, Snackbar.LENGTH_SHORT).show()
					}
				}
			}
			vLP -> {
				val lib = listOf(
						Lb("Material Dialogs", "", "будетссылка"),
						Lb("FastAdapter", "", "будетссылка"),
						Lb("jsoup", "", "будетссылка"),
						Lb("Bridge", "", "будетссылка"),
						Lb("Ason", "", "будетссылка"),
						Lb("Glide", "", "будетссылка"),
						Lb("FlexboxLayout", "", "будетссылка"),
						Lb("Android-Job", "", "будетссылка"),
						Lb("CustomTabsHelper", "", "будетссылка"),
						Lb("Async/Await", "", "будетссылка"),
						Lb("Anko", "", "будетссылка"),
						Lb("FancyShowCaseView", "", "будетссылка", true)
				).joinToString(separator = "") { "<b><a href=\"${it.link}\">${it.name}</a></b> ${it.description}${if (!it.isLast) "<br><br>" else ""}" }
				MaterialDialog.Builder(sC)
						.title(R.string.used_libraries)
						.content(lib.toHtml())
						.positiveText(android.R.string.ok)
						.build()
						.apply {
							contentView?.applyLinks(false)
							tON { show() }
						}
			}
			vAP -> {
				val lib = listOf(
						Lb("CloudAPI", "ы", "будетссылка"),
						Lb("UrlShortener", "ы", "будетссылка"),
						Lb("Mercury", "ы", "будетссылка", true)
				).joinToString(separator = "") { "<b><a href=\"${it.link}\">${it.name}</a></b>${if (!it.isLast) "<br><br>" else ""}" }
				MaterialDialog.Builder(sC)
						.title(R.string.used_libraries)
						.content(lib.toHtml())
						.positiveText(android.R.string.ok)
						.build()
						.apply {
							contentView?.applyLinks(false)
							tON { show() }
						}
			}
			aP -> {
				val about = "<b>Лучшая новостная читалка<br><i>Новости будущего</i></b><br><br>Разработчик: Andrey Khokhlov<br><br><a href=\"https://prodota.ru\">Сайт разработчика</a><br><a href=\"https://vk.com/broken_justice\">VK страница</a><br><br>"
				val perehod = "Открыто ${Db.aRU.size} . Спасибо за посещение!"
				MaterialDialog.Builder(sC)
						.title(R.string.app_name)
						.content("$about$perehod".toHtml())
						.positiveText(android.R.string.ok)
						.build()
						.apply {
							contentView?.applyLinks(false)
							tON { show() }
						}
			}
			bP -> sC.bRt()
			iP -> {
				MaterialDialog.Builder(sC)
						.title(R.string.import_opml)
						.input(R.string.import_opml_hint, 0) { _, input ->
							iOpml(input.toString())
						}
						.positiveText(android.R.string.ok)
						.let { tON { it.show() } }
			}
			sIP -> {
				MaterialDialog.Builder(sC)
						.title(R.string.sync_interval)
						.items(R.array.sync_interval_titles)
						.itemsCallbackSingleChoice(resources.getIntArray(R.array.sync_interval_values).indexOf(Fix.sIn)) { _, _, which, _ ->
							Fix.sIn = resources.getIntArray(R.array.sync_interval_values)[which]
							if (Fix.sEb) sSy(Fix.sIn) else cSy()
							rSID()
							true
						}
						.let { tON { it.show() } }
			}
			sNP -> {
				doAsync { sn(sC) }
			}
			iPf -> "https://vk.com/broken_justice".oU(mA!!, amp = false)
			sTP -> {
				mA?.bNV?.find<View>(R.id.feeds)?.performClick()
				mA?.start()
			}
		}
		return true
	}

	override fun onPreferenceChange(preference: Preference?, value: Any?): Boolean {
		when (preference) {
			sNP -> if (Fix.sEb) sSy(Fix.sIn) else cSy()
		}
		return true
	}

	private fun rLST() {
		sNP?.summary = "${R.string.last_suc_sync.resStr()}: " + when (Fix.lS) {
			0.toLong() -> R.string.never.resStr()
			else -> DateUtils.getRelativeTimeSpanString(Fix.lS)
		}
	}

	private fun rSID() {
		sIP?.summary = R.array.sync_interval_titles.resStrArr()!![R.array.sync_interval_values.resIntArr()!!.indexOf(Fix.sIn)]
	}

	private fun iOpml(opml: String?) = async {
		var imported = 0
		var cf: Array<Checkfeed>?
		if (!opml.isNullOrBlank()) await {
			cf = opml?.cOTF()
			cf?.forEach { Db.aV(it) }
			imported = cf?.size ?: 0
		}
		sC.sendBroadcast(Intent("feed_state"))
		MaterialDialog.Builder(sC)
				.title(R.string.import_opml)
				.content(if (imported != 0) R.string.suc_import else R.string.import_failed)
				.positiveText(android.R.string.ok)
				.let { tON { it.show() } }
	}

	override fun onDestroy() {
		tON { sC.unregisterReceiver(sRc) }
		super.onDestroy()
	}

	private class Lb(val name: String, val description: String, val link: String, val isLast: Boolean = false)
}
