
@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.add.mini

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter_extensions.drag.ItemTouchCallback
import com.mikepenz.fastadapter_extensions.drag.SimpleDragCallback
import com.mikepenz.fastadapter_extensions.utilities.DragDropUtil
import ankh.eranews.exported.Checkfeed
import ankh.eranews.exported.added.Db
import ankh.eranews.Itcannotbeworse.nN
import ankh.eranews.Itcannotbeworse.resStr
import ankh.eranews.add.set.Obnovit
import ankh.eranews.add.channels.Textcs
import ankh.eranews.add.channels.Podfeedit
import ankh.eranews.add.channels.Res
import ankh.eranews.add.vid.Swipe
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import java.util.*

@SuppressLint("ViewConstructor")
class Izbrannoe : Sams(), ItemTouchCallback {
	private var fVw: View? = null
	private val rO: RecyclerView? by lazy { fVw?.find<RecyclerView>(R.id.refreshrecyclerview_recycler) }
	private val fAd = ItemAdapter<Podfeedit>()
	private val eA = ItemAdapter<Textcs>()
	private val rfO: Swipe? by lazy { fVw?.find<Swipe>(R.id.refreshrecyclerview_refresh) }
	private var cf: MutableList<Checkfeed>? = null

	override fun onCreateView(): View? {
		super.onCreateView()
		fVw = Obnovit().createView(AnkoContext.create(context, this))
		ItemTouchHelper(SimpleDragCallback(this)).attachToRecyclerView(rO)
		if (rO?.adapter == null) {
			val adapter: FastAdapter<Res<*, *>> = FastAdapter.with(listOf(fAd, eA))
			rO?.adapter = adapter
		}
		rfO?.setOnRefreshListener { ld() }
		ld()
		return fVw
	}

	private fun ld() {
		cf = Db.aFav.toMutableList()
		if (cf?.nN() == true) {
			fAd.setNewList(cf!!.map { Podfeedit(it, fl = this@Izbrannoe) })
			eA.setNewList(listOf())
		} else {
			fAd.setNewList(listOf())
			eA.setNewList(listOf(Textcs(R.string.nothing_marked_favorite.resStr())))
		}
		rfO?.hideIndicator()
	}

	override fun itemTouchOnMove(oldPosition: Int, newPosition: Int): Boolean {
		DragDropUtil.onMove(fAd, oldPosition, newPosition)
		Collections.swap(cf, oldPosition, newPosition)
		cf?.let { Db.aFav = it.toTypedArray() }
		context.sendBroadcast(Intent("feed_state"))
		return true
	}

	override fun itemTouchDropped(p0: Int, p1: Int) {
	}

}