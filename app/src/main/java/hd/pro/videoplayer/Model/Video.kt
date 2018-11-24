package hd.pro.videoplayer.Model

import android.graphics.Bitmap
import java.io.File

data class Video(
    var path :File,
    var title :String,
    var thumbNail : Bitmap?,
    var size : Long,
    var date :Long)
