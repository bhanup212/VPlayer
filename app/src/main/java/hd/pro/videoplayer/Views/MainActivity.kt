package hd.pro.videoplayer.Views

import android.app.Application
import android.content.Context
import android.content.Intent
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hd.pro.videoplayer.Adapter.VideosAdapter
import hd.pro.videoplayer.Model.Video
import hd.pro.videoplayer.R
import hd.pro.videoplayer.VideoPlayer
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import java.io.File

class MainActivity : AppCompatActivity(), VideosAdapter.onClickListener {


    val TAG :String = "MainActivity"
    var videoList : ArrayList<Video> = ArrayList()
    var videoAdapter : VideosAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_progress_bar.visibility = VISIBLE
        getAllVideos()
        videoAdapter = VideosAdapter(this,this,videoList)
        //VideoTask(this).execute()
        videos_rv.layoutManager = GridLayoutManager(this,2)
        videos_rv.itemAnimator = DefaultItemAnimator()
        videos_rv.adapter = videoAdapter
        videoAdapter!!.notifyDataSetChanged()

    }
    override fun onItemClick(video: Video,position :Int) {
        //Toast.makeText(this,"position",Toast.LENGTH_SHORT).show()
        var intent = Intent(this,VideoPlayer::class.java)
        intent.putExtra("POSITION",position.toString())
        intent.putExtra("PATH",video.path.absolutePath)
        Log.d(TAG,"path: "+ video.path.absolutePath)
        startActivity(intent)
    }
    fun getAllVideos(){
        doAsync {
            val uri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(MediaStore.Video.VideoColumns.DATA)
            val cursor = contentResolver.query(uri, projection, null, null, null)
            var vidCount: Int
            if (cursor !== null) {
                vidCount = cursor.count
                videoList.clear()
                Log.d(TAG,"Video count: "+vidCount)
                while (cursor.moveToNext()) {
                    val file = File(cursor.getString(0))
                    var thumbnail =
                        ThumbnailUtils.createVideoThumbnail(file.absolutePath, MediaStore.Images.Thumbnails.MINI_KIND)
                    //Log.d(TAG,"file name is "+file.name)
                    videoList.add(Video(file, file.name, thumbnail, file.length() / (1024 * 1024), file.lastModified()))
                    runOnUiThread { videoAdapter!!.notifyDataSetChanged()  }

                }
                cursor.close()
                main_progress_bar.visibility = GONE
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.folder_item ->{

            }
            R.id.all_item ->{

            }
            R.id.rate_item ->{

            }
            R.id.share_item ->{

            }
        }
        return super.onOptionsItemSelected(item)
    }






}
