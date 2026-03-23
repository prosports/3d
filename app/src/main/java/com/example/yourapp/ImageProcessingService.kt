import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

class ImageProcessingService {

    // Compresses the image to the specified quality
    fun compressImage(imagePath: String, quality: Int): ByteArray? {
        val options = BitmapFactory.Options().apply { inSampleSize = 1 }
        val bitmap: Bitmap = BitmapFactory.decodeFile(imagePath, options)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)
        return stream.toByteArray()
    }

    // Resizes the image to the specified width and height
    fun resizeImage(imagePath: String, width: Int, height: Int): Bitmap? {
        val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeFile(imagePath, options)

        options.inSampleSize = calculateInSampleSize(options, width, height)
        options.inJustDecodeBounds = false

        return BitmapFactory.decodeFile(imagePath, options)
    }

    // Calculates the inSampleSize value based on the required width and height
    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}