
package ankh.eranews.add.channels

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ankh.eranews.exported.Checkfeed
import ankh.eranews.exported.added.Db
import ankh.eranews.Itcannotbeworse.hV
import ankh.eranews.Itcannotbeworse.resClr
import ankh.eranews.Itcannotbeworse.resDrw
import ankh.eranews.Itcannotbeworse.sV
import ankh.eranews.add.mini.Novostinabortu
import ankh.eranews.add.set.Podfeedtext
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class Podfeedit(val cf: Checkfeed? = null, val iL: Boolean = false, val fl: Sams? = null) : Res<Podfeedit, Podfeedit.ViewHolder>() {

	override fun getType(): Int {
		return R.id.feed_item_id
	}

	override fun createView(ctx: Context, parent: ViewGroup?): View {
		return Podfeedtext().createView(AnkoContext.create(ctx, this))
	}

	override fun bindView(viewHolder: ViewHolder, payloads: MutableList<Any?>) {
		super.bindView(viewHolder, payloads)
		val ctx = viewHolder.itemView.context
		if (cf != null) {
			sTTx(cf.te, viewHolder.te)
			viewHolder.site.text = Uri.parse(cf.web ?: cf.site()).host
			viewHolder.itemView.setOnClickListener {
				fl?.oV(Novostinabortu(cf = cf).withTitle(cf.te))
			}
			viewHolder.fav.setImageDrawable((if (Db.isSavedFavorite(cf.site())) R.drawable.ic_favorite_universal else R.drawable.ic_favorite_border_universal).resDrw(ctx, R.color.colorPrimaryText.resClr(ctx)))
			viewHolder.fav.setOnClickListener {
				if (Db.isSavedFavorite(cf.site())) {
					Db.delFav(cf.site())
					viewHolder.fav.setImageDrawable(R.drawable.ic_favorite_border_universal.resDrw(ctx, R.color.colorPrimaryText.resClr(ctx)))
				} else {
					Db.aV(cf)
					viewHolder.fav.setImageDrawable(R.drawable.ic_favorite_universal.resDrw(ctx, R.color.colorPrimaryText.resClr(ctx)))
				}
				ctx.sendBroadcast(Intent("favorites_updated"))
			}
		}
		if (iL) viewHolder.dvd.hV() else viewHolder.dvd.sV()
	}

	private fun sTTx(title: String?, textView: TextView) {
		textView.text = "$title"
	}

	override fun getViewHolder(p0: View) = ViewHolder(p0)

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		var te: TextView = view.find(R.id.feedrecycleritem_title)
		var site: TextView = view.find(R.id.feedrecycleritem_website)
		var fav: ImageView = view.find(R.id.feedrecycleritem_favorite)
		var dvd: View = view.find(R.id.feedrecycleritem_divider)
	}
}