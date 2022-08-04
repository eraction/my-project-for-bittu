/*
~ 2018
 ~ Andrey Khokhlov
 ~ Program ready to use. Version 1.0>.
*/

package ankh.eranews.Itcannotbeworse

import android.view.View
import android.view.ViewManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import ankh.eranews.add.vid.Swipe
import ankh.eranews.add.vid.Uvelichittx
import org.jetbrains.anko.custom.ankoView

// https://gist.github.com/soulduse/8a928b94c47cf9dbcd09e6c0d1aa12df Anko Extensions

inline fun ViewManager.flexboxLayout(theme: Int = 0, init: FlexboxLayout.() -> Unit) = ankoView(::FlexboxLayout, theme, init)
inline fun ViewManager.swipeRefreshLayout(theme: Int = 0, init: Swipe.() -> Unit) = ankoView(::Swipe, theme, init)
inline fun ViewManager.zoomTextView(theme: Int = 0, init: Uvelichittx.() -> Unit) = ankoView(::Uvelichittx, theme, init)
inline fun ViewManager.textView(theme: Int = 0, init: TextView.() -> Unit) = ankoView(::TextView, theme, init)
inline fun ViewManager.imageView(theme: Int = 0, init: ImageView.() -> Unit) = ankoView(::ImageView, theme, init)
inline fun ViewManager.view(theme: Int = 0, init: View.() -> Unit) = ankoView(::View, theme, init)
inline fun ViewManager.button(theme: Int = 0, init: Button.() -> Unit) = ankoView(::Button, theme, init)