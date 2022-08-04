
package ankh.eranews.add.set

import android.graphics.Color
import android.view.Gravity
import android.view.View
import ankh.readit.R
import org.jetbrains.anko.*

class Vidtega : AnkoComponent<View> {
	override fun createView(ui: AnkoContext<View>): View = with(ui) {
		frameLayout {
			lparams(width = wrapContent, height = wrapContent) {
				padding = dip(4)
			}
			verticalLayout {
				backgroundResource = R.drawable.chip
				textView {
					id = R.id.tag_text
					gravity = Gravity.CENTER
					textColor = Color.WHITE
					textSize = 13f
				}.lparams(width = wrapContent, height = dip(32)) {
					horizontalPadding = dip(12)
				}
			}.lparams(width = wrapContent, height = wrapContent)
		}
	}
}