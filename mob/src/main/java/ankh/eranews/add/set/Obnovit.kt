
package ankh.eranews.add.set

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import ankh.eranews.Itcannotbeworse.swipeRefreshLayout
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.recyclerview.v7.recyclerView

class Obnovit : AnkoComponent<Sams> {
	override fun createView(ui: AnkoContext<Sams>): View = with(ui) {
		swipeRefreshLayout {
			id = R.id.refreshrecyclerview_refresh
			recyclerView {
				id = R.id.refreshrecyclerview_recycler
				layoutManager = LinearLayoutManager(context)
			}
		}
	}
}