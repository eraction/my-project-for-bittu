
package ankh.eranews.add.set

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import ankh.eranews.Itcannotbeworse.resClr
import ankh.eranews.Itcannotbeworse.setTextStyle
import ankh.eranews.Itcannotbeworse.textView
import ankh.eranews.add.channels.Textcs
import ankh.readit.R
import org.jetbrains.anko.*

class Sc2tx : AnkoComponent<Textcs> {
	override fun createView(ui: AnkoContext<Textcs>): View = with(ui) {
		verticalLayout {
			lparams(width = matchParent, height = wrapContent) {
				padding = dip(16)
			}
			textView {
				lparams(width = matchParent, height = wrapContent)
				id = R.id.customtextrecycleritem_text
				gravity = Gravity.CENTER
				setTextStyle(context, R.style.TextAppearance_AppCompat_Headline)
				setTypeface(typeface, Typeface.BOLD)
				R.color.colorSecondaryText.resClr(context)?.let { textColor = it }
			}
		}
	}
}