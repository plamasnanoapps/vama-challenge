package com.example.vamacodingchallenge

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.vamacodingchallenge.model.Album
import com.example.vamacodingchallenge.model.presentation.AlbumPresenter
import com.example.vamacodingchallenge.utils.LockScreenOrientation
import com.example.vamacodingchallenge.view.AlbumDetails
import java.io.Serializable

class InfoActivity : ComponentActivity() {

    companion object{
        private const val albumId = "albumId"

        fun intent(context: Context, album: AlbumPresenter) =
            Intent(context, InfoActivity::class.java).apply {
                putExtra(albumId,album)
            }
    }

    private fun <T : Serializable?> getSerializable(activity: Activity, name:String, clazz: Class<T>): T {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            @Suppress("DEPRECATION") activity.intent.getSerializableExtra(name) as T
    }

    private val album : AlbumPresenter by lazy {
        getSerializable(this, albumId, AlbumPresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            AlbumDetails(album = album)
        }
    }
}















