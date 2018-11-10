package dakaii.musicplayer.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.MediaStore
import dakaii.musicplayer.adapters.SongListAdapter

class MusicPlayerService: Service() {

    var currentPosition: Int = 0
    var musicDataList: ArrayList<String> = ArrayList()
    var mp: MediaPlayer? = null
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        currentPosition = intent!!.getIntExtra(SongListAdapter.SONG_TEMP_ID, 0)
        musicDataList = intent!!.getStringArrayListExtra(SongListAdapter.SONG_LIST)

        if(mp != null) {
            mp!!.stop()
            mp!!.release()
            mp = null
        }

        mp = MediaPlayer()
        mp!!.setDataSource(musicDataList[currentPosition])
        mp!!.prepare()
        mp!!.setOnPreparedListener {
            mp!!.start()
        }
        return super.onStartCommand(intent, flags, startId)
    }
}