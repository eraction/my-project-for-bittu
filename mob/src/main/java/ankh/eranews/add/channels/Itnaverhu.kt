
package ankh.eranews.add.channels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ankh.eranews.add.set.Verhit
import ankh.readit.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class Itnaverhu(val te: String? = null) : Res<Itnaverhu, Itnaverhu.ViewHolder>() {

	override fun getType(): Int {
		return R.id.header_item_id
	}

	override fun createView(ctx: Context, parent: ViewGroup?): View {
		return Verhit().createView(AnkoContext.create(ctx, this))
	}

	override fun bindView(viewHolder: ViewHolder, payloads: MutableList<Any?>) {
		super.bindView(viewHolder, payloads)
		viewHolder.te.text = te ?: ""
	}

	override fun getViewHolder(p0: View) = ViewHolder(p0)

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		var te: TextView = view.find(R.id.headerrecycleritem_textview)
	}
}