/*
~ 2018
 ~ Andrey Khokhlov
 ~ Program ready to use. Version 1.0>.
*/

@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.Itcannotbeworse

import android.content.Context
import co.metalab.asyncawait.async
import com.afollestad.materialdialogs.MaterialDialog
import ankh.eranews.exported.Checkfeed
import ankh.eranews.exported.feed.News
import ankh.eranews.mA
import ankh.eranews.add.mini.Newsonboard
import ankh.readit.R

fun sFF(context: Context, query: String? = null) {
	val progressDialog = context.progressDialog()
	val load = { finalQuery: String ->
		context.async {
			progressDialog.show()
			var fCf: Array<Checkfeed>? = null
			var fR: Array<String>? = null
			await {
				News().fS(finalQuery, 100, null, null) { feeds, related ->
					fCf = feeds
					fR = related
				}
			}
			progressDialog.dismiss()
			if (fCf.nN()) mA?.oV(Newsonboard(cf = fCf, tags = fR).withTitle("${R.string.search_results_for.resStr()} $finalQuery"))
			else context.nF()
		}
	}
	if (query == null || query.isNullOrBlank()) {
		MaterialDialog.Builder(context)
				.title(android.R.string.search_go)
				.input(null, null) { _, input ->
					load(input.toString())
				}
				.negativeText(android.R.string.cancel)
				.positiveText(android.R.string.search_go)
				.show()
	} else load(query)
}