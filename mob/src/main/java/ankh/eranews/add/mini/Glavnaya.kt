
@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.add.mini

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.widget.RecyclerView
import android.view.View
import co.metalab.asyncawait.async
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import ankh.eranews.exported.Checkfeed
import ankh.eranews.exported.feed.News
import ankh.eranews.exported.added.Db
import ankh.eranews.exported.added.Fix
import ankh.eranews.Itcannotbeworse.nN
import ankh.eranews.Itcannotbeworse.resStr
import ankh.eranews.Itcannotbeworse.sFF
import ankh.eranews.Itcannotbeworse.tON
import ankh.eranews.add.aapt2.Customres
import ankh.eranews.add.aapt2.Boolres
import ankh.eranews.add.set.Glavnayavid
import ankh.eranews.add.channels.*
import ankh.eranews.add.vid.Swipe
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

@SuppressLint("ViewConstructor")
class Glavnaya : Sams(), Customres, Boolres {
	private var rCf: Array<Checkfeed>? = null
	private var rRl: Array<String>? = null
	private var fVw: View? = null
	private val rO: RecyclerView? by lazy { fVw?.find<RecyclerView>(R.id.homefragment_recyclerone) }
	private val lHA = ItemAdapter<Itnaverhu>()
	private val lAd = ItemAdapter<Podfeedit>()
	private val lFAd = ItemAdapter<Plusitemi>()
	private val fHAd = ItemAdapter<Itnaverhu>()
	private val fAd = ItemAdapter<Podfeedit>()
	private val fFAd = ItemAdapter<Plusitemi>()
	private val rHAd = ItemAdapter<Itnaverhu>()
	private val recA = ItemAdapter<Podfeedit>()
	private val rFAd = ItemAdapter<Plusitemi>()
	private val rTAd = ItemAdapter<TagsRecyclerItem>()
	private val refresh: Swipe? by lazy { fVw?.find<Swipe>(R.id.homefragment_refresh) }
	private var fUR : FSUR? = null
	private var lFRReg = false

	override val fabDrawable = R.drawable.ic_search

	override val fabClick = {
		sFF(context)
	}

	override val expanded = true

	override fun onCreateView(): View? {
		super.onCreateView()
		if (!lFRReg) {
			fUR = FSUR(this)
			context.registerReceiver(fUR, IntentFilter("feed_state"))
			lFRReg = true
		}
		fVw = Glavnayavid().createView(AnkoContext.create(context, this))
		refresh?.setOnRefreshListener { ldAll(false) }
		ldAll(true)
		return fVw
	}

	private fun ldAll(cache: Boolean = true) {
		if (rO?.adapter == null) {
			val adapter: FastAdapter<Res<*, *>> = FastAdapter.with(listOf(lHA, lAd, lFAd, fHAd, fAd, fFAd, rHAd, recA, rFAd, rTAd))
			rO?.adapter = adapter
		}
		lLF()
		lFF()
		lRF(cache)
	}

	private fun lLF() = async {
		lHA.setNewList(listOf())
		lAd.setNewList(listOf())
		lFAd.setNewList(listOf())
		if (Fix.sRF) {
			val lF = await { Db.aLCf.takeLast(5).reversed() }
			if (lF.nN()) {
				lHA.add(Itnaverhu(te = R.string.last_feeds.resStr()!!))
				lAd.add(lF.mapIndexed { i, feed -> Podfeedit(cf = feed, fl = this@Glavnaya, iL = i == lF.lastIndex) })
				lFAd.add(Plusitemi { oV(Newsonboard(cf = Db.aLCf.reversed().toTypedArray()).withTitle(R.string.last_feeds.resStr())) })
			}
		}
	}

	private fun lFF() = async {
		fHAd.setNewList(listOf())
		fAd.setNewList(listOf())
		fFAd.setNewList(listOf())
		val fF = await { Db.aFav.take(5) }
		if (fF.nN()) {
			fHAd.add(Itnaverhu(te = R.string.favorites.resStr()!!))
			fAd.add(fF.mapIndexed { i, feed -> Podfeedit(cf = feed, fl = this@Glavnaya, iL = i == fF.lastIndex) })
			fFAd.add(Plusitemi { oV(Izbrannoe().withTitle(R.string.favorites.resStr())) })
		}
	}

	private fun lRF(cache: Boolean = false) = async {
		rHAd.setNewList(listOf())
		recA.setNewList(listOf())
		rFAd.setNewList(listOf())
		rTAd.setNewList(listOf())
		if (Fix.sRFd) {
			refresh?.showIndicator()
			await {
				if (rCf == null || !cache) News().rF(cache = cache) { feeds, related ->
					rCf = feeds
					rRl = related
				}
			}
			val tRF = rCf?.take(15)
			if (rCf.nN() && tRF != null) {
				rHAd.add(Itnaverhu(te = R.string.recommendations.resStr()!!))
				recA.add(tRF.mapIndexed { i, feed -> Podfeedit(cf = feed, fl = this@Glavnaya, iL = i == tRF.lastIndex) })
				rFAd.add(listOf(Plusitemi {
					oV(Newsonboard(cf = rCf, tags = rRl).withTitle(R.string.recommendations.resStr()))
				}))
			}
			if (rRl.nN()) rTAd.add(TagsRecyclerItem(rRl, this@Glavnaya))
			refresh?.hideIndicator()
		}
	}

	override fun onDestroy() {
		tON { context.unregisterReceiver(fUR) }
		super.onDestroy()
	}

	private class FSUR(val glavnaya: Glavnaya) : BroadcastReceiver() {
		override fun onReceive(context: Context?, intent: Intent?) {
			glavnaya.ldAll()
		}
	}
}