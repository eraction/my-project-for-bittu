/*
~ 2018
 ~ Andrey Khokhlov
 ~ Program ready to use. Version 1.0>.
*/

package ankh.eranews.Itcannotbeworse

import android.content.Context
import android.os.Build
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.bumptech.glide.Glide
import ankh.eranews.exported.feed.oU
import ankh.eranews.mA
import ankh.readit.R

fun View.hV() {
	visibility = View.GONE
}

fun View.mI() {
	visibility = View.INVISIBLE
}

fun View.sV() {
	visibility = View.VISIBLE
}

fun ImageView.loadImage(url: String?) {
	clearGlide()
	tON { Glide.with(this).load(url).into(this) }
}

fun ImageView.clearGlide() {
	tON { Glide.with(this).clear(this) }
}

fun Context.nF(callback: () -> Unit = {}) {
	MaterialDialog.Builder(this)
			.content(R.string.nothing_found)
			.positiveText(android.R.string.ok)
			.onAny { _, _ -> callback() }
			.cancelListener { callback() }
			.show()
}

fun TextView.setTextStyle(context: Context, id: Int) {
	@Suppress("DEPRECATION")
	if (Build.VERSION.SDK_INT < 23) setTextAppearance(context, id)
	else setTextAppearance(id)
}

fun TextView.applyLinks(amp: Boolean = true) {
	movementMethod = LinkMovementMethod.getInstance()
	text.let { tempText ->
		if (tempText is Spannable) {
			text = SpannableStringBuilder(tempText).apply {
				clearSpans()
				tempText.getSpans(0, text.length, URLSpan::class.java).forEach {
					setSpan(object : ClickableSpan() {
						override fun onClick(view: View?) {
							mA?.let { activity -> it.url.oU(activity, amp) }
						}
					}, tempText.getSpanStart(it), tempText.getSpanEnd(it), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
				}
			}
		}
	}
}

fun Context.progressDialog(): MaterialDialog = MaterialDialog.Builder(this).content(R.string.loading).cancelable(false).progress(true, 0).build()