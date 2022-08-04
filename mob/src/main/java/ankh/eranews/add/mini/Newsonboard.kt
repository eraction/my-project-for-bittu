
package ankh.eranews.add.mini

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import ankh.eranews.exported.Checkfeed
import ankh.eranews.Itcannotbeworse.nN
import ankh.eranews.add.set.Sc1
import ankh.eranews.add.channels.Podfeedit
import ankh.eranews.add.channels.Res
import ankh.eranews.add.channels.TagsRecyclerItem
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

@SuppressLint("ViewConstructor")
class Newsonboard(val cf: Array<Checkfeed>? = null, val tags: Array<String>? = null) : Sams() {
	private var fVw: View? = null
	private val rO: RecyclerView? by lazy { fVw?.find<RecyclerView>(R.id.basicrecyclerview_recycler) }
	private var fAd = ItemAdapter<Podfeedit>()
	private var tAd = ItemAdapter<TagsRecyclerItem>()

	override fun onCreateView(): View? {
		super.onCreateView()
		fVw = Sc1().createView(AnkoContext.create(context, this))
		if (rO?.adapter == null) {
			val adapter: FastAdapter<Res<*, *>> = FastAdapter.with(listOf(tAd, fAd))
			rO?.adapter = adapter
		}
		if (cf?.nN() == true) fAd.setNewList(cf.mapIndexed { i, feed -> Podfeedit(cf = feed, iL = i == cf.lastIndex, fl = this@Newsonboard) })
		else fAd.setNewList(listOf())
		if (tags.nN()) tAd.setNewList(listOf(TagsRecyclerItem(fl = this, cs = tags)))
		else tAd.setNewList(listOf())
		return fVw
	}

}
