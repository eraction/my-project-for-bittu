
package ankh.eranews.add.set

import android.view.View
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.JustifyContent
import ankh.eranews.Itcannotbeworse.flexboxLayout
import ankh.eranews.add.channels.TagsRecyclerItem
import ankh.readit.R
import org.jetbrains.anko.*

class Tegiit : AnkoComponent<TagsRecyclerItem> {
	override fun createView(ui: AnkoContext<TagsRecyclerItem>): View = with(ui) {
		verticalLayout {
			flexboxLayout {
				lparams(width = matchParent, height = wrapContent) {
					padding = dip(12)
				}
				id = R.id.tagsrecycleritem_box
				flexWrap = FlexWrap.WRAP
				justifyContent = JustifyContent.FLEX_START
			}
		}
	}
}