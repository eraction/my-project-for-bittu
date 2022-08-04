
package ankh.eranews.add.set

import android.graphics.Color
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CoordinatorLayout
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import ankh.eranews.add.activity.Activity
import ankh.readit.R
import org.jetbrains.anko.*


class Worsetivity : AnkoComponent<Activity> {
	override fun createView(ui: AnkoContext<Activity>): View = with(ui) {
		include<CoordinatorLayout>(R.layout.mainactivity) {
			find<AppBarLayout>(R.id.mainactivity_appbar).apply {
				fitsSystemWindows = true
			}
			find<CollapsingToolbarLayout>(R.id.mainactivity_collapsingtoolbar).apply {
				fitsSystemWindows = true
				setContentScrimResource(R.color.colorPrimary)
				expandedTitleMarginBottom = dip(64)
				expandedTitleMarginStart = dip(16)
			}
			find<TextView>(R.id.mainactivity_toolbarsubtitle).apply {
				ellipsize = TextUtils.TruncateAt.END
				maxLines = 2
				textColor = Color.parseColor("#B3FFFFFF")
				textSize = 14f
			}
		}
	}

}
