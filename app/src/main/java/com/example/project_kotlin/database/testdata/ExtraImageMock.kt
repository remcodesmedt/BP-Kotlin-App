package com.example.project_kotlin.database.testdata

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.project_kotlin.R
import com.example.project_kotlin.database.interfaces.ExtraImageInterface
import com.example.project_kotlin.domain.ExtraImage
import java.io.ByteArrayOutputStream
import java.io.InputStream

class ExtraImageMock (private val context: Context){
    private fun imageToByteArray(id: Int): ByteArray {
        val inputStream: InputStream = context.resources.openRawResource(id)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    }

    fun insertMocks(){
        val image1 = ExtraImage(image = imageToByteArray(R.raw.extraimage1))
        val image2 = ExtraImage(image = imageToByteArray(R.raw.extraimage2))
        val image3 = ExtraImage(image = imageToByteArray(R.raw.extraimage3))
        val image4 = ExtraImage(image = imageToByteArray(R.raw.extraimage4))
        val image5 = ExtraImage(image = imageToByteArray(R.raw.extraimage5))
        val image6 = ExtraImage(image = imageToByteArray(R.raw.extraimage6))
        val image7 = ExtraImage(image = imageToByteArray(R.raw.extraimage7))
        val image8 = ExtraImage(image = imageToByteArray(R.raw.extraimage8))
        val image9 = ExtraImage(image = imageToByteArray(R.raw.extraimage9))
        val image10 = ExtraImage(image = imageToByteArray(R.raw.extraimage10))

        ExtraImageInterface.insertItem(image1)
        ExtraImageInterface.insertItem(image2)
        ExtraImageInterface.insertItem(image3)
        ExtraImageInterface.insertItem(image4)
        ExtraImageInterface.insertItem(image5)
//        ExtraImageInterface.insertItem(image6)
        ExtraImageInterface.insertItem(image7)
        ExtraImageInterface.insertItem(image8)
        ExtraImageInterface.insertItem(image9)
        ExtraImageInterface.insertItem(image10)
    }

    fun getMocks(): List<ExtraImage>{
        return ExtraImageInterface.getItems()
    }
}