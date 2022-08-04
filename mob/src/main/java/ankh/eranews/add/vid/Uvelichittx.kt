
package ankh.eranews.add.vid

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.TextView
import ankh.eranews.exported.added.Fix

class Uvelichittx(context: Context) : TextView(context) {
	var sD: ScaleGestureDetector? = null
	val zoomLimit = 3.0f

	private var sFc = 1.0f
	private var dS: Float = 0.0f

	init {
		dS = textSize
		sD = ScaleGestureDetector(context, ScaleListener())
		sFc = Fix.tSF
		sFc = Math.max(1.0f, Math.min(sFc, zoomLimit))
		setTextSize(TypedValue.COMPLEX_UNIT_PX, dS * sFc)
		setOnTouchListener { view, motionEvent ->
			view.performClick()
			if (motionEvent.pointerCount >= 2) {
				when (motionEvent.action) {
					MotionEvent.ACTION_DOWN -> {
						view.parent.parent.requestDisallowInterceptTouchEvent(true)
						sD?.onTouchEvent(motionEvent)
					}
					MotionEvent.ACTION_MOVE -> {
						view.parent.parent.requestDisallowInterceptTouchEvent(true)
						sD?.onTouchEvent(motionEvent)
					}
					MotionEvent.ACTION_UP -> view.parent.parent.requestDisallowInterceptTouchEvent(false)
				}
			} else {
				view.parent.parent.requestDisallowInterceptTouchEvent(false)
				view.onTouchEvent(motionEvent)
			}
			true
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	override fun onTouchEvent(ev: MotionEvent): Boolean {
		super.onTouchEvent(ev)
		sD?.onTouchEvent(ev)
		return true
	}

	private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
		override fun onScale(detector: ScaleGestureDetector): Boolean {
			sFc *= detector.scaleFactor
			sFc = Math.max(1.0f, Math.min(sFc, zoomLimit))
			setTextSize(TypedValue.COMPLEX_UNIT_PX, dS * sFc)
			Fix.tSF = sFc
			return true
		}
	}
}
