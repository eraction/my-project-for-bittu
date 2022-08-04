/*
~ 2018
 ~ Andrey Khokhlov
 ~ Program ready to use. Version 1.0>.
*/

package ankh.eranews.exported.added

import android.content.Context
import android.content.Intent
import com.evernote.android.job.Job
import com.evernote.android.job.JobManager
import com.evernote.android.job.JobRequest
import ankh.eranews.aC
import ankh.eranews.exported.launcher.Forfeedly
import ankh.eranews.Itcannotbeworse.nN
import ankh.eranews.Itcannotbeworse.tON

fun sSy(iMn: Int) {
	val iMs = (iMn * 60000).toLong()
	val aJ = JobManager.instance().getAllJobRequestsForTag(SJ.TG)
	val oIMs = if (aJ.nN()) aJ.first().intervalMs else 0.toLong()
	if (oIMs == 0.toLong() || oIMs != iMs || aJ.isEmpty()) {
		JobRequest.Builder(SJ.TG)
				.setPeriodic(iMs)
				.setUpdateCurrent(true)
				.build()
				.schedule()
	}
}

fun cSy() {
	JobManager.instance().cancelAllForTag(SJ.TG)
}

class SJ : Job() {
	override fun onRunJob(params: Params): Result {
		return if (sn(context) != null) Result.SUCCESS else Result.RESCHEDULE
	}

	companion object {
		val TG = "sync_job_tag"
	}
}

fun sn(context: Context): String? = tON {
	System.out.println("Nachalo")
	if (aC == null) aC = context.applicationContext
	Db.aFav.forEach {
		Forfeedly().apply {
			type = Forfeedly.FT.FEED
			fU = "checkfeed/" + it.site()
			mmr = Forfeedly.Ranked.NEWEST
		}.ims(false)
	}
	System.out.println("Konec")
	Fix.lS = System.currentTimeMillis()
	context.sendBroadcast(Intent("Informaciya"))
	"not null"
}