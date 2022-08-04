
package ankh.eranews.add.set

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.JustifyContent
import ankh.eranews.Itcannotbeworse.flexboxLayout
import ankh.eranews.Itcannotbeworse.imageView
import ankh.eranews.Itcannotbeworse.resClr
import ankh.eranews.Itcannotbeworse.setTextStyle
import ankh.eranews.Itcannotbeworse.textView
import ankh.eranews.Itcannotbeworse.view
import ankh.eranews.add.channels.Worseit
import ankh.readit.R
import org.jetbrains.anko.*

class Worseits : AnkoComponent<Worseit> {
	override fun createView(ui: AnkoContext<Worseit>): View = with(ui) {
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
					bottomPadding = dip(8)
				}
				orientation = LinearLayout.HORIZONTAL
				gravity = Gravity.TOP
				textView {
					lparams(width = 0, height = wrapContent, weight = 1f) {
						marginEnd = dip(16)
					}
					id = R.id.articlerecycleritem_title
					setTextStyle(context, R.style.TextAppearance_AppCompat_Medium)
					R.color.colorPrimaryText.resClr(context)?.let { textColor = it }
					typeface = Typeface.DEFAULT_BOLD
				}
				imageView {
					lparams(width = dip(36), height = dip(36)) {
						padding = dip(6)
					}
					id = R.id.articlerecycleritem_bookmark
					context.obtainStyledAttributes(intArrayOf(R.attr.selectableItemBackgroundBorderless)).apply {
						backgroundResource = getResourceId(0, 0)
					}.recycle()
				}
				imageView {
					lparams(width = dip(36), height = dip(36)) {
						padding = dip(6)
					}
					id = R.id.articlerecycleritem_share
					context.obtainStyledAttributes(intArrayOf(R.attr.selectableItemBackgroundBorderless)).apply {
						backgroundResource = getResourceId(0, 0)
					}.recycle()
				}
			}
			imageView {
				lparams(width = matchParent, height = wrapContent) {
					bottomMargin = dip(8)
				}
				adjustViewBounds = true
				id = R.id.articlerecycleritem_visual
			}
			textView {
				lparams(width = matchParent, height = wrapContent) {
					bottomMargin = dip(8)
				}
				id = R.id.articlerecycleritem_details
				setTextStyle(context, R.style.TextAppearance_AppCompat_Caption)
			}
			textView {
				lparams(width = matchParent, height = wrapContent) {
					bottomMargin = dip(8)
				}
				id = R.id.articlerecycleritem_content
				setTextStyle(context, R.style.TextAppearance_AppCompat_Body1)
			}
			flexboxLayout {
				lparams(width = matchParent, height = wrapContent) {
					bottomMargin = dip(16)
				}
				id = R.id.articlerecycleritem_tagsbox
				flexWrap = FlexWrap.WRAP
				justifyContent = JustifyContent.FLEX_START
			}
			view {
				lparams(width = matchParent, height = dip(1))
				backgroundColor = R.color.colorDivider.resClr(context)!!
			}
		}
	}
}