
package ankh.eranews.add.set

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.View
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.JustifyContent
import ankh.eranews.Itcannotbeworse.flexboxLayout
import ankh.eranews.Itcannotbeworse.imageView
import ankh.eranews.Itcannotbeworse.setTextStyle
import ankh.eranews.Itcannotbeworse.textView
import ankh.eranews.Itcannotbeworse.zoomTextView
import ankh.eranews.add.mini.Title
import ankh.readit.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class Worsetitle : AnkoComponent<Title> {
	@SuppressLint("PrivateResource")
	override fun createView(ui: AnkoContext<Title>): View = with(ui) {
		nestedScrollView {
			id = R.id.articlefragment_scrollview
			verticalLayout {
				lparams(width = matchParent, height = wrapContent)
				textView {
					lparams(width = matchParent, height = wrapContent) {
						topMargin = dip(32)
						bottomMargin = dip(8)
						horizontalMargin = dip(16)
					}
					id = R.id.articlefragment_title
					setTextStyle(context, R.style.TextAppearance_AppCompat_Headline)
					setTypeface(typeface, Typeface.BOLD)
				}
				imageView {
					lparams(width = matchParent, height = wrapContent) {
						bottomMargin = dip(8)
						horizontalMargin = dip(16)
					}
					id = R.id.articlefragment_visual
					adjustViewBounds = true
				}
				textView {
					lparams(width = matchParent, height = wrapContent) {
						bottomMargin = dip(8)
						horizontalMargin = dip(16)
					}
					id = R.id.articlefragment_details
					setTextStyle(context, R.style.TextAppearance_AppCompat_Caption)
				}
				zoomTextView {
					lparams(width = matchParent, height = wrapContent) {
						bottomMargin = dip(8)
						horizontalMargin = dip(16)
					}
					id = R.id.articlefragment_content
					setTextStyle(context, R.style.TextAppearance_AppCompat_Body1)
					setTextIsSelectable(true)
					textSize = 16f
				}
				textView {
					lparams(width = matchParent, height = wrapContent) {
						bottomMargin = dip(8)
						horizontalMargin = dip(16)
					}
					textResource = R.string.article_tip_zoom
					setTextStyle(context, R.style.TextAppearance_AppCompat_Caption)
					setTypeface(typeface, Typeface.ITALIC)
				}
				flexboxLayout {
					lparams(width = matchParent, height = wrapContent) {
						bottomMargin = dip(16)
						horizontalPadding = dip(12)
						visibility = View.GONE
					}
					id = R.id.articlefragment_tagsbox
					flexWrap = FlexWrap.WRAP
					justifyContent = JustifyContent.FLEX_START
				}
			}
		}
	}
}
