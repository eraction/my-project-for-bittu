/*
~ 2018
 ~ Andrey Khokhlov
 ~ Program ready to use. Version 1.0>.
*/
package ankh.eranews.exported.launcher

import ankh.eranews.exported.Name
import ankh.eranews.exported.feed.News
import ankh.eranews.exported.added.gCAL
import ankh.eranews.exported.added.iALC
import ankh.eranews.exported.added.readFromCache
import ankh.eranews.exported.added.saveToCache

class Forfeedly {
	var type: FT? = null
	var cnt = 20
	var qr: String? = null
	var fU: String? = null
	var mmr: Ranked = Ranked.NEWEST
	var cnn: String? = null

	fun ims(cache: Boolean): List<Name>? = when (type) {
		FT.MIX -> {
			var imm: News.Ids? = if (cache) readFromCache("MixIds$fU" + when (mmr) {
				Ranked.OLDEST -> "oldest"
				else -> ""
			}, News.Ids::class.java) else null
			if (imm == null) {
				imm = News().mX(fU, cnt).apply {
					saveToCache("MixIds$fU" + when (mmr) {
						Ranked.OLDEST -> "oldest"
						else -> ""
					})
				}
			}
			isds(imm?.ids, cache)
		}
		FT.FEED -> {
			var ids: News.Ids? = if (cache) readFromCache("StreamIds$fU" + when (mmr) {
				Ranked.OLDEST -> "oldest"
				else -> ""
			}, News.Ids::class.java) else null
			if (ids == null) {
				ids = News().sI(fU, cnt, null, when (mmr) {
					Ranked.NEWEST -> "newest"
					Ranked.OLDEST -> "oldest"
				}).apply {
					saveToCache("StreamIds$fU" + when (mmr) {
						Ranked.OLDEST -> "oldest"
						else -> ""
					})
				}
			}
			cnn = ids?.cnn
			isds(ids?.ids, cache)
		}
		FT.SEARCH -> News().aS(fU, qr)?.ims
		else -> null
	}?.onEach { it.process().saveToCache() }

	fun mI(): List<Name>? = isds(
			News().sI(fU, cnt, cnn, when (mmr) {
				Ranked.NEWEST -> "newest"
				Ranked.OLDEST -> "oldest"
			})?.apply {
				this@Forfeedly.cnn = cnn
			}?.ids, true
	)?.onEach { it.process().saveToCache() }

	private fun isds(ids: Array<String>?, cache: Boolean): List<Name>? = if (ids != null && ids.isNotEmpty()) {
		ids.filter { if (cache) !iALC(it) else true }.let {
			News().et(it)?.forEach { it.saveToCache() }
		}
		ids.mapNotNull { gCAL(it) }
	} else null

	enum class FT { FEED, SEARCH, MIX }
	enum class Ranked { NEWEST, OLDEST }

}
