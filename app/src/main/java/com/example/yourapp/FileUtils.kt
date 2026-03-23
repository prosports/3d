import java.io.File
import java.io.IOException

object FileUtils {
    @Throws(IOException::class)
    fun readFile(filePath: String): String {
        return File(filePath).readText()
    }

    @Throws(IOException::class)
    fun writeFile(filePath: String, content: String) {
        File(filePath).writeText(content)
    }

    fun deleteFile(filePath: String): Boolean {
        return File(filePath).delete()
    }
}