
package ankh.eranews.add.channels

import android.support.v7.widget.RecyclerView
import com.mikepenz.fastadapter.IClickable
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.items.AbstractItem

abstract class Res<Item, VH : RecyclerView.ViewHolder> : AbstractItem<Item, VH>() where Item : IItem<*, *>, Item : IClickable<*> {

	override fun getLayoutRes() = 0

}