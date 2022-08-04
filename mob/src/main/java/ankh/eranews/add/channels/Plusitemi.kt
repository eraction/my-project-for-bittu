
package ankh.eranews.add.channels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ankh.eranews.add.set.Plusitem
import ankh.readit.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class Plusitemi(private val ckl: () -> Unit = {}) : Res<Plusitemi, Plusitemi.ViewHolder>() {

	override fun getType(): Int {
		return R.id.more_item_id
	}

	override fun createView(ctx: Context, parent: ViewGroup?): View {
		return Plusitem().createView(AnkoContext.create(ctx, this))
	}

	override fun bindView(viewHolder: ViewHolder, payloads: MutableList<Any?>) {
		super.bindView(viewHolder, payloads)
		viewHolder.button.setOnClickListener { ckl() }
	}

	override fun getViewHolder(p0: View) = ViewHolder(p0)

	class ViewHolder(vw: View) : RecyclerView.ViewHolder(vw) {
		var button: Button = vw.find(R.id.morerecycleritem_button)
	}
}