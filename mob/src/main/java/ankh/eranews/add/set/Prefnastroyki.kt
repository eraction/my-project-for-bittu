
package ankh.eranews.add.set

import android.view.View
import ankh.eranews.add.mini.Vidnastroyki
import ankh.readit.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.include

class Prefnastroyki : AnkoComponent<Vidnastroyki> {
	override fun createView(ui: AnkoContext<Vidnastroyki>): View = with(ui) {
		include(R.layout.settingsview)
	}
}