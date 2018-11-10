package dakaii.musicplayer.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import dakaii.musicplayer.R
import dakaii.musicplayer.helpers.CustomItemClickListener
import dakaii.musicplayer.models.SongModel
import dakaii.musicplayer.services.MusicPlayerService
import java.util.concurrent.TimeUnit

class SongListAdapter(songModel: ArrayList<SongModel>, context: Context): RecyclerView.Adapter<SongListAdapter.SongListViewHolder>() {

    val context = context
    val songModel = songModel
    var songList: ArrayList<String> = ArrayList()

    companion object {
        const val SONG_LIST = "SONG_LIST"
        const val SONG_TEMP_ID = "SONG_TEMP_ID"
    }

    override fun onBindViewHolder(holder: SongListViewHolder, position: Int) {
        val model = songModel[position]
        holder.songTV.text = convertMillis(model.songName.toLong())
        holder.durationTV.text = convertMillis(model.songDuration.toLong())
        holder.setOnCustomItemClickListener(object: CustomItemClickListener {
            override fun onCustomItemClick(view: View, pos: Int) {
                for (i in 0 until songModel.size) {
                    songList.add(songModel[i].songPath)
                }
                Log.i(SONG_LIST, songList.toString())
                val musicDataIntent = Intent(context, MusicPlayerService::class.java)
                musicDataIntent.putStringArrayListExtra(SONG_LIST, songList)
                musicDataIntent.putExtra(SONG_TEMP_ID, pos)
                context.startService(musicDataIntent)
            }
        })
    }

    override fun getItemCount(): Int {
        return songModel.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.music_row, parent, false)
        return SongListViewHolder(view)
    }

    private fun convertMillis(millis: Long): String {
        return String.format("%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(millis),
            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)))
    }


    class SongListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var songTV: TextView
        var durationTV: TextView
        var albumArt: ImageView
        var customItemClickListner: CustomItemClickListener? = null
        init {
            songTV = itemView.findViewById(R.id.song_name_tv)
            durationTV = itemView.findViewById(R.id.song_duration_tv)
            albumArt = itemView.findViewById(R.id.al_img_view)
            itemView.setOnClickListener(this)
        }
        fun setOnCustomItemClickListener(listener: CustomItemClickListener) {
            this.customItemClickListner = listener
        }

        override fun onClick(view: View?) {
            this.customItemClickListner!!.onCustomItemClick(view!!, adapterPosition)
        }
    }
}