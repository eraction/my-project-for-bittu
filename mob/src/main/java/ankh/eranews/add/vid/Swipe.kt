
package ankh.eranews.add.vid

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import ankh.readit.R

class Swipe : SwipeRefreshLayout {

	constructor(context: Context) : super(context) {
		setColorSchemeResources(R.color.colorAccent)
	}

	constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
		setColorSchemeResources(R.color.colorAccent)
	}

	fun showIndicator() {
		setColorSchemeResources(R.color.colorAccent)
		if (!isRefreshing) isRefreshing = true
	}

	fun hideIndicator() {
		if (isRefreshing) isRefreshing = false
	}

}