
package ankh.eranews.add.mini

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import ankh.eranews.exported.Name
import ankh.eranews.Itcannotbeworse.nN
import ankh.eranews.add.set.Sc1
import ankh.eranews.add.channels.Worseit
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

@SuppressLint("ViewConstructor")
class Beyond(val names: List<Name>) : Sams() {
	private var fVw: View? = null
	private val rO: RecyclerView? by lazy { fVw?.find<RecyclerView>(R.id.basicrecyclerview_recycler) }
	private var artAd = ItemAdapter<Worseit>()

	override fun onCreateView(): View? {
		super.onCreateView()
		fVw = Sc1().createView(AnkoContext.create(context, this))
		if (rO?.adapter == null) {
			val adapter: FastAdapter<Worseit> = FastAdapter.with(artAd)
			rO?.adapter = adapter
		}
		if (names.nN()) artAd.setNewList(names.map { Worseit(it, this@Beyond) })
		return fVw
	}
}