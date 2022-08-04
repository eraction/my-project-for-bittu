
package ankh.eranews.add.set

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import ankh.eranews.Itcannotbeworse.imageView
import ankh.eranews.Itcannotbeworse.resClr
import ankh.eranews.Itcannotbeworse.setTextStyle
import ankh.eranews.Itcannotbeworse.textView
import ankh.eranews.Itcannotbeworse.view
import ankh.eranews.add.channels.Podfeedit
import ankh.readit.R
import org.jetbrains.anko.*

class Podfeedtext : AnkoComponent<Podfeedit> {
	override fun createView(ui: AnkoContext<Podfeedit>): View = with(ui) {
		verticalLayout {
			lparams(width = matchParent, height = wrapContent) {
				topPadding = dip(16)
				horizontalPadding = dip(16)
			}
			context.obtainStyledAttributes(intArrayOf(R.attr.selectableItemBackground)).apply {
				backgroundResource = getResourceId(0, 0)
			}.recycle()
			verticalLayout {
				lparams(width = matchParent, height = wrapContent) {
					bottomPadding = dip(16)
				}
				orientation = LinearLayout.HORIZONTAL
				gravity = Gravity.TOP
				verticalLayout {
					lparams(width = 0, height = wrapContent, weight = 1f) {
						marginEnd = dip(16)
					}
					textView {
						lparams(width = matchParent, height = wrapContent)
						id = R.id.feedrecycleritem_title
						setTextStyle(context, R.style.TextAppearance_AppCompat_Medium)
						R.color.colorPrimaryText.resClr(context)?.let { textColor = it }
						typeface = Typeface.DEFAULT_BOLD
					}
					textView {
						lparams(width = matchParent, height = wrapContent)
						id = R.id.feedrecycleritem_website
						setTextStyle(context, R.style.TextAppearance_AppCompat_Small)
						textColor = R.color.colorSecondaryText.resClr(context)!!
					}
				}
				imageView {
					lparams(width = dip(36), height = dip(36)) {
						padding = dip(6)
					}
					id = R.id.feedrecycleritem_favorite
					context.obtainStyledAttributes(intArrayOf(R.attr.selectableItemBackgroundBorderless)).apply {
						backgroundResource = getResourceId(0, 0)
					}.recycle()
				}
			}
			view {
				lparams(width = matchParent, height = dip(1))
				id = R.id.feedrecycleritem_divider
				backgroundColor = R.color.colorDivider.resClr(context)!!
			}
		}
	}
}