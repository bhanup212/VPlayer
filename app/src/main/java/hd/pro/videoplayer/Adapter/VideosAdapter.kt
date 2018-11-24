package hd.pro.videoplayer.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hd.pro.videoplayer.Model.Video
import hd.pro.videoplayer.R

class VideosAdapter (val context: Context,private val clickListener: onClickListener?= null,var videos: ArrayList<Video>) :
    RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    override fun onBindViewHolder(holder: VideosAdapter.ViewHolder, position: Int) {
            holder.bindItems(videos[position])
        holder.thumbnail.setOnClickListener {
            if (clickListener !== null){
                clickListener.onItemClick(videos[position],position )
            }
        }
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail = itemView.findViewById(R.id.video_row_img) as ImageView
        fun bindItems(video : Video){
            val title = itemView.findViewById(R.id.video_row_title) as TextView
            title.text = video.title
            thumbnail.setImageBitmap(video.thumbNail)
            //Picasso.get().load(video.path.absoluteFile).into(thumbnail)
        }
    }
    public interface onClickListener{
        fun onItemClick(video: Video,position: Int)
    }
}