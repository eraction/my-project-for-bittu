
@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.add.mini

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import co.metalab.asyncawait.async
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import ankh.eranews.exported.added.Db
import ankh.eranews.Itcannotbeworse.nN
import ankh.eranews.Itcannotbeworse.resStr
import ankh.eranews.add.set.Obnovit
import ankh.eranews.add.channels.Worseit
import ankh.eranews.add.channels.Textcs
import ankh.eranews.add.channels.Res
import ankh.eranews.add.vid.Swipe
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

@SuppressLint("ViewConstructor")
class Zakladki : Sams() {
	private var fV: View? = null
	private val rO: RecyclerView? by lazy { fV?.find<RecyclerView>(R.id.refreshrecyclerview_recycler) }
	private val bmA = ItemAdapter<Worseit>()
	private val eA = ItemAdapter<Textcs>()
	private val rfO: Swipe? by lazy { fV?.find<Swipe>(R.id.refreshrecyclerview_refresh) }

	override fun onCreateView(): View? {
		super.onCreateView()
		fV = Obnovit().createView(AnkoContext.create(context, this))
		rfO?.setOnRefreshListener { lAr() }
		if (rO?.adapter == null) {
			val adapter: FastAdapter<Res<*, *>> = FastAdapter.with(listOf(bmA, eA))
			rO?.adapter = adapter
		}
		lAr()
		return fV
	}

	private fun lAr() = async {
		rfO?.showIndicator()
		val art = await { Db.aBm }
		if (art.nN()) {
			bmA.setNewList(art.map { Worseit(it, this@Zakladki) })
			eA.setNewList(listOf())
		} else {
			bmA.setNewList(listOf())
			eA.setNewList(listOf(Textcs(R.string.nothing_bookmarked.resStr())))
		}
		rfO?.hideIndicator()
	}

	override fun inflateMenu(inflater: MenuInflater, menu: Menu?) {
		super.inflateMenu(inflater, menu)
		inflater.inflate(R.menu.bookmarksfragment, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem?) {
		when (item?.itemId) {
			R.id.refresh -> {
				lAr()
			}
		}
	}
}
