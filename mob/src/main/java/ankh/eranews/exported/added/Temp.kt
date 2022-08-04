/*
 ~ 2018
  ~ Andrey Khokhlov
  ~ Program ready to use. Version 1.0>.
 */

@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.exported.added

import android.content.Context
import co.metalab.asyncawait.async
import com.bumptech.glide.Glide
import ankh.keys.Keys
import ankh.eranews.aC
import ankh.eranews.exported.Name
import ankh.eranews.Itcannotbeworse.tON

fun <T> T?.saveToCache(key: String?) = Keys(aC!!, nm = "cache", ch = true).write<T>(key?.formatForCache(), this)

fun <T> readFromCache(key: String?, type: Class<T>): T? = Keys(aC!!, nm = "cache", ch = true).read(key?.formatForCache(), type)

fun String.formatForCache(): String = tON { replace("[^0-9a-zA-Z]".toRegex(), "") } ?: this

fun iALC(id: String): Boolean = Keys(aC!!, nm = "article_cache", ch = true).exists(id.formatForCache())

fun gCAL(id: String): Name? = tON {
	if (iALC(id)) Keys(aC!!, nm = "article_cache", ch = true).read(id.formatForCache(), Name::class.java)
	else null
}

fun Name.saveToCache() {
	if (!id.isNullOrBlank()) Keys(aC!!, nm = "article_cache", ch = true).write<Name>(id!!.formatForCache(), this)
}

fun Context.clearCache(finished: () -> Unit?) {
	async {
		await {
			Keys(aC!!, nm = "cache", ch = true).destroy()
			Keys(aC!!, nm = "article_cache", ch = true).destroy()
			Glide.get(this@clearCache).clearDiskCache()
		}
		finished()
	}
}