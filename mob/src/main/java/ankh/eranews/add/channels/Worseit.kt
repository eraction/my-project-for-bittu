
package ankh.eranews.add.channels

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import ankh.eranews.exported.Name
import ankh.eranews.exported.added.Db
import ankh.eranews.Itcannotbeworse.*
import ankh.eranews.add.mini.Title
import ankh.eranews.add.set.Worseits
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class Worseit(val nm: Name? = null, val fr: Sams? = null) : Res<Worseit, Worseit.ViewHolder>() {

	override fun getType(): Int {
		return R.id.article_item_id
	}

	override fun createView(ctx: Context, parent: ViewGroup?): View {
		return Worseits().createView(AnkoContext.create(ctx, this))
	}

	override fun bindView(viewHolder: ViewHolder, payloads: MutableList<Any?>) {
		super.bindView(viewHolder, payloads)
		val ctx = viewHolder.itemView.context
		if (!nm?.te.isNullOrBlank()) {
			viewHolder.te.sV()
			viewHolder.te.text = nm?.te
			viewHolder.te.setTypeface(null, if (Db.isSavedReadUrl(nm?.url)) Typeface.BOLD_ITALIC else Typeface.BOLD)
		} else viewHolder.te.hV()
		if ((nm?.pl?.toInt() ?: 0) != 0) {
			viewHolder.dt.sV()
			val dT = DateUtils.getRelativeTimeSpanString(nm!!.pl)
			viewHolder.dt.text = dT
		} else {
			viewHolder.dt.hV()
		}
		if (!nm?.ct.isNullOrBlank()) {
			viewHolder.ctx.sV()
			viewHolder.ctx.text = nm?.ec
		} else {
			viewHolder.ctx.hV()
		}
		if (nm?.kw.nN()) {
			viewHolder.tBx.sV()
			viewHolder.tBx.removeAllViews()
			viewHolder.tBx.addTags(fr!!, nm?.kw?.take(3)?.toTypedArray())
		} else {

		}
		if (!nm?.vU.isNullOrBlank()) {
			viewHolder.inter.sV()
			viewHolder.inter.loadImage(nm?.vU)
		} else {
			viewHolder.inter.clearGlide()
			viewHolder.inter.hV()
		}
		viewHolder.itemView.setOnClickListener {
			if (nm != null) fr?.oV(Title(name = nm).withTitle(nm.oT))
		}
		viewHolder.bm.setImageDrawable((if (Db.isSavedBookmark(nm?.url)) R.drawable.ic_bookmark_universal else R.drawable.ic_bookmark_border_universal).resDrw(ctx, R.color.colorPrimaryText.resClr(ctx)))
		viewHolder.bm.setOnClickListener {
			if (nm != null) {
				if (Db.isSavedBookmark(nm.url)) {
					Db.delBm(nm.url)
					viewHolder.bm.setImageDrawable(R.drawable.ic_bookmark_border_universal.resDrw(ctx, R.color.colorPrimaryText.resClr(ctx)))
				} else {
					Db.addBookmark(nm)
					viewHolder.bm.setImageDrawable(R.drawable.ic_bookmark_universal.resDrw(ctx, R.color.colorPrimaryText.resClr(ctx)))
				}
			}
		}
		viewHolder.sharing.setImageDrawable(R.drawable.ic_share_universal.resDrw(ctx, R.color.colorPrimaryText.resClr(ctx)))
		viewHolder.sharing.setOnClickListener {
			if (fr != null) nm?.sharing(fr.context)
		}
	}

	override fun getViewHolder(p0: View) = ViewHolder(p0)

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		var bm: ImageView = view.find(R.id.articlerecycleritem_bookmark)
		var sharing: ImageView = view.find(R.id.articlerecycleritem_share)
		var te: TextView = view.find(R.id.articlerecycleritem_title)
		var dt: TextView = view.find(R.id.articlerecycleritem_details)
		var ctx: TextView = view.find(R.id.articlerecycleritem_content)
		var inter: ImageView = view.find(R.id.articlerecycleritem_visual)
		var tBx: FlexboxLayout = view.find(R.id.articlerecycleritem_tagsbox)
	}
}
