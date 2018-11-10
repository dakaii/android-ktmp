package dakaii.musicplayer.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import dakaii.musicplayer.R

class SongListAdapter(): RecyclerView.Adapter<SongListAdapter.SongListViewHolder>() {

    override fun onBindViewHolder(holder: SongListViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class SongListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var songTV: TextView
        var durationTV: TextView
        var albumArt: ImageView
        init {
            songTV = itemView.findViewById(R.id.song_name_tv)
            durationTV = itemView.findViewById(R.id.song_duration_tv)
            albumArt = itemView.findViewById(R.id.al_img_view)
        }
    }
}