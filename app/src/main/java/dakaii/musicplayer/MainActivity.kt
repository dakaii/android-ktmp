package dakaii.musicplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songCursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            null, null, null, null)

        while (songCursor != null && songCursor.moveToNext()) {
            var songName = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
            var songDuration = songCursor.getInt(songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
        }
    }
}
