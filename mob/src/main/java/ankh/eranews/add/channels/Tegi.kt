
package ankh.eranews.add.channels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import ankh.eranews.Itcannotbeworse.tON
import ankh.eranews.add.mini.Vidvmeste
import ankh.eranews.add.set.Vidtega
import ankh.eranews.add.set.Tegiit
import ankh.readit.R
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class TagsRecyclerItem(val cs: Array<String>? = null, val fl: Sams? = null) : Res<TagsRecyclerItem, TagsRecyclerItem.VH>() {

	override fun getType(): Int {
		return R.id.tags_item_id
	}

	override fun createView(ctx: Context, parent: ViewGroup?): View {
		return Tegiit().createView(AnkoContext.create(ctx, this))
	}

	override fun bindView(viewHolder: VH, payloads: MutableList<Any?>) {
		super.bindView(viewHolder, payloads)
		viewHolder.tBx.removeAllViews()
		viewHolder.tBx.addTags(fl, cs)
	}

	override fun getViewHolder(p0: View) = VH(p0)

	class VH(vw: View) : RecyclerView.ViewHolder(vw) {
		var tBx: FlexboxLayout = vw.find(R.id.tagsrecycleritem_box)
	}
}

fun FlexboxLayout.addTags(fl: Sams?, cs: Array<out String?>? = null) = tON {
	cs?.filterNotNull()?.forEach { tag ->
		addView(Vidtega().createView(AnkoContext.Companion.create(context, this)).apply {
			find<TextView>(R.id.tag_text).apply {
				val te = "#$tag"
				text = te
				setOnClickListener {
					fl?.oV(Vidvmeste(fId = "topic/$tag").withTitle(te))
				}
			}
		})
	}
}