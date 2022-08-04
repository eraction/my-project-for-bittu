 /*
 ~ 2018
  ~ Andrey Khokhlov
  ~ Program ready to use. Version 1.0>.
 */

package ankh.eranews.exported.feed

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import com.afollestad.bridge.Bridge
import ankh.eranews.exported.added.Fix
import ankh.eranews.Itcannotbeworse.bN
import ankh.eranews.Itcannotbeworse.resClr
import ankh.eranews.Itcannotbeworse.resStr
import ankh.readit.R
import me.zhanghai.android.customtabshelper.CustomTabsHelperFragment
import java.net.URLEncoder

// расшарить
fun Context.sharing(te: String, text: String) {
	startActivity(Intent.createChooser(Intent().apply {
		action = Intent.ACTION_SEND
		type = "text/plain"
		putExtra(Intent.EXTRA_SUBJECT, te)
		putExtra(Intent.EXTRA_TEXT, text)
	}, "${R.string.share.resStr()} $te"))
}

// короткие ссылки
fun String.sH(): String {
	if (isNotBlank()) {
		Bridge.get("https://tny.im/yourls-api.php?action=shorturl&format=simple&url=$this")
				.asString()
				.let { return it ?: this }
	} else return this
}

 // хейстбин
 fun String.uH(): String? {
	if (isNotBlank()) {
		Bridge.post("https://hastebin.com/documents")
				.body(this)
				.contentType("plain/text")
				.asAsonObject()
				.let {
					return it?.getString("key").bN()
				}
	} else return null
}

fun String.dH(): String? {
	if (isNotBlank()) {
		Bridge.get("https://hastebin.com/documents/$this")
				.asAsonObject()
				.let {
					return it?.getString("data").bN()
				}
	} else return null
}

// амп меркуки
fun String.amp() = "https://mercury.postlight.com/amp?url=${URLEncoder.encode(this, "UTF-8")}"

// открытие ссылок
fun String?.oU(activity: Activity, amp: Boolean = true, Amp: Boolean = false, nA: String? = null) {
	val ampda = Fix.amp && amp
	(if (ampda && (!Amp || (Amp && this@oU.isNullOrBlank()))) (this@oU ?: nA)?.amp()
	else if (ampda && Amp) this@oU
	else nA ?: this@oU)?.let { finalUrl ->
		val aI = Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl))
		if (Fix.cT) {
			try {
				val cTI = CustomTabsIntent.Builder()
						.setToolbarColor(R.color.colorPrimary.resClr(activity)!!)
						.setShowTitle(true)
						.addDefaultShareMenuItem()
						.enableUrlBarHiding()
						.build()
				CustomTabsHelperFragment.open(activity, cTI, Uri.parse(finalUrl)) { activity, _ ->
					activity.startActivity(aI)
				}
			} catch (e: Exception) {
				e.printStackTrace()
				activity.startActivity(aI)
			}
		} else {
			activity.startActivity(aI)
		}
	}
}