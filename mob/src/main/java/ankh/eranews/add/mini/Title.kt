
@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.add.mini

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.format.DateUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import ankh.eranews.exported.Name
import ankh.eranews.exported.feed.oU
import ankh.eranews.exported.added.Db
import ankh.eranews.Itcannotbeworse.*
import ankh.eranews.add.aapt2.Customres
import ankh.eranews.add.set.Worsetitle
import ankh.eranews.add.channels.addTags
import ankh.eranews.add.vid.Uvelichittx
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

@SuppressLint("ViewConstructor")
class Title(var name: Name) : Sams(), Customres {
	private var fVw: View? = null
	private val tVw: TextView? by lazy { fVw?.find<TextView>(R.id.articlefragment_title) }
	private val vVw: ImageView? by lazy { fVw?.find<ImageView>(R.id.articlefragment_visual) }
	private val dVw: TextView? by lazy { fVw?.find<TextView>(R.id.articlefragment_details) }
	private val tBx: FlexboxLayout? by lazy { fVw?.find<FlexboxLayout>(R.id.articlefragment_tagsbox) }
	private val aCV: Uvelichittx? by lazy { fVw?.find<Uvelichittx>(R.id.articlefragment_content) }

	private val bm
		get() = Db.isSavedBookmark(name.url)

	override val fabDrawable = R.drawable.ic_share
	override val fabClick = { sArtc() }

	override fun onCreateView(): View? {
		super.onCreateView()
		fVw = Worsetitle().createView(AnkoContext.create(context, this))
		sArt(name)
		Db.aRDU(name.url)
		return fVw
	}

	private fun sArt(name: Name?) {
		if (name != null) {
			this@Title.name = name
			img(name.vU)
			te(name.te)
			dt(name.av, name.oT, name.pl)
			cnt(name.ct)
			kwr(name.kw)
		}
	}

	private fun img(visualUrl: String? = "") {
		if (!visualUrl.isNullOrBlank()) vVw?.apply {
			sV()
			loadImage(visualUrl)
		} else vVw?.hV()
	}

	private fun te(title: String? = "") {
		if (!title.isNullOrBlank()) tVw?.apply {
			sV()
			text = title?.toHtml()
		} else tVw?.hV()
	}

	private fun dt(author: String? = "", originTitle: String? = "", published: Long? = 0) {
		var dt: String? = ""
		if (!author.isNullOrBlank()) dt += author
		if (!originTitle.isNullOrBlank()) {
			if (!dt.isNullOrBlank()) dt += " - "
			dt += originTitle
		}
		if ((published?.toInt() ?: 0) != 0) {
			if (!dt.isNullOrBlank()) dt += "\n"
			dt += DateUtils.getRelativeTimeSpanString(published!!)
		}
		if (!dt.isNullOrBlank()) dVw?.apply {
			sV()
			text = dt
		} else dVw?.hV()
	}

	private fun cnt(content: String? = "") {
		if (!content.isNullOrBlank()) aCV?.apply {
			sV()
			text = content?.toHtml()
			applyLinks(true)
		} else aCV?.hV()
	}

	private fun kwr(keywords: Array<String>? = null) {
		if (keywords.nN()) tBx?.apply {
			sV()
			removeAllViews()
			addTags(this@Title, keywords)
		} else tBx?.hV()
	}

	private fun sArtc() = name.sharing(context)

	override fun inflateMenu(inflater: MenuInflater, menu: Menu?) {
		super.inflateMenu(inflater, menu)
		inflater.inflate(R.menu.articlefragment, menu)
		menu?.findItem(R.id.bookmark)?.icon = (if (bm) R.drawable.ic_bookmark_universal else R.drawable.ic_bookmark_border_universal).resDrw(context, Color.WHITE)
	}

	override fun onOptionsItemSelected(item: MenuItem?) {
		super.onOptionsItemSelected(item)
		when (item?.itemId) {
			R.id.bookmark -> {
				if (!bm) Db.addBookmark(name)
				else Db.delBm(name.url)
				item.icon = (if (bm) R.drawable.ic_bookmark_universal else R.drawable.ic_bookmark_border_universal).resDrw(context, Color.WHITE)
			}
			R.id.browser -> (name.cAMP ?: name.amp).oU(context, Amp = true, nA = name.url)
		}
	}
}
