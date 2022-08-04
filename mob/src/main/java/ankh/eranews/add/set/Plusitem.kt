
package ankh.eranews.add.set

import android.graphics.Color
import android.view.View
import ankh.eranews.Itcannotbeworse.button
import ankh.eranews.Itcannotbeworse.resStr
import ankh.eranews.add.channels.Plusitemi
import ankh.readit.R
import org.jetbrains.anko.*

class Plusitem : AnkoComponent<Plusitemi> {
	override fun createView(ui: AnkoContext<Plusitemi>): View = with(ui) {
		verticalLayout {
			button {
				lparams(width = wrapContent, height = dip(48)) {
					horizontalMargin = dip(16)
					verticalMargin = dip(8)
				}
				id = R.id.morerecycleritem_button
				maxLines = 1
				text = R.string.more.resStr()
				textColor = Color.WHITE
			}
		}
	}
}