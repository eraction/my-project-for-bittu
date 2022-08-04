package ankh.Forview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout

abstract class SamActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
			samActivity = this
		if (vS.size == 0) vS += Sv
	}

	abstract val Sv: MutableList<MutableList<Sams>>
	abstract val cV: FrameLayout

	fun cW(): Sams = vS[cS].last()
	private fun aV() = vS.flatten()

	open fun onsW() {
	}

	fun sS(stack: Int) {
		cS = stack
		sW(vS[stack].last())
	}

	fun oV(view: Sams) {
		view.title = view.title ?: cW().title ?: ""
		vS[cS].add(view)
		sW(view)
	}

	fun cV() {
		vS[cS].apply {
			if (size > 1) removeAt(lastIndex).onDestroy()
			sW(last())
		}
	}

	fun rS() {
		rS(cS)
	}

	fun iRV() = vS[cS].size == 1
	fun currentStack() = cS

	private fun rS(stack: Int) {
		vS[stack].apply { while (size > 1) removeAt(lastIndex).onDestroy() }
		sS(stack)
	}

	private fun sW(view: Sams?) {
		if (view != null) cV.apply {
			removeAllViews()
			view.apply { (parent as ViewGroup?)?.removeView(this) }
			addView(view.apply { oS() })
			invalidateOptionsMenu()
			onsW()
		}
	}

	override fun onBackPressed() = when {
		vS[cS].size > 1 -> cV()
		cS != 0 -> sS(0)
		else -> super.onBackPressed()
	}

	override fun onDestroy() {
		cV.removeAllViews()
		aV().forEach { it.onDestroy() }
		super.onDestroy()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		super.onCreateOptionsMenu(menu)
		menu?.clear()
		createMenu(menu)
		cW().inflateMenu(menuInflater, menu)
		return true
	}

	open fun createMenu(menu: Menu?) {
	}

	override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
		super.onPrepareOptionsMenu(menu)
		menu?.clear()
		createMenu(menu)
		cW().inflateMenu(menuInflater, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		super.onOptionsItemSelected(item)
		cW().onOptionsItemSelected(item)
		return true
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		cW().onActivityResult(requestCode, resultCode, data)
	}

	override fun recreate() {
		aV().forEach { it.rV() }
		super.recreate()
	}
}

private class ASamActivity : SamActivity() {
	override val Sv: MutableList<MutableList<Sams>>
		get() = mutableListOf()
	override val cV: FrameLayout
		get() = FrameLayout(this)
}

abstract class Sams : LinearLayout(samActivity) {
	val contentView by lazy { this }
	val context
		get() = samActivity
	var title: String? = null

	init {
		orientation = LinearLayout.VERTICAL
	}

	fun withTitle(title: String?): Sams {
		this.title = title
		return this
	}

	fun oS() {
		if (contentView.childCount == 0) contentView.addView(onCreateView())
	}

	open fun onCreateView(): View? {
		return null
	}

	open fun onDestroy() {}

	fun oV(view: Sams) {
		context.oV(view)
	}

	fun cV() {
		context.cV()
	}

	fun rV() {
		contentView.removeAllViews()
	}

	open fun inflateMenu(inflater: MenuInflater, menu: Menu?) {
	}

	open fun onOptionsItemSelected(item: MenuItem?) {
	}

	open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
	}
}

private val vS = mutableListOf<MutableList<Sams>>()
private var cS: Int = 0
private var samActivity: SamActivity = ASamActivity()