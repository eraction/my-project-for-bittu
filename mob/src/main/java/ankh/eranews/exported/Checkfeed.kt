/*
~ 2018
 ~ Andrey Khokhlov
 ~ Program ready to use. Version 1.0>.
*/

package ankh.eranews.exported

import android.support.annotation.Keep
import com.afollestad.bridge.annotations.ContentType

@ContentType("application/json")
@Keep
class Checkfeed(
		var te: String? = null,
		var id: String? = null,
		var fId: String? = null,
		var web: String? = null
) {
	fun site() = (id ?: fId)?.replace("^checkfeed/".toRegex(), "")
}