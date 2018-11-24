package hd.pro.videoplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayer : AppCompatActivity() {

    val TAG:String = "VideoPlayer"

    var position : Int ?=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        var path = intent.getStringExtra("PATH")
        position = intent.extras.getString("POSITION","0").toInt()

        video_player_view.minimumHeight = 300
        video_player_view.minimumWidth = 200
        video_player_view.setMediaController(MediaController(this))
        Log.d(TAG,"path is: "+path)
        video_player_view.setVideoPath(path)
        video_player_view.start()

        video_player_view.setOnCompletionListener { MediaPlayer.OnCompletionListener{
                finish()
        } }
    }
}
