/*
~ 2018
 ~ Andrey Khokhlov
 ~ Program ready to use. Version 1.0>.
*/
@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.add.activity

import android.app.SearchManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import ankh.eranews.exported.Checkfeed
import ankh.eranews.exported.feed.oU
import ankh.eranews.exported.feed.sharing
import ankh.eranews.exported.added.Fix
import ankh.eranews.cTHF
import ankh.eranews.Itcannotbeworse.*
import ankh.eranews.lT
import ankh.eranews.mA
import ankh.eranews.add.mini.Zakladki
import ankh.eranews.add.mini.Novostinabortu
import ankh.eranews.add.mini.Glavnaya
import ankh.eranews.add.mini.Vidnastroyki
import ankh.eranews.add.aapt2.Customres
import ankh.eranews.add.aapt2.Boolres
import ankh.eranews.add.set.Worsetivity
import ankh.readit.R
import ankh.Forview.SamActivity
import ankh.Forview.Sams
import me.toptas.fancyshowcase.FancyShowCaseQueue
import me.toptas.fancyshowcase.FancyShowCaseView
import me.zhanghai.android.customtabshelper.CustomTabsHelperFragment
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class Activity : SamActivity() {
	private val tb: Toolbar? by lazy { find<Toolbar>(R.id.mainactivity_toolbar) }
	private val ab: AppBarLayout? by lazy { find<AppBarLayout>(R.id.mainactivity_appbar) }
	private val button: FloatingActionButton? by lazy { find<FloatingActionButton>(R.id.mainactivity_fab) }
	private val ste: TextView? by lazy { find<TextView>(R.id.mainactivity_toolbarsubtitle) }
	val bNV: BottomNavigationView? by lazy { find<BottomNavigationView>(R.id.mainactivity_navigationview) }

	override val Sv: MutableList<MutableList<Sams>>
		get() = mutableListOf(
				mutableListOf(Glavnaya().withTitle(R.string.news.resStr())),
				mutableListOf(Zakladki().withTitle(R.string.bookmarks.resStr())),
				mutableListOf(Vidnastroyki().withTitle(R.string.settings.resStr()))
		)
	override val cV: FrameLayout
		get() = find(R.id.mainactivity_container)

	override fun onCreate(savedInstanceState: Bundle?) {
		mA = this
		Worsetivity().setContentView(this)
		super.onCreate(savedInstanceState)
		cTHF = CustomTabsHelperFragment.attachTo(this@Activity)
		setSupportActionBar(tb)
		bNV?.apply {
			selectedItemId = when (lT) {
				1 -> R.id.bm
				2 -> R.id.pref
				else -> R.id.feeds
			}
			setOnNavigationItemSelectedListener { it ->
				val cs = when (it.itemId) {
					R.id.feeds -> 0
					R.id.bm -> 1
					R.id.pref -> 2
					else -> 0
				}
				sS(cs)
				lT = cs
				true
			}
			setOnNavigationItemReselectedListener {
				rS()
			}
		}
		cFDT()
		hI(intent)
		sS(currentStack())
		if (!Fix.tutor) start()
	}

	override fun onsW() {
		super.onsW()
		cFDT()
	}

	private fun hI(intent: Intent?) {
		if (intent != null) {
			// ярлык
			intent.getStringExtra("gg")?.let {
				rS()
				val fte = intent.getStringExtra("feedtitle")
				if (!it.isBlank()) oV(Novostinabortu(cf = Checkfeed(fId = it, te = fte)).withTitle(fte))
			}
			// браузер
			if (intent.scheme == "http" || intent.scheme == "https") {
				intent.dataString?.let {
					MaterialDialog.Builder(this)
							.items(R.string.search_for_feeds.resStr(), R.string.this_is_article.resStr())
							.itemsCallback { _, _, i, _ ->
								when (i) {
									0 -> sFF(this, it)
									1 -> it.oU(this@Activity, nA = it)
								}
							}
							.show()
				}
			}
			// гугл войс поисковик
			if (intent.action == "search.com.google.voice.android.gms.actions.SEARCH_ACTION") {
				intent.getStringExtra(SearchManager.QUERY).let {
					sFF(this, it)
				}
			}
		}
	}

	private fun cFDT() {
		val cF = cW()
		// проверочка
		if (iRV()) {
			supportActionBar?.setDisplayHomeAsUpEnabled(false)
			ab?.setExpanded(if (cF is Boolres) cF.expanded ?: false else false)
		} else {
			supportActionBar?.setDisplayHomeAsUpEnabled(true)
			ab?.setExpanded(if (cF is Boolres) cF.expanded ?: true else true)
		}
		// проверка названия
		rFDT(cF)
		// проверка помощи в меню
		invalidateOptionsMenu()
		// проверка фрагмента
		if (cF is Customres) {
			button?.let {
				if (cF.fabDrawable != null) it.setImageDrawable(cF.fabDrawable?.resDrw(this, Color.WHITE))
				it.setOnClickListener { cF.fabClick() }
				it.sV()
				it.show()
			}
		} else {
			button?.mI()
		}
	}

	fun rFDT(fragment: Sams?) = tON {
		tb?.title = R.string.app_name.resStr()
		ste?.text = fragment?.title
	}

	fun start() {
		FancyShowCaseQueue()
				.add(FancyShowCaseView.Builder(this)
						.title(R.string.gg_0.resStr())
						.build())
				.add(FancyShowCaseView.Builder(this)
						.focusOn(bNV?.find(R.id.feeds))
						.title(R.string.gg_1.resStr())
						.build())
				.add(FancyShowCaseView.Builder(this)
						.focusOn(bNV?.find(R.id.bm))
						.title(R.string.gg_2.resStr())
						.build())
				.add(FancyShowCaseView.Builder(this)
						.focusOn(bNV?.find(R.id.pref))
						.title(R.string.gg_3.resStr())
						.build())
				.add(FancyShowCaseView.Builder(this)
						.focusOn(button)
						.title(R.string.gg_4.resStr())
						.build())
				.add(FancyShowCaseView.Builder(this)
						.title(R.string.gg_5.resStr())
						.build())
				.show()
		Fix.tutor = true
	}

	override fun onNewIntent(intent: Intent?) {
		super.onNewIntent(intent)
		hI(intent)
	}

	override fun createMenu(menu: Menu?) {
		super.createMenu(menu)
		menuInflater.inflate(R.menu.universal, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		super.onOptionsItemSelected(item)
		when (item?.itemId) {
			android.R.id.home -> onBackPressed()
			R.id.share_app -> sharing("\" ${R.string.sharing_tt.resStr()}\"", R.string.try_nc.resStr()!!)
		}
		return true
	}

	override fun onBackPressed() = if (iRV()) super.onBackPressed() else cV()

}
