
package ankh.eranews.add.set

import android.graphics.Typeface
import android.view.View
import ankh.eranews.Itcannotbeworse.resClr
import ankh.eranews.Itcannotbeworse.textView
import ankh.eranews.add.channels.Itnaverhu
import ankh.readit.R
import org.jetbrains.anko.*

class Verhit : AnkoComponent<Itnaverhu> {
	override fun createView(ui: AnkoContext<Itnaverhu>): View = with(ui) {
		verticalLayout {
			textView {
				lparams(width = wrapContent, height = wrapContent) {
					margin = dip(16)
				}
				id = R.id.headerrecycleritem_textview
				textColor = R.color.colorAccent.resClr(context)!!
				textSize = 16f
				setTypeface(typeface, Typeface.BOLD)
			}
		}
	}
}