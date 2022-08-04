/*
~ 2018
 ~ Andrey Khokhlov
 ~ Program ready to use. Version 1.0>.
*/

package ankh.eranews.Itcannotbeworse

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.content.res.AppCompatResources
import android.text.Html
import android.text.Spanned
import ankh.eranews.aC
import ankh.eranews.exported.Checkfeed
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import javax.xml.parsers.SAXParserFactory

fun String.cOTF() = tON {
	mutableListOf<Checkfeed>().apply {
		SAXParserFactory.newInstance().newSAXParser().xmlReader.apply {
			contentHandler = object : DefaultHandler() {
				@Throws(SAXException::class)
				override fun startElement(uri: String, localName: String, xT: String, attributes: Attributes) {
					if (xT.equals("outline", ignoreCase = true) && attributes.getValue("xmlUrl") != null) {
						add(Checkfeed(
								te = attributes.getValue("title"),
								fId = attributes.getValue("xmlUrl")
						))
					}
				}
			}
			parse(InputSource(this@cOTF.byteInputStream()))
		}
	}.toTypedArray()
}


fun String.bE(words: Int) = split(" ").toMutableList().filter { !it.isNullOrBlank() && it != "\n" }.take(words).joinToString(separator = " ", postfix = "...").trim()

fun String?.bN() = if (isNullOrBlank()) null else this

fun <T> Array<out T>?.nN() = this != null && isNotEmpty()

fun <T> Collection<T>?.nN() = this != null && isNotEmpty()

fun String.cH(): String? = if (!isNullOrBlank()) Jsoup.clean(this, Whitelist.basic()) else this

fun String.toHtml(): Spanned = if (android.os.Build.VERSION.SDK_INT < 24) {
	@Suppress("DEPRECATION")
	Html.fromHtml(this)
} else {
	Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
}

fun <T> tON(print: Boolean = false, execute: Boolean = true, code: () -> T): T? = try {
	if (execute) code() else null
} catch (e: Exception) {
	if (print) e.printStackTrace()
	null
}

fun sharedPref(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(aC)

fun Int.resStr() = tON { aC?.resources?.getString(this) }

fun Int.resStrArr(): Array<out String>? = tON { aC?.resources?.getStringArray(this) }

fun Int.resIntArr() = tON { aC?.resources?.getIntArray(this) }

fun Int.resDrw(context: Context?, color: Int? = null) = tON {
	AppCompatResources.getDrawable(context ?: aC!!, this)?.apply {
		if (color != null) DrawableCompat.setTint(this, color)
	}
}

fun Int.resClr(context: Context?) = tON { ContextCompat.getColor(context ?: aC!!, this) }