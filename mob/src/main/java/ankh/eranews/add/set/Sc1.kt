
package ankh.eranews.add.set

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.recyclerview.v7.recyclerView

class Sc1 : AnkoComponent<Sams> {
	override fun createView(ui: AnkoContext<Sams>): View = with(ui) {
		recyclerView {
			id = R.id.basicrecyclerview_recycler
			layoutManager = LinearLayoutManager(context)
		}
	}
}