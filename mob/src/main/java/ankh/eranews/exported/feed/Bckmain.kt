/*
~ 2018
 ~ Andrey Khokhlov
 ~ Program ready to use. Version 1.0>.
*/

@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ankh.eranews.exported.feed

import android.content.ClipData
import ankh.eranews.exported.Checkfeed
import ankh.eranews.exported.added.Db
import ankh.eranews.Itcannotbeworse.progressDialog
import ankh.eranews.Itcannotbeworse.resStr
import ankh.eranews.Itcannotbeworse.tON
import ankh.eranews.mA
import android.content.Context
import android.support.design.widget.Snackbar
import co.metalab.asyncawait.async
import com.afollestad.ason.Ason
import com.afollestad.materialdialogs.MaterialDialog
import ankh.eranews.exported.Name
import ankh.readit.R
import org.jetbrains.anko.clipboardManager

class Bck(val context: Context) {

	fun ck() = async {
		val pD = context.progressDialog().apply { show() }
		val ckAs = Ason()
		val fav = await { Ason.serializeArray<Checkfeed>(Db.aFav) }
		val bm = await { Ason.serializeArray<Name>(Db.aBm) }
		val rU = await { Ason.serializeArray<String>(Db.aRU) }
		await { ckAs.put("fav", fav).put("bm", bm).put("rU", rU) }
		pD.dismiss()
		await { tON { ckAs.toString().uH() } }.let { key ->
			if (key != null) {
				MaterialDialog.Builder(context)
						.title(R.string.suc_backup)
						.content(R.string.restore_key_desc)
						.input(R.string.restore_key.resStr(), key) { _, _ ->
							context.clipboardManager.primaryClip = ClipData.newPlainText(R.string.restore_key.resStr(), key)
						}
						.negativeText(android.R.string.cancel)
						.positiveText(android.R.string.copy)
						.show()
			} else Snackbar.make(mA!!.findViewById(R.id.mainactivity_container), R.string.backup_failed, Snackbar.LENGTH_SHORT).show()
		}
	}

	fun rt(key: String) = async {
		await { tON { key.dH() } }.let { json ->
			if (json != null) {
				val pD = context.progressDialog().apply { show() }
				tON { Ason(json) }?.let { restoreAson ->
					await { tON { Db.aFav = restoreAson.get("fav", Array<Checkfeed>::class.java) } }
					await { tON { Db.aBm = restoreAson.get("bm", Array<Name>::class.java) } }
					await { tON { Db.aRU = restoreAson.get("rU", Array<String>::class.java) } }
				}
				pD.dismiss()
				MaterialDialog.Builder(context).content(R.string.suc_restore).positiveText(android.R.string.ok).show()
			} else MaterialDialog.Builder(context).content(R.string.restore_failed).positiveText(android.R.string.ok).show()
		}
	}

}

fun Context.bRt() {
	MaterialDialog.Builder(this)
			.items(R.string.backup.resStr(), R.string.restore.resStr())
			.itemsCallback { _, _, which, _ ->
				when (which) {
					0 -> Bck(this).ck()
					else -> {
						MaterialDialog.Builder(this)
								.title(R.string.restore)
								.input(R.string.restore_key, 0, { _, key ->
									Bck(this).rt(key.toString())
								})
								.negativeText(android.R.string.cancel)
								.positiveText(R.string.restore)
								.show()
					}
				}
			}
			.negativeText(android.R.string.cancel)
			.show()
}