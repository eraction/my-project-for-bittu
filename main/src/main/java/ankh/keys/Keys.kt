package ankh.keys

import android.content.Context
import com.afollestad.ason.Ason
import com.afollestad.ason.AsonArray
import java.io.File

/**
 *
 * Сохранение объектов в файлы ключами
 *
 * ctx конекст объекта
 * nm Имя базы данных, по дефолту = "default"
 * fEx расширение файлы базы данных, default = "nc"
 * ch сохранение базы в кэш, по default = false
 *
 */
class Keys(ctx: Context, nm: String = "default", private val fEx: String = "nc", ch: Boolean = false) {
	private var folder: File = File(if (ch) ctx.applicationContext?.cacheDir else ctx.applicationContext?.filesDir, "NCStore$nm")

	init {
		tryOrNull(!folder.exists()) { folder.mkdir() }
	}

	/**
     *
	 * добавление новых итемов
	 *
	 * key ключ для итема, не сохраняется если пусто
	 * item итем объекта, удаляется если пусто
	 *
	 *
	 */
	fun <T> write(key: String?, item: Any?): Keys {
		if (item == null) delete(key)
		else if (key != null && key.isNotBlank()) {
			val gg = File(folder, key + ".$fEx")
			if (item.javaClass.isArray) tryOrNull { saveAsonArray(gg, Ason.serializeArray<T>(item)) }
			else tryOrNull { saveAson(gg, Ason.serialize(item)) }
		}
		return this
	}

	/**
	 * Удаление по ключу
	 *
	 *  key Ключ для итема, то, что нужно удалить
	 *
	 *
	 */
	fun delete(key: String?): Keys {
		tryOrNull(key != null && key.isNotBlank()) { File(folder, key + ".$fEx").delete() }
		return this
	}

	/**
	 * Получение итемов
	 *
	 * key Ключ итема
	 * type Класс итема
	 * defaultValue по дефолту, default = null
	 *
	 *
	 */
	fun <T> read(key: String?, type: Class<T>, defaultValue: T? = null): T? = if (key != null) tryOrNull {
		val gg = File(folder, key + ".$fEx")
		if (gg.exists()) {
			if (type.isArray) Ason.deserialize(AsonArray<T>(gg.readText()), type)
			else Ason.deserialize(Ason(gg.readText()), type)
		} else null
	} ?: defaultValue else defaultValue

	/**
	 * уничтожить всё))) (нет)
	 */
	fun destroy() {
		tryOrNull { folder.deleteRecursively() }
	}

	/**
	 * Проверка если ключ уже есть
	 *
	 *
	 *
	 *
	 */
	fun exists(key: String?): Boolean = if (key != null && key.isNotBlank()) File(folder, key + ".$fEx").exists() else false

	private fun saveAson(file: File, ason: Ason) {
		if (!file.exists()) tryOrNull { file.createNewFile() }
		tryOrNull { file.writeText(ason.toString()) }
	}

	private fun <T> saveAsonArray(file: File, ason: AsonArray<T>) {
		if (!file.exists()) tryOrNull { file.createNewFile() }
		tryOrNull { file.writeText(ason.toString()) }
	}

	private fun <T> tryOrNull(execute: Boolean = true, code: () -> T): T? = try {
		if (execute) code() else null
	} catch (e: Exception) {
		null
	}

}