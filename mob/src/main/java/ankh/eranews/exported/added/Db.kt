
@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.exported.added

import ankh.keys.Keys
import ankh.eranews.aC
import ankh.eranews.exported.Name
import ankh.eranews.exported.Checkfeed
import ankh.eranews.Itcannotbeworse.tON

/**
 * База
 */
object Db {

	private val FAV = "feeds_database"
	private val favs = Keys(aC!!, FAV)
	private val BM = "bookmarks_database"
	private val bms = Keys(aC!!, BM)
	private val RD_U = "site_database"
	private val rdUs = Keys(aC!!, RD_U)
	private val L_F = "last_feeds"
	private val lfs = Keys(aC!!, L_F)

	private fun Checkfeed?.safeFavorite() = this != null && !this.site().isNullOrBlank()

	var aFav: Array<Checkfeed>
		get() = favs.read(FAV, Array<Checkfeed>::class.java) ?: arrayOf()
		set(value) {
			tON { favs.write<Array<Checkfeed>>(FAV, value.filter { it.safeFavorite() }.distinctBy { it.site() }.toTypedArray()) }
		}

	private val aFU
		get() = aFav.map { it.site() }

	fun aV(vararg checkfeeds: Checkfeed?) {
		aFav += checkfeeds.filterNotNull().filter { !isSavedFavorite(it.site()) }
	}

	fun delFav(url: String?) {
		aFav = aFav.filter { it.site() != url }.toTypedArray()
	}

	fun uFT(feedUrl: String?, newTitle: String?) {
		if (!feedUrl.isNullOrBlank() && !newTitle.isNullOrBlank()) {
			aFav = aFav.toMutableList().onEach {
				if (it.site() == feedUrl) it.te = newTitle
			}.toTypedArray()
		}
	}

	private fun Name?.safeBookmark() = this != null && !this.url.isNullOrBlank()

	var aBm: Array<Name>
		get() = bms.read(BM, Array<Name>::class.java) ?: arrayOf()
		set(value) {
			tON { bms.write<Array<Name>>(BM, value.filter { it.safeBookmark() }.distinctBy { it.url }.toTypedArray()) }
		}

	private val aBU
		get() = aBm.map { it.url }

	private fun aBmk(vararg names: Name?) {
		aBm += names.filterNotNull().filter { !isSavedBookmark(it.url) }
	}

	fun addBookmark(name: Name?) {
		tON(execute = name.safeBookmark()) {
			aBmk(name)
		}
	}

	fun delBm(url: String?) {
		tON(execute = !url.isNullOrBlank()) {
			aBm = aBm.filter { it.url != url }.toTypedArray()
		}
	}

	var aRU: Array<String>
		get() = rdUs.read(RD_U, Array<String>::class.java) ?: arrayOf()
		set(value) {
			tON { rdUs.write<Array<String>>(RD_U, value.distinct().toTypedArray()) }
		}

	fun aRDU(url: String?) = url?.let { aRU += it }

	private fun Checkfeed?.safeLastFeed() = this != null && !this.site().isNullOrBlank()

	var aLCf: Array<Checkfeed>
		get() = lfs.read(L_F, Array<Checkfeed>::class.java) ?: arrayOf()
		set(value) {
			tON { lfs.write<Array<Checkfeed>>(L_F, value.filter { it.safeLastFeed() }.toTypedArray()) }
		}

	fun aLF(checkfeed: Checkfeed?) {
		if (checkfeed != null) {
			aLCf = aLCf.filter { it.site() != checkfeed.site() }.toTypedArray()
			aLCf += checkfeed
		}
	}

	fun isSavedFavorite(url: String?) = aFU.contains(url)

	fun isSavedBookmark(url: String?) = aBU.contains(url)

	fun isSavedReadUrl(url: String?) = aRU.contains(url)

}
