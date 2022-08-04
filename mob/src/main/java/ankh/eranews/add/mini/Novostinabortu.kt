
@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.add.mini

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import co.metalab.asyncawait.async
import com.afollestad.materialdialogs.MaterialDialog
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter_extensions.items.ProgressItem
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener
import ankh.eranews.exported.Name
import ankh.eranews.exported.Checkfeed
import ankh.eranews.exported.added.Db
import ankh.eranews.exported.launcher.Forfeedly
import ankh.eranews.Itcannotbeworse.*
import ankh.eranews.add.activity.Activity
import ankh.eranews.add.set.Obnovit
import ankh.eranews.add.channels.Worseit
import ankh.eranews.add.vid.Swipe
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.*

@SuppressLint("ViewConstructor")
class Novostinabortu(val cf: Checkfeed) : Sams() {
	private var fVw: View? = null
	private val rO: RecyclerView? by lazy { fVw?.find<RecyclerView>(R.id.refreshrecyclerview_recycler) }
	private val aAd = ItemAdapter<Worseit>()
	private val fAdp = ItemAdapter<ProgressItem>()
	private val rfO: Swipe? by lazy { fVw?.find<Swipe>(R.id.refreshrecyclerview_refresh) }
	private var art = mutableListOf<Name>()
	private val fav
		get() = Db.isSavedFavorite(cf.site())
	private var fL: Forfeedly? = null
	private var eMIt: MenuItem? = null
	private var cnn: String? = null
	private var MMR: String? = null

	override fun onCreateView(): View? {
		super.onCreateView()
		fVw= Obnovit().createView(AnkoContext.create(context, this))
		rfO?.setOnRefreshListener { lart() }
		if (rO?.adapter == null) {
			val adapter: FastAdapter<Worseit> = FastAdapter.with(listOf(aAd, fAdp))
			rO?.adapter = adapter
		}
		fL = Forfeedly().apply {
			type = Forfeedly.FT.FEED
			fU = "checkfeed/" + cf.site()
			cnn = this@Novostinabortu.cnn
			mmr = when (this@Novostinabortu.MMR) {
				"oldest" -> Forfeedly.Ranked.OLDEST
				else -> Forfeedly.Ranked.NEWEST
			}
		}
		lart(true)
		Db.aLF(cf)
		context.sendBroadcast(Intent("feed_state"))
		return fVw
	}

	private fun lart(cache: Boolean = false) = async {
		rfO?.showIndicator()
		if (art.isEmpty() || !cache) await {
			fL?.ims(cache)?.let {
				art = it.toMutableList()
			}
			cnn = fL?.cnn
		}
		if (art.nN()) {
			rO?.clearOnScrollListeners()
			aAd.setNewList(art.map { Worseit(nm = it, fr = this@Novostinabortu) })
			rO?.addOnScrollListener(object : EndlessRecyclerOnScrollListener(fAdp) {
				override fun onLoadMore(currentPage: Int) {
					async {
						val newArt = await { fL?.mI() }
						cnn = fL?.cnn
						if (newArt != null && newArt.isNotEmpty()) {
							art.addAll(newArt)
							aAd.add(newArt.map { Worseit(nm = it, fr = this@Novostinabortu) })
						}
					}
				}
			})
		} else context.nF {
			cV()
		}
		rfO?.hideIndicator()
	}

	private fun cHSS(title: String, feedId: String) {
		Intent().apply {
			@Suppress("DEPRECATION")
			putExtras(bundleOf("duplicate" to false, Intent.EXTRA_SHORTCUT_INTENT to context.intentFor<Activity>("feedtitle" to title, "feedid" to feedId).newTask().clearTop(), Intent.EXTRA_SHORTCUT_NAME to title, Intent.EXTRA_SHORTCUT_ICON_RESOURCE to Intent.ShortcutIconResource.fromContext(context.applicationContext, R.mipmap.ic_launcher)))
			action = "com.android.launcher.action.INSTALL_SHORTCUT"
		}.let { context.applicationContext.sendBroadcast(it) }
	}

	override fun inflateMenu(inflater: MenuInflater, menu: Menu?) {
		super.inflateMenu(inflater, menu)
		inflater.inflate(R.menu.feedfragment, menu)
		menu?.findItem(R.id.favorite)?.icon = (if (fav) R.drawable.ic_favorite_universal else R.drawable.ic_favorite_border_universal).resDrw(context, Color.WHITE)
		eMIt = menu?.findItem(R.id.edit_title)
		eMIt?.isVisible = fav
	}

	override fun onOptionsItemSelected(item: MenuItem?) {
		when (item?.itemId) {
			R.id.favorite -> {
				if (!fav) Db.aV(cf)
				else Db.delFav(cf.site())
				item.icon = (if (fav) R.drawable.ic_favorite_universal else R.drawable.ic_favorite_border_universal).resDrw(context, Color.WHITE)
				eMIt?.isVisible = fav
			}
			R.id.sort -> {
				MaterialDialog.Builder(context)
						.title(R.string.sort)
						.items(R.string.newest_first.resStr(), R.string.oldest_first.resStr())
						.itemsCallbackSingleChoice(when (MMR) {
							"oldest" -> 1
							else -> 0
						}) { _, _, which, _ ->
							fL?.apply {
								cnn = ""
								mmr = when (which) {
									1 -> Forfeedly.Ranked.OLDEST
									else -> Forfeedly.Ranked.NEWEST
								}
							}
							art.clear()
							lart()
							MMR = when (fL?.mmr) {
								Forfeedly.Ranked.OLDEST -> "oldest"
								else -> "newest"
							}
							true
						}
						.positiveText(R.string.set)
						.negativeText(android.R.string.cancel)
						.show()
			}
			R.id.search -> {
				val pD = context.progressDialog()
				MaterialDialog.Builder(context)
						.title(android.R.string.search_go)
						.input(null, null, { _, query ->
							async {
								pD.show()
								val foundArticles = await {
									Forfeedly().apply {
										type = Forfeedly.FT.SEARCH
										fU = "checkfeed/" + cf.site()
										this.qr = query.toString()
									}.ims(false)
								}
								pD.dismiss()
								if (foundArticles.nN()) oV(Beyond(names = foundArticles!!).withTitle("Results for " + query.toString()))
								else context.nF()
							}
						})
						.negativeText(android.R.string.cancel)
						.positiveText(android.R.string.search_go)
						.show()
			}
			R.id.refresh -> lart()
			R.id.edit_title -> {
				MaterialDialog.Builder(context)
						.title(R.string.edit_feed_title)
						.input(null, cf.te, { _, input ->
							if (!input.toString().isBlank()) {
								Db.uFT(cf.site(), input.toString())
								cf.te = input.toString()
								title = cf.te
								(context as? Activity)?.rFDT(this)
							}
						})
						.negativeText(android.R.string.cancel)
						.positiveText(android.R.string.ok)
						.show()
			}
			R.id.create_shortcut -> {
				cHSS(title ?: R.string.app_name.resStr()!!, cf.site() ?: "")
				Snackbar.make(contentView, R.string.shortcut_created, Snackbar.LENGTH_SHORT).show()
			}
		}
	}
}
