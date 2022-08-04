
package ankh.eranews.add.mini

import android.annotation.SuppressLint
import android.view.View
import ankh.eranews.add.set.Prefnastroyki
import ankh.Forview.Sams
import org.jetbrains.anko.AnkoContext

@SuppressLint("ViewConstructor")
class Vidnastroyki : Sams() {
	override fun onCreateView(): View? {
		super.onCreateView()
		return Prefnastroyki().createView(AnkoContext.create(context, this))
	}
}
