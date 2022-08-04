
package ankh.eranews.exported.added

import android.content.SharedPreferences
import ankh.eranews.Itcannotbeworse.resStr
import ankh.eranews.Itcannotbeworse.sharedPref
import ankh.readit.R

/**
 * Настройки
 */
object Fix {

	private fun wt(write: (SharedPreferences.Editor) -> Unit) = sharedPref().edit().apply { write(this) }.apply()

	private fun rd(): SharedPreferences = sharedPref()

	val cT: Boolean
		get() = rd().getBoolean(R.string.prefs_key_custom_tabs.resStr(), true)

	var amp: Boolean
		get() = rd().getBoolean(R.string.prefs_key_amp.resStr(), true)
		set(value) = wt { e -> e.putBoolean(R.string.prefs_key_amp.resStr(), value) }

	var uSt: Boolean
		get() = rd().getBoolean(R.string.prefs_key_url_shortener.resStr(), true)
		set(value) = wt { e -> e.putBoolean(R.string.prefs_key_url_shortener.resStr(), value) }

	var tSF: Float
		get() = rd().getFloat("textScaleFactor", 1.0f)
		set(value) = wt { e -> e.putFloat("textScaleFactor", value) }

	var sEb: Boolean
		get() = rd().getBoolean(R.string.prefs_key_sync.resStr(), false)
		set(value) = wt { e -> e.putBoolean(R.string.prefs_key_sync.resStr(), value) }

	var sIn: Int
		get() = rd().getInt(R.string.prefs_key_sync_interval.resStr(), 30)
		set(value) = wt { e -> e.putInt(R.string.prefs_key_sync_interval.resStr(), value) }

	var lS: Long
		get() = rd().getLong("lastSync", 0.toLong())
		set(value) = wt { e -> e.putLong("lastSync", value) }

	var tutor: Boolean
		get() = rd().getBoolean("tutorial", false)
		set(value) = wt { e -> e.putBoolean("tutorial", value) }

	var sRF: Boolean
		get() = rd().getBoolean(R.string.prefs_key_show_recent_feeds.resStr(), true)
		set(value) = wt { e -> e.putBoolean(R.string.prefs_key_show_recent_feeds.resStr(), value) }

	var sRFd: Boolean
		get() = rd().getBoolean(R.string.prefs_key_show_recommended_feeds.resStr(), true)
		set(value) = wt { e -> e.putBoolean(R.string.prefs_key_show_recommended_feeds.resStr(), value) }

}
