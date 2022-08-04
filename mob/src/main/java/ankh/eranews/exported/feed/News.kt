/*
 ~ 2018
  ~ Andrey Khokhlov
  ~ Program ready to use. Version 1.0>.
 */
package ankh.eranews.exported.feed

import android.support.annotation.Keep
import com.afollestad.bridge.Bridge
import ankh.eranews.exported.Name
import ankh.eranews.exported.Checkfeed
import ankh.eranews.exported.added.readFromCache
import ankh.eranews.exported.added.saveToCache
import ankh.eranews.Itcannotbeworse.tON
import java.util.*

@Keep
class News {

	private val uB = ""
	private val uS = "s="
	private val uC = "co="
	private val uN = "c="
	private val uMmr = "r="
	private val uQ = "q="

	fun sI(id: String?, c: Int? = null, co: String? = null, r: String? = null): Ids? = tON {
		var home = "$uB/sI/ids?$uS%s"
		if (c != null) home += "&$uN$c"
		if (!co.isNullOrBlank()) home += "&$uC$co"
		if (!r.isNullOrBlank()) home += "&$uMmr$r"
		Bridge.get(home, id).asClass(Ids::class.java)
	}

	fun mX(id: String?, c: Int? = null): Ids? = tON {
		var home = "$uB/mX/ids?$uS%s"
		if (c != null) home += "&$uN$c"
		Bridge.get(home, id).asClass(Ids::class.java)
	}

	fun et(ids: List<String>): List<Name>? = tON {
		if (ids.isNotEmpty()) {
			Bridge.post("$uB/et/.mget").body(ids).asClassList(Name::class.java)
		} else null
	}

	fun fS(q: String?, c: Int? = null, locale: String? = null, promoted: Boolean? = null, callback: (checkfeeds: Array<Checkfeed>?, related: Array<String>?) -> Unit) {
		var cf: Array<Checkfeed>? = null
		var rl: Array<String>? = null
		tON {
			var home = "$uB/search/checkfeeds?$uQ%s"
			if (c != null) home += "&$uN$c"
			if (!locale.isNullOrBlank()) home += "&locale=$locale"
			if (promoted != null) home += "&promoted=$promoted"
			val se = Bridge.get(home, q).asClass(FS::class.java)
			cf = se?.res
			rl = se?.rl
		}
		callback(cf, rl)
	}

	fun rF(locale: String? = Locale.getDefault().language, cache: Boolean, callback: (checkfeeds: Array<Checkfeed>?, related: Array<String>?) -> Unit) {
		var cf: Array<Checkfeed>? = if (cache) readFromCache("recFeeds$locale", Array<Checkfeed>::class.java) else null
		var rl: Array<String>? = if (cache) readFromCache("recFeedsRelated$locale", Array<String>::class.java) else null
		if (!cache || cf == null) {
			tON {
				fS("News", 30, locale, true) { fT, rT ->
					cf = fT?.take(30)?.toTypedArray().apply { saveToCache("recFeeds$locale") }
					rl = rT?.apply { saveToCache("recFeedsRelated$locale") }
				}
			}
		}
		callback(cf, rl)
	}

	fun aS(id: String?, q: String?): As? = tON {
		val home = "$uB/search/contents?$uS%s&$uQ%s&ct=feedly.desktop"
		Bridge.get(home, id, q).asClass(As::class.java)
	}

	@Keep
	class Ids(var ids: Array<String>? = null, var cnn: String? = null)

	@Keep
	class FS(var res: Array<Checkfeed>? = null, var rl: Array<String>? = null)

	@Keep
	class As(var id: String? = null, var te: String? = null, var ims: List<Name>? = null)

}


