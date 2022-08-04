
package ankh.eranews.add.channels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ankh.eranews.add.set.Sc2tx
import ankh.readit.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class Textcs(val tx: String? = null) : Res<Textcs, Textcs.VH>() {

	override fun getType(): Int {
		return R.id.customtext_item_id
	}

	override fun createView(ctx: Context, parent: ViewGroup?): View {
		return Sc2tx().createView(AnkoContext.create(ctx, this))
	}

	override fun bindView(viewHolder: VH, payloads: MutableList<Any?>) {
		super.bindView(viewHolder, payloads)
		viewHolder.text.text = tx
	}

	override fun getViewHolder(p0: View) = VH(p0)

	class VH(vw: View) : RecyclerView.ViewHolder(vw) {
		var text: TextView = vw.find(R.id.customtextrecycleritem_text)
	}
}
