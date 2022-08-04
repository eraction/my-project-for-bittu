
package ankh.eranews.add.set

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import ankh.eranews.Itcannotbeworse.swipeRefreshLayout
import ankh.eranews.add.mini.Glavnaya
import ankh.readit.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.wrapContent

class Glavnayavid : AnkoComponent<Glavnaya> {
	override fun createView(ui: AnkoContext<Glavnaya>): View = with(ui) {
		swipeRefreshLayout {
			id = R.id.homefragment_refresh
			recyclerView {
				lparams(width = matchParent, height = wrapContent)
				id = R.id.homefragment_recyclerone
				layoutManager = LinearLayoutManager(context)
			}
		}
	}
}
