
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
import ankh.eranews.exported.Name
import ankh.eranews.exported.launcher.Forfeedly
import ankh.eranews.Itcannotbeworse.nF
import ankh.eranews.add.set.Obnovit
import ankh.eranews.add.channels.Worseit
import ankh.eranews.add.vid.Swipe
import ankh.readit.R
import ankh.Forview.Sams
import ankh.eranews.Itcannotbeworse.nF
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

@SuppressLint("ViewConstructor")
class Vidvmeste(val fId: String) : Sams() {
	private var fVw: View? = null
	private val rO: RecyclerView? by lazy { fVw?.find<RecyclerView>(R.id.refreshrecyclerview_recycler) }
	private val aAd = ItemAdapter<Worseit>()
	private val rfO: Swipe? by lazy { fVw?.find<Swipe>(R.id.refreshrecyclerview_refresh) }
	private var art = listOf<Name>()
	private var fld: Forfeedly? = null

	override fun onCreateView(): View? {
		super.onCreateView()
		fVw = Obnovit().createView(AnkoContext.create(context, this))
		rfO?.setOnRefreshListener {
			lart(false)
		}
		fld = Forfeedly().apply {
			type = Forfeedly.FT.MIX
			fU = fId
		}
		if (rO?.adapter == null) {
			val adapter: FastAdapter<Worseit> = FastAdapter.with(aAd)
			rO?.adapter = adapter
		}
		lart(true)
		return fVw
	}

	private fun lart(cache: Boolean = false) = async {
		rfO?.showIndicator()
		await { fld?.ims(cache)?.let { art = it } }
		if (!art.isEmpty()) aAd.setNewList(art.map { Worseit(nm = it, fr = this@Vidvmeste) })
		else context.nF { cV() }
		rfO?.hideIndicator()
	}

	override fun inflateMenu(inflater: MenuInflater, menu: Menu?) {
		super.inflateMenu(inflater, menu)
		inflater.inflate(R.menu.mixfragment, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem?) {
		when (item?.itemId) {
			R.id.refresh -> lart(false)
		}
	}
}
