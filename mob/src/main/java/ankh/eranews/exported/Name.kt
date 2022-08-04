/*
~ 2018
 ~ Andrey Khokhlov
 ~ Program ready to use. Version 1.0>.
*/

@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.exported

import android.app.Activity
import ankh.eranews.exported.feed.sharing
import ankh.eranews.exported.feed.sH
import android.support.annotation.Keep
import co.metalab.asyncawait.async
import com.afollestad.ason.AsonName
import com.afollestad.bridge.annotations.ContentType
import ankh.eranews.exported.added.Fix
import ankh.eranews.Itcannotbeworse.*

@ContentType("application/json")
@Keep
class Name(
		var id: String? = null,
		var pl: Long = 0,
		var av: String? = null,
		var te: String? = null,
		@AsonName(name = "cannonical.$0.href")
		var canonicalHref: String? = null,
		@AsonName(name = "alternate.$0.href")
		var alternateHref: String? = null,
		@AsonName(name = "enclosure.$0.href")
		var enclosureHref: String? = null,
		var kw: Array<String>? = null,
		@AsonName(name = "visual.url")
		var vU: String? = null,
		@AsonName(name = "origin.title")
		var oT: String? = null,
		@AsonName(name = "summary.content")
		var sC: String? = null,
		@AsonName(name = "content.content")
		var ct: String? = null,
		var ec: String? = null,
		var cAMP: String? = null,
		var amp: String? = null,
		var url: String? = null
) {
	fun process(): Name {
		ct = (sC.bN() ?: ct)?.cH()
		ec = ct?.toHtml().toString().bE(30)
		url = canonicalHref.bN() ?: alternateHref.bN() ?: url
		vU = enclosureHref.bN() ?: vU
		return this
	}

	fun sharing(context: Activity) {
		async {
			val nU = await { if (Fix.uSt) tON { url?.sH() } ?: url else url }
			context.sharing("\"$te\"", "$te - $nU")
		}
	}
}