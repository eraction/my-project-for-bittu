package ankh.eranews

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate
import com.evernote.android.job.JobManager
import ankh.eranews.exported.added.Fix
import ankh.eranews.exported.added.SJ
import ankh.eranews.exported.added.cSy
import ankh.eranews.exported.added.sSy
import ankh.eranews.add.activity.Activity
import me.zhanghai.android.customtabshelper.CustomTabsHelperFragment

class EraNews : Application() {
	override fun onCreate() {
		super.onCreate()
		aC = applicationContext
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
		JobManager.create(this@EraNews).addJobCreator { tag ->
			when (tag) {
				SJ.TG -> SJ()
				else -> null
			}
		}
		if (Fix.sEb) sSy(Fix.sIn) else cSy()
	}
}

var aC: Context? = null
var cTHF: CustomTabsHelperFragment? = null
var lT = 0
var mA: Activity? = null